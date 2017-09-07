from flask import Flask, request
from flask_restful import Api

import logging
from logging.handlers import RotatingFileHandler

app = Flask(__name__)
debug = True

api = Api(app)
logger = None


@app.before_first_request
def before_first_request():
    def make_handler():
        handler = RotatingFileHandler('server_log.log', maxBytes=100000, backupCount=5)
        # handler.setLevel(logging.DEBUG if debug else logging.INFO)
        formatter = logging.Formatter("[%(asctime)s] %(levelname)s - %(message)s")
        handler.setFormatter(formatter)
        # 로그 포매팅

        return handler

    def make_logger():
        app.logger.addHandler(make_handler())
        app.logger.setLevel(logging.INFO)

        return app.logger
        # 기본값은 WARNING(30)

    global logger
    logger = make_logger()
    # 로거 set

    logger.info('-------- Logger started --------')


@app.before_request
def before_request():
    logger.info('Requested from {0} [ {1} {2} ]'.format(request.host, request.method, request.url))
    logger.info('Request data : {0}'.format(request.form))


@app.after_request
def after_request(response):
    # flask.wrapper.Response 클래스의 인스턴스
    logger.info('Response data : {0}'.format(response.data))
    logger.info('Response status : {0}'.format(response.status))

    return response


@app.teardown_request
def teardown_request(exception):
    logger.info('Teardown request')


@app.teardown_appcontext
def teardown_appcontext(exception):
    logger.info('---- Teardown appcontext')


if __name__ == '__main__':
    app.run(debug=debug)
