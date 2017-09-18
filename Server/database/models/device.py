from database.mongodb import device_col


def add_device(_type, registration_id):
    device_col.insert({
        'type': _type,
        'registration_id': registration_id
    })


def get_devices_by_type(_type):
    return list(device_col.find({'type': _type}, {'_id': False}))
