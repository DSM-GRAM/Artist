# 사용자에 대한 데이터를 관리합니다

from flask import request, send_from_directory
from flask_restful import Resource
from database.models.user import *


class User(Resource):
    def post(self):
        # 새로운 사용자(스코어) 추가
        phone = request.form['phone']
        name = request.form['name']
        affiliation = request.form['affiliation']
        age = request.form['age']
        category = request.form['category']
        score = request.form['score']

        add_new_score(phone, name, affiliation, age, category, score)

        img = request.files['img']
        # 사용자의 이미지 처리
        img.save(f'./user_images/{phone}.png')

        # leaderboard 쪽으로 푸쉬 보내야 함

        return '', 201


class CategoryCount(Resource):
    def post(self):
        # 카테고리 카운트
        return get_category_counts()
