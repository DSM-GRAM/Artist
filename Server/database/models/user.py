from database.mongodb import user_col
import pymongo


def add_new_score(phone, name, affiliation, age, category, score):
    data = {
        'phone': phone,
        'name': name,
        'affiliation': affiliation,
        'age': int(age),
        'category': int(category),
        'score': int(score)
    }

    if user_col.find({'phone': phone}).count():
        user_col.update({'phone': phone}, data)

    return user_col.insert(data)


def get_user_data_list():
    return list(user_col.find({}, {'_id': False}).sort('score', pymongo.DESCENDING))


def get_category_counts():
    data = {}
    for i in range(1, 11):
        data[i] = user_col.find({'category': i}).count()

    return data
