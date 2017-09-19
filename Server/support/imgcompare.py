from PIL import Image


def image_compare(img1_location, img2_location):

    # 사진 불러오기
    img1 = Image.open(img1_location)
    img2 = Image.open(img2_location)
    if img1.size != img2.size:
        return '같은 크기의 이미지 넣어주센'
    x, y = img1.size

    # row 4, column 3의 격자
    xy = [[0, x//3, 0, y//4], [x//3, 2 * x//3, 0, y//4], [2 *x//3, x, 0, y//4],
          [0, x//3, y//4, 2 * y//4], [x//3, 2 * x//3, y//4, 2 * y//4], [2 *x//3, x, y//4, 2 * y//4],
          [0, x//3, 2 * y//4, 3 * y//4], [x//3, 2 * x//3, 2 * y//4, 3 * y//4], [2 * x//3, x, 2 * y//4, 3 * y//4],
          [0, x//3, 3 * y//4, y], [x//3, 2 * x//3, 3 * y//4, y], [2 *x//3, x, 3 * y//4, y]]

    diff = [0] * 12

    # RGB 오차 범위
    range1 = -10
    range2 = 10

    # 픽셀 비교
    for i in range(12):
        x1, x2, y1, y2 = xy[i]
        for j in range(x1, x2):
            for k in range(y1, y2):
                try:
                    temp_r1, temp_g1, temp_b1 = img1.getpixel((j, k))
                except ValueError:
                    temp_r1, temp_g1, temp_b1, _ = img1.getpixel((j, k))
                try:
                    temp_r2, temp_g2, temp_b2 = img2.getpixel((j, k))
                except ValueError:
                    temp_r2, temp_g2, temp_b2, _ = img2.getpixel((j, k))
                if range1 < temp_r1-temp_r2 < range2 and range1 < temp_g1-temp_g2 < range2 and range1 < temp_b1-temp_b2 < range2:
                    diff[i] += 1
        diff[i] /= (x2-x1) * (y2-y1) * 12/100

    return round(sum(diff), 2)
