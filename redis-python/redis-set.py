import redis
import csv
r = redis.StrictRedis(host='<REDIS HOST>',
        port=6380, db=0, password='<REIDS KEY>', ssl=True)

with open('load.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 1
    for row in csv_reader:
        k = row["date"] + row["index"]
        v = str({"date":row["date"], "index":row["index"], "value":row["value"]})
        r.set(k, v)
        line_count += 1

#r.flushall()

#print(r.get('foo'))