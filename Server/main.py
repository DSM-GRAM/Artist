from flask import Flask
from flask_restful import Api

import logging
from logging.handlers import RotatingFileHandler

app = Flask(__name__)
debug = True

api = Api(app)
logger = None


@app.before_first_request
def before_first_request():
    handler = RotatingFileHandler('server_log.log', maxBytes=100000, backupCount=5)
    # handler.setLevel(logging.DEBUG if debug else logging.INFO)

    formatter = logging.Formatter("[%(asctime)s] %(levelname)s - %(message)s")
    handler.setFormatter(formatter)
    # 로그 포매팅

    app.logger.addHandler(handler)
    app.logger.setLevel(logging.INFO)
    # 'info' 로그가 성공적으로 로그 파일에 들어갈 수 있도록
    # 기본값은 WARNING(30)

    app.logger.info('Logger started')

    global logger
    logger = app.logger
    # 로거 set


@app.before_request
def before_request():
    pass


@app.after_request
def after_request(response):
    # flask.wrapper.Response 클래스의 인스턴스

    return response


@app.teardown_request
def teardown_request(exception):
    pass


@app.teardown_appcontext
def teardown_appcontext(exception):
    pass


@app.route('/')
def index():
    return 'hello'


if __name__ == '__main__':
    app.run(debug=debug)
