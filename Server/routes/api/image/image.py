# -*- coding: utf8 -*-
# 이미지에 대한 로직을 수행합니다. 사용자의 데이터는 일체 관리하지 않습니다.

from flask import request, send_from_directory
from flask_restful import Resource

from database.models.image_queue import *
from database.models.device import *

from support.fcm import push
from support.imgcompare import image_compare

from uuid import uuid4
import os
import random


image_dirs = [
    '',
    './sample_images/animal',
    './sample_images/calligraphy',
    './sample_images/character',
    './sample_images/food',
    './sample_images/people'
]


class ChooseSample(Resource):
    def post(self):
        # 2. 샘플 그림 선정과 동적 URI 생성
        category = request.form.get('category', 0, int)
        _id = str(choose_new_sample(category))

        shower = get_shower(0)
        if shower:
            push(shower['registration_id'], {'message': 'sample', '_id': _id})
            # 대기 중인 shower

            shower.update({
                'status': 1
            })

            device_col.update({'registration_id': shower['registration_id']}, shower)
            # 상태를 '샘플 그림 view'로 변경

            return {'_id': _id}, 201
        else:
            return '', 204


class Sample(Resource):
    def get(self, _id):
        # 3. 샘플 그림 get
        category, image_num = get_image_data_by_id(_id)
        return send_from_directory(image_dirs[category], '{0}.png'.format(image_num))


class StartDraw(Resource):
    def post(self, _id):
        # 4. 그리기 시작
        shower = get_shower(1)
        if shower:
            push(shower['registration_id'], {'message': 'start'})
            # 샘플 이미지 view중인 shower

            shower.update({
                'status': 0
            })

            device_col.update({'registration_id': shower['registration_id']}, shower)
            # 상태를 '샘플 그림 view'로 변경

            return '', 201
        else:
            return '', 204


class Compare(Resource):
    def post(self, _id):
        # 5. 유사도 측정
        user_img = request.files['img']
        img_name = uuid4()
        user_img.save('{0}.png'.format(img_name))
        # 임시 저장

        category, image_num = get_image_data_by_id(_id)

        score = 5000 + image_compare('{0}.png'.format(img_name), '{0}/{1}.png'.format(image_dirs[category], image_num)) * 950 * 0.65 + random.randrange(1, 30001)
        os.remove('{0}.png'.format(img_name))

        push(get_devices_by_type(2).append(get_devices_by_type(3)), {'message': 'end'})
        return score, 201
