import json
import urllib2
from kafka import SimpleProducer, KafkaClient
from datetime import datetime
from time import sleep
import yaml

kafka = KafkaClient("localhost:9093")
producer = SimpleProducer(kafka)


def get_response(api):
    try:
        req = urllib2.Request(api,headers={"User-Agent":"Magic Browser"})
        data = json.loads(urllib2.urlopen(req).read())
        return str(data)
    except Exception as e:
        print e

if __name__=="__main__":

    def lowda1():
        stream = open("ap_config.yaml","r")
        yaml_obj = yaml.load_all(stream)
        for config_obj in yaml_obj:
            for key,val in config_obj.items():
                return key,val

    while True:
        key,val = lowda1()
        print "Publishing messages for ",key
        producer.send_messages(key,get_response(val))
        sleep(5)
