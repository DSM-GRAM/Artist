# 리더보드를 위한 데이터를 관리합니다

from flask import request
from flask_restful import Resource
from database.models.user import *


class Rank(Resource):
    def get(self):
        return get_user_data_list()


class Image(Resource):
    def get(self):
        phone = request.args['phone']
        pass
