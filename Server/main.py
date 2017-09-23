from flask import Flask, request
from flask_restful import Api

import logging
from logging.handlers import RotatingFileHandler

app = Flask(__name__)

api = Api(app)
logger = None


def add_resources():
    def add_device_api():
        from routes.api.device.device import Device

        api.add_resource(Device, '/device')

    def add_user_api():
        from routes.api.user.user import User, CategoryCount

        api.add_resource(User, '/user')
        api.add_resource(CategoryCount, '/category-count')

    def add_leaderboard_api():
        from routes.api.leaderboard.leaderboard import Rank, UserImage

        api.add_resource(Rank, '/rank')
        api.add_resource(UserImage, '/user-image/<phone>')

    def add_image_api():
        from routes.api.image.image import ChooseSample, Sample, StartDraw, Compare

        api.add_resource(ChooseSample, '/new-sample')
        api.add_resource(Sample, '/sample/<_id>')
        api.add_resource(StartDraw, '/start-draw/<_id>')
        api.add_resource(Compare, '/compare/<_id>')

    def add_developer_api():
        from routes.api.developer.dev import ChangeScore, DeleteScore, InitializeDevices

        api.add_resource(ChangeScore, '/change-score')
        api.add_resource(DeleteScore, '/delete-score')
        api.add_resource(InitializeDevices, '/initialize-devices')

    add_device_api()
    add_user_api()
    add_leaderboard_api()
    add_image_api()
    add_developer_api()


@app.before_first_request
def before_first_request():
    def make_logger():
        handler = RotatingFileHandler('server_log.log', maxBytes=100000, backupCount=5)
        # handler.setLevel(logging.DEBUG if debug else logging.INFO)
        formatter = logging.Formatter("[%(asctime)s] %(levelname)s - %(message)s")
        handler.setFormatter(formatter)

        app.logger.addHandler(handler)
        app.logger.setLevel(logging.INFO)

    make_logger()
    global logger
    logger = app.logger
    # 로거 set

    logger.info('-------- Logger started --------')


@app.before_request
def before_request():
    logger.info('Requested from {0} [ {1} {2} ]'.format(request.host, request.method, request.url))
    logger.info('Request data : {0}'.format(request.form))


@app.after_request
def after_request(response):
    # flask.wrapper.Response 클래스의 인스턴스
    logger.info('Response status : {0}'.format(response.status))

    return response


@app.teardown_request
def teardown_request(exception):
    logger.info('Teardown request')


@app.teardown_appcontext
def teardown_appcontext(exception):
    logger.info('---- Teardown appcontext')


if __name__ == '__main__':
    add_resources()
    app.run()
