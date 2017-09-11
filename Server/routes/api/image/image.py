# 이미지에 대한 로직을 수행합니다. 사용자의 데이터는 일체 관리하지 않습니다.

from flask import request, send_from_directory
from flask_restful import Resource
from database.models.image_queue import *

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


class ImageData(Resource):
    def post(self):
        # 얻어올 샘플 그림에 대한 동적 URI 획득
        # 파일과 데이터를 한 번에 전송할 수 없으므로
        return insert_new_image(request.form['category'])


class Sample(Resource):
    def get(self, uri):
        # 접속 시 샘플 그림 전송
        uri_info = get_uri_info(uri)
        return send_from_directory(image_dirs[uri_info['category']], f"{uri_info['image_num']}.PNG")


class Compare(Resource):
    def post(self):
        # 유사도 측정
        user_img = request.files['img']

        category = int(request.form['category'])
        image_num = int(request.form['image_num'])
        origin_img = open(f'{image_dirs[category]}/{image_num}.png')

        return '', 201
