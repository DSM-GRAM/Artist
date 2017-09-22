from flask import request
from flask_restful import Resource
from database.models.user import *


class User(Resource):
    def post(self):
        phone = request.form['phone']
        name = request.form['name']
        affiliation = request.form['affiliation']
        age = request.form['age']
        category = request.form['category']
        score = request.form['score']

        add_new_score(phone, name, affiliation, age, category, score)

        img = request.files['img']
        img.save('./user_images/{0}.png'.format(phone))

        return '', 201


class CategoryCount(Resource):
    def get(self):
        return get_category_counts()
