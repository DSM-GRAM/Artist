from pyfcm import FCMNotification

fcm = FCMNotification(api_key='AAAAyTAfM2Y:APA91bHhDWYab_8mLYaPWiDl28160C8st3uxWMjt_x3OvFLJgZYqdcUdQnrmBw_63KdHy4h3NtaZulyTKLtC_Rqrjw-reRSKosCkOkWH7l6gw_2Q7MOuZJv6malt_cyU8vfSOBMSHRkl')


def push(registration_id, data):
    if registration_id:
        print('Push start')
        print(fcm.notify_single_device(registration_id=registration_id, message_title=data))
