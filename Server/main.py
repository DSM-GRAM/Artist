from flask import Flask
from flask_restful import Api

app = Flask(__name__)
api = Api(app)

debug = True


@app.route('/')
def index():
    return 'hello'


if __name__ == '__main__':
    app.run(debug=debug)
