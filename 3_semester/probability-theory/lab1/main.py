# [+] Вариационный ряд
# [+] Экстремальные значения и размах
# [+] Оценки математического ожидания
# [+] Среднеквадратичное отклонение
# [+] Эмпирическая функция распределения + график
# [ ] Гистограмма и полигон приведенных частот

from Data import Data

raw_data = open('data.txt').readlines()
data = []

for i in raw_data:
    try: data.append(float((i[:-1])))
    except: print('Invalid line!')
print('Исходные данные:')
print(data)
data = Data(data)
data.get_variation_series()
data.get_extremums()
data.get_range()
data.get_expected_value()
data.get_standard_deviation()
data.get_impirical_distribution_function()
data.get_polygon(6)
data.get_histogram()