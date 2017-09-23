# -*- coding: utf8 -*-
from database.mongodb import device_col


def add_device(_type, registration_id):
    device_col.remove({'registration_id': registration_id})
    # 중복 제거

    device_col.insert({
        'type': _type,
        'registration_id': registration_id,
        'status': 0
    })


def get_devices_by_type(_type):
    return list(device_col.find({'type': _type}, {'_id': False}))


def get_shower(status_for_find):
    return device_col.find_one({'type': 2, 'status': status_for_find}, {'_id': False})
