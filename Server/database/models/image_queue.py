from database.mongodb import image_queue_col
from pymongo.collection import ObjectId
import random


def choose_new_sample(category):
    return image_queue_col.insert({
        'category': category,
        'image_num': random.randrange(1, 21)
    })


def get_image_data_by_id(_id):
    image_data = image_queue_col.find_one({'_id': ObjectId(_id)})
    return image_data['category'], image_data['image_num']
