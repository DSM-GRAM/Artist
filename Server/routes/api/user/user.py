from flask import request
from flask_restful import Resource
from database.models.user import *


class Score(Resource):
    def post(self):
        phone = request.form['phone']
        name = request.form['name']
        affiliation = request.form['affiliation']
        age = request.form['age']
        score = request.form['score']

        return '', 201 if add_new_score(phone, name, affiliation, age, score) else 204
