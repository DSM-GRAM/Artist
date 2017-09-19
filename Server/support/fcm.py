from pyfcm import FCMNotification

fcm = FCMNotification(api_key='')


def push(registration_ids, data):
    fcm.notify_multiple_devices(registration_ids=registration_ids, data_message=data)
