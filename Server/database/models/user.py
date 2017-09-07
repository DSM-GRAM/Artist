from database.mongodb import user_col


def create_user(phone, name, affiliation, age):
    if user_col.find({'phone': phone}).count():
        # 이미 존재
        return False


def delete_user(phone):
    data = {

    }


def get_user_data(phone):
    pass
