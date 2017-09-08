from flask import request
from flask_restful import Resource
from database.models.user import *


class User(Resource):
    def post(self):
        # 새로운 사용자(스코어) 추가
        phone = request.form['phone']
        name = request.form['name']
        affiliation = request.form['affiliation']
        age = request.form['age']
        score = request.form['score']

        if add_new_score(phone, name, affiliation, age, score):
            # leaderboard 쪽으로 푸쉬 보내야 함
            return '', 201
        else:
            return '', 204
