import redis
import csv
r = redis.StrictRedis(host='<REDIS HOST>',
        port=6380, db=0, password='<REIDS KEY>', ssl=True)

r.flushall()