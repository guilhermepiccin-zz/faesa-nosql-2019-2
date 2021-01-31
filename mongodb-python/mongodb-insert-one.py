import pymongo
import csv

client = pymongo.MongoClient(
   "<MONGO CONNECTION STRING>")
db = client['indices_mercado']
indexes = db['indice']

obj = {"atributo1":"valor11","atributo2":"valor22"}
indexes.insert_one(obj)
