import pymongo
import csv

client = pymongo.MongoClient(
   "<MONGODB CONNECTION STRING")

db = client['indices_mercado']

indexes = db['indice']

vetor = []
with open('indexes_data.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 1
    for row in csv_reader:
        vetor.append({"date":row["date"], "index":row["index"], "value":row["value"]})
        line_count += 1
        if line_count % 500 == 0:
            print(line_count)
            indexes.insert_many(vetor)
            vetor = []

print(indexes.count())
#indexes.drop()