import time
import requests
import json
import urllib2
from kafka import SimpleProducer, KafkaClient
from kafka import KafkaProducer
from datetime import datetime
from time import sleep


kafka = KafkaClient("localhost:9093")
producer = SimpleProducer(kafka)

topic = 'cex.io'

def send_message(api_name=None):
    req=urllib2.Request("https://cex.io/api/currency_limits",headers={'User-Agent' : "Magic Browser"})
    data = json.loads(urllib2.urlopen(req).read())
    return str(data)

#
if __name__=="__main__":
    while True:
        print "Sending Message!"
        producer.send_messages("cex.io",send_message())
        sleep(5)
