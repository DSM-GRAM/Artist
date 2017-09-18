# 디바이스에 대한 데이터를 관리합니다.

from flask import request
from flask_restful import Resource
from database.models.device import *


class Device(Resource):
    def post(self):
        registration_id = request.form.get('registration_id')
        _type = request.form.get('type', 1, int)
        # 1 = drawer
        # 2 = shower
        # 3 = leaderboard
        add_device(_type, registration_id)
