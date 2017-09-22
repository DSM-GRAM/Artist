# -*- coding: utf8 -*-
from PIL import Image


def image_compare(img1_location, img2_location):
    # 사진 불러오기
    img1 = Image.open(img1_location)
    img2 = Image.open(img2_location)
    if img1.size != img2.size:
        img1 = img1.resize(img2.size)
    x, y = img2.size

    # RGB 오차 범위
    range1 = -20
    range2 = 20
    same = 0
    white = 0

    # 픽셀 비교
    for i in range(x):
        for j in range(y):

            if sum(img1.getpixel((i, j))) + sum(img1.getpixel((i, j))) > 1500:
                white += 1
                continue

            try:
                temp_r1, temp_g1, temp_b1 = img1.getpixel((i, j))
            except ValueError:
                temp_r1, temp_g1, temp_b1, _ = img1.getpixel((i, j))
            try:
                temp_r2, temp_g2, temp_b2 = img2.getpixel((i, j))
            except ValueError:
                temp_r2, temp_g2, temp_b2, _ = img2.getpixel((i, j))

            if range1 < temp_r1 - temp_r2 < range2 and range1 < temp_g1 - temp_g2 < range2 and range1 < temp_b1 - temp_b2 < range2:
                same += 1

    return round(same / ((x * y) - white) * 100, 2)

print(image_compare('a.png', 'b.png'))