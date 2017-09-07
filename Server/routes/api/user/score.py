from flask import request
from flask_restful import Resource
from database.models.score import add_new_score


class Score(Resource):
    def post(self):
        # 새로운 스코어
        phone = request.form['phone']
        score = request.form['score']

        if add_new_score(phone, score):
            return '', 201
        else:
            return '', 204
