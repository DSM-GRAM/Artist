from flask import request, send_from_directory
from flask_restful import Resource
from database.models.image_queue import *

image_dirs = [
    '',
    './files/animal',
    './files/artist',
    './files/calligraphy',
    './files/character',
    './files/food',
    './files/motif',
    './files/optical_illusion',
    './files/other',
    './files/people',
    './files/scenery'
]


class ImageData(Resource):
    def post(self):
        # 얻어올 샘플 그림에 대한 동적 URI 획득
        # 파일과 데이터를 한 번에 전송할 수 없으므로
        return insert_new_image(request.form['category'])


class Sample(Resource):
    def get(self, uri):
        # 접속 시 샘플 그림 전송
        image_info = get_image_info(uri)
        return send_from_directory(image_dirs[image_info['category']], '{0}.PNG'.format(image_info['image_num']))


class Paint(Resource):
    def post(self):
        # 유사도 측정
        sample = request.files['sample']
        painted = request.files['painted']
        pass
