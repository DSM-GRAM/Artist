from database.mongodb import score_col


def add_new_score(phone, score):
    if score_col.find({'phone': phone}).count():
        # 이미 존재
        return False

    data = {
        'phone': phone,
        'score': score
    }

    return score_col.insert(data)


def get_ranking():
    pass
