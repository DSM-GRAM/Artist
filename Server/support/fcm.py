from pyfcm import FCMNotification

fcm = FCMNotification(api_key='AAAAyTAfM2Y:APA91bHhDWYab_8mLYaPWiDl28160C8st3uxWMjt_x3OvFLJgZYqdcUdQnrmBw_63KdHy4h3NtaZulyTKLtC_Rqrjw-reRSKosCkOkWH7l6gw_2Q7MOuZJv6malt_cyU8vfSOBMSHRkl')


def push(registration_ids, data):
    if registration_ids:
        print(fcm.notify_multiple_devices(registration_ids=registration_ids, message_title=data))
