from pymongo import MongoClient

client = MongoClient('localhost', 27017)
db = client.gram_artist

user_col = db.user
image_data_col = db.image_data
