# 이미지에 대한 로직을 수행합니다. 사용자의 데이터는 일체 관리하지 않습니다.

from flask import request, send_from_directory
from flask_restful import Resource
from database.models.user_chosen_image import *

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


class Sample(Resource):
    def get(self, phone):
        # 접속 시 샘플 그림 전송
        category = int(request.args['category'])
        image_num = insert_new_image(phone, category)
        return send_from_directory(image_dirs[category], f"{image_num}.PNG")


class Compare(Resource):
    def post(self, phone):
        # 유사도 측정
        user_img = request.files['img']

        category, image_num = get_image_data_by_phone(phone)

        origin_img = open(f'{image_dirs[category]}/{image_num}.png')

        return '', 201
