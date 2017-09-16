from database.mongodb import image_data_col
import random


def insert_new_image(phone, category):
    image_num = random.randrange(1, 21)

    data = {
        'phone': phone,
        'image_num': image_num,
        'category': category
    }

    image_data_col.insert(data)
    return image_num


def get_image_data_by_phone(phone):
    image_data = image_data_col.find_one({'phone': phone}, {'_id': False, 'phone': False})
    return image_data['category'], image_data['image_num']
