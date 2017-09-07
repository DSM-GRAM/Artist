from flask import Flask
from flask_restful import Api


app = Flask(__name__)
api = Api(app)

debug = True


@app.before_first_request
def before_first_request():
    pass


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
