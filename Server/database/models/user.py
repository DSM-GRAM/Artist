from database.mongodb import user_col


def add_new_score(phone, name, affiliation, age, score):
    if user_col.find({'phone': phone}).count():
        # 이미 존재
        return False

    data = {
        'phone': phone,
        'name': name,
        'affiliation': affiliation,
        'age': age,
        'score': score
    }

    return user_col.insert(data)


def delete_user(phone):
    data = {

    }


def get_user_data(phone):
    pass
