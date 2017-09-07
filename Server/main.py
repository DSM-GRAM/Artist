from flask import Flask
from flask_restful import Api

import logging
from logging.handlers import RotatingFileHandler

app = Flask(__name__)
api = Api(app)

debug = True


@app.before_first_request
def before_first_request():
    handler = RotatingFileHandler('server_log.log', maxBytes=100000, backupCount=5)
    handler.setLevel(logging.DEBUG if debug else logging.INFO)

    formatter = logging.Formatter("[%(asctime)s] %(levelname)s - %(message)s")
    handler.setFormatter(formatter)

    app.logger.addHandler(handler)
    app.logger.info('Logger started')


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
