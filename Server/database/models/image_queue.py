from database.mongodb import image_queue_col
import uuid
import random


def insert_new_image(category):
    image_num = random.randrange(1, 21)
    uri = str(uuid.uuid4()).replace('-', '')

    data = {
        'image_num': image_num,
        'uri': uri,
        'category': category
    }

    image_queue_col.insert(data)
    del data['_id']
    return data


def get_image_info(uri):
    return dict(image_queue_col.find_one({'uri': uri}, {'_id': False}))
