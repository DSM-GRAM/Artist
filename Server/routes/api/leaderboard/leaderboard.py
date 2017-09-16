# 리더보드를 위한 데이터를 관리합니다

from flask import request, send_from_directory
from flask_restful import Resource
from database.models.user import *


class Rank(Resource):
    def get(self):
        return get_user_data_list()


class UserImage(Resource):
    def get(self, phone):
        # 특정 사용자의 이미지 GET
        return send_from_directory('./user_images', f"{phone}.png")
