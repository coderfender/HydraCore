import json
import urllib2
from kafka import SimpleProducer, KafkaClient
from datetime import datetime
from time import sleep
import yaml
from kafka.structs import TopicPartition
from collections import namedtuple
from kafka import KafkaProducer
from pickle import dumps
import logging


kafka = KafkaClient("localhost:9093")
producer = KafkaProducer(bootstrap_servers=["localhost:9093"])
logging.basicConfig(level=logging.INFO)
logger=logging.getLogger(__name__)


def get_response(api):
    try:
        req = urllib2.Request(api,headers={"User-Agent":"Magic Browser"})
        data = json.loads(urllib2.urlopen(req).read())
        return data
    except Exception as e:
        logger.exception(e)

class Config(object):

    def __init__(self):
        self.named_tuple = namedtuple("Crypto","type price curr")
        self.crypto_yaml = yaml.load_all(open("coin_meta.yaml"))

    def get_config(self,api_link,market=None):
        if market=="cex.io":
            return self.cex_io(api_link)
        elif market=="coin.market.cap":
            return self.coin_market_cap(api_link)
        elif market=="gdax.api":
            print "Hitting GDAX!"
            return self.gdax(api_link)
        logger.exception("Config not found !!")
        return None

    def coin_market_cap(self,api_link,ticker=True):
        json_dump = get_response(api_link)[0]
        return [self.named_tuple(type="BTC",price=json_dump["price_usd"],curr="USD")]


    def gdax(self,api_link,ticker=False):
        for crypto_obj in self.crypto_yaml:
            for coin_name,code in crypto_obj.items():
                try:
                    json_dump = get_response(api_link.format(code))
                    named_tup = self.named_tuple(type=code,price=json_dump["price"],curr="USD")
                    yield named_tup
                except Exception as e:
                    logger.warn(msg=" ".join([str(e),coin_name,"gdax"]))


    def cex_io(self,api_link,ticker=False):
        curr2="USD"
        for crytpo_obj in self.crypto_yaml:
            for coin_name,code in crytpo_obj.items():
                try:
                    json_dump = get_response(api_link.format(code,curr2))
                    yield self.named_tuple(type=code,price=json_dump["lprice"],curr=json_dump["curr2"])
                except Exception as e:
                    logger.warn(msg=str(e)+" "+coin_name+" "+"cex_io")

def send_messages(key,val,conf_obj,producer):
    crypto_tuples = config.get_config(val,key)
    # if not crypto_tuples:
    #     logger.debug("No values returned")
    #     return producer
    for crypto_tuple in crypto_tuples:
        value = json.dumps({"price":str(crypto_tuple.price),
                            "curr":str(crypto_tuple.curr),
                            "time":str(datetime.now())})
        producer.send(topic=key,key=crypto_tuple.type,value=value)
        logger.info("Sending messages for {0}".format(key))
    return producer



if __name__=="__main__":

    config = Config()

    while True:

        with open("ap_config.yaml","r") as stream:
            yaml_obj = yaml.load_all(stream)
            for config_obj in yaml_obj:
                for (key,val) in config_obj.items():
                    send_messages(key,val,config,producer)
                    print "Sent messages for ",key
        sleep(5)
