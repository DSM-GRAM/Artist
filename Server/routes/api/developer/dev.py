from flask import request
from flask_restful import Resource

from database.mongodb import user_col, device_col


class ChangeScore(Resource):
    def post(self):
        name = '"' + request.form.get('name') + '"'
        score = request.form.get('score', type=float)
        to_score = request.form.get('to_score', type=float)

        data = user_col.find_one({'name': name, 'score': score})
        data.update({
            'score': to_score
        })

        user_col.update({'name': name, 'score': score}, data)

        return '', 201


class DeleteScore(Resource):
    def post(self):
        name = '"' + request.form.get('name') + '"'
        score = request.form.get('score', type=float)

        user_col.remove({'name': name, 'score': score})

        return '', 201


class InitializeDevices(Resource):
    def post(self):
        device_col.remove()
