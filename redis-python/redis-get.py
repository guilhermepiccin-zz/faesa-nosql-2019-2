import redis
import csv
r = redis.StrictRedis(host='<REDIS HOST>',
        port=6380, db=0, password='<REIDS KEY>', ssl=True)

print(r.get('1995-01-30ipca'))