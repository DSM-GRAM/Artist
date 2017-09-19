from pymongo import MongoClient

client = MongoClient('localhost', 27017)
db = client.gram_artist

user_col = db.user
image_queue_col = db.image_queue
device_col = db.device
