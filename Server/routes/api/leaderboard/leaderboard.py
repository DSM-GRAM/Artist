from flask import request
from flask_restful import Resource
from database.models.user import *


class Rank(Resource):
    def get(self):
        # 랭킹
        pass
