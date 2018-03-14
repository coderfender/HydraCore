import time
from kafka import SimpleProducer, KafkaClient
import requests
import json
import urllib2
from kafka import KafkaProducer
from datetime import datetime
from time import sleep



# kafka = KafkaClient('localhost:8080')

kafka = KafkaClient("localhost:9093")
producer = SimpleProducer(kafka)

topic = 'cex.io'

# while 1:
#   # "kafkaesque" is the name of our topic
#   producer.send_messages("kafkaesque", ""+ str(datetime.now().time()) )
#   sleep(1)

def send_message(api_name=None):
    req=urllib2.Request("https://cex.io/api/currency_limits",headers={'User-Agent' : "Magic Browser"})
    data = json.loads(urllib2.urlopen(req).read())
    # print data
    return str(data)
    return str(datetime.now())


#
if __name__=="__main__":
    while True:
        print "Sending Message!"
        producer.send_messages("cex.io",send_message())
        sleep(5)
