# from flask import request
from flask_restful import Resource
from database.models.user import *


class Rank(Resource):
    def get(self):
        return get_user_data_list()
