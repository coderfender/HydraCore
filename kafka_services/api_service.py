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


kafka = KafkaClient("localhost:9093")
producer = KafkaProducer(bootstrap_servers=["localhost:9093"])


def get_response(api):
    try:
        req = urllib2.Request(api,headers={"User-Agent":"Magic Browser"})
        data = json.loads(urllib2.urlopen(req).read())
        return data
    except Exception as e:
        print e

class Config(object):

    def __init__(self):
        self.named_tuple = namedtuple("Crypto","type price curr")
        with open("coin_meta.yaml") as yaml_coin:
            pass

    def get_config(self,api_link,market=None):
        if market=="cex.io":
            return self.cex_io(api_link)
        elif market=="coin.market.cap":
            return self.coin_market_cap(api_link)
        elif market=="gdax.api":
            return self.gdax(api_link)
        print "Config not found !!"
        return

    def coin_market_cap(self,api_link):
        json_dump = get_response(api_link)[0]
        return self.named_tuple(type="BTC",price=json_dump["price_usd"],curr="USD")


    def gdax(self,api_link):
        json_dump = get_response(api_link.format("BTC"))
        return self.named_tuple(type="BTC",price=json_dump["price"],curr="USD")

    def cex_io(self,api_link):
        curr1 = "BTC"
        curr2 ="USD"
        json_dump = get_response(api_link.format(curr1,curr2))
        hasher = {}
        return self.named_tuple(type="BTC",price=json_dump["lprice"],curr=json_dump["curr2"])


def send_messages(key,val,conf_obj,producer):
    crypto_tuple = config.get_config(val,key)
    value = json.dumps({"price":str(crypto_tuple.price),"curr":str(crypto_tuple.curr)})
    producer.send(topic=key,key=crypto_tuple.type,value=value)
    print "Sending messages for",key
    return producer



if __name__=="__main__":

    config = Config()

    while True:
        with open("ap_config.yaml","r") as stream:
            yaml_obj = yaml.load_all(stream)
            [[send_messages(key,val,config,producer)
              for (key,val) in config_obj.items()]
              for config_obj in yaml_obj]
            sleep(15)
