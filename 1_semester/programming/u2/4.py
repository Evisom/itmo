d = input('Введите месяц и день через точку: ').split('.')
d[0] = int(d[0])
d[1] = int(d[1])
c = [31,28,31,30,31,30,31,31,30,31,30,31]
if 0 < d[0] <= 12 and 1 <= d[1] <= c[d[0]-1]:
    print(sum(c) - sum(c[0:d[0]-1]) - d[1])
else:
    print('Некорректный ввод')