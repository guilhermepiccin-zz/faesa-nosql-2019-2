import pymongo
import csv

client = pymongo.MongoClient(
   "<MONGODB CONNECTION STRING>")

db = client['indices_mercado']

indexes = db['indice']

print(indexes.find_one({"matricula":"9876"}))