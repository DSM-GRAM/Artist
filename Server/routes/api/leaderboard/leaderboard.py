# -*- coding: utf8 -*-
# 리더보드를 위한 데이터를 관리합니다

from flask import send_from_directory
from flask_restful import Resource
from database.models.user import *


class Rank(Resource):
    def get(self):
        user_data = get_user_data_list()
        if user_data:
            return user_data, 200
        else:
            return '', 204


class UserImage(Resource):
    def get(self, phone):
        # 특정 사용자의 이미지 GET
        return send_from_directory('./user_images', '{0}.PNG'.format(phone))
