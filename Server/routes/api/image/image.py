# 이미지에 대한 로직을 수행합니다. 사용자의 데이터는 일체 관리하지 않습니다.

from flask import request, send_from_directory
from flask_restful import Resource
from database.models.image_queue import *
from database.models.device import *
from support.fcm import push

image_dirs = [
    '',
    './sample_images/animal',
    './sample_images/artist',
    './sample_images/calligraphy',
    './sample_images/character',
    './sample_images/food',
    './sample_images/motif',
    './sample_images/optical_illusion',
    './sample_images/other',
    './sample_images/people',
    './sample_images/scenery'
]


class ChooseSample(Resource):
    def post(self):
        # 2. 샘플 그림 선정과 동적 URI 생성
        category = request.form.get('category', 0, int)
        push(get_devices_by_type(2), {'message': 'sample'})
        return {'_id': str(choose_new_sample(category))}


class Sample(Resource):
    def get(self, _id):
        # 3. 샘플 그림 get
        category, image_num = get_image_data_by_id(_id)
        return send_from_directory(image_dirs[category], f"{image_num}.PNG")


class StartDraw(Resource):
    def post(self, _id):
        # 4. 그리기 시작
        push(get_devices_by_type(2), {'message': 'start'})
        return '', 201


class Compare(Resource):
    def post(self, _id):
        # 5. 유사도 측정
        user_img = request.files['img']
        category, image_num = get_image_data_by_id(_id)

        origin_img = open(f'{image_dirs[category]}/{image_num}.png')

        push(get_devices_by_type(2).append(get_devices_by_type(3)), {'message': 'end'})
        return '', 201
