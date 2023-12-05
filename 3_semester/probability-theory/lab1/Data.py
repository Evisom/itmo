import matplotlib.pyplot as plt

class Data:
    def __init__(self, data):
        self.__round = 5 # Округление
        self.__data = data # Данные
        self.__variation_series = sorted(self.__data) # Вариационный ряд
        self.__extremums = [min(self.__data), max(self.__data)] # Экстремумы
        self.__range = round(self.__extremums[1] - self.__extremums[0], self.__round) # Размах
        self.__count = len(self.__data) # Количество элементов
        self.__expected_value = round(sum(self.__data) / self.__count, self.__round) # Математическое ожидание
        self.__variance = sum([(x - self.__expected_value)**2 for x in self.__data]) / self.__count  * (self.__count / (self.__count - 1))# Дисперсия
        self.__standard_deviation = round(self.__variance ** (1/2), self.__round) # Среднеквадратичное отклонение
        self.__cumulative_frequency = 0
        self.__impirical_distribution_function = [] # Эмпирическая функция распределения
        for i in self.__variation_series:
            self.__cumulative_frequency += 1 / self.__count
            self.__impirical_distribution_function.append(round(self.__cumulative_frequency, self.__round))


    def get_variation_series(self):
        print('Вариационный ряд:')
        print(self.__variation_series)
        return self.__variation_series
    def get_extremums(self):
        print('Экстремальные значения:')
        print(self.__extremums)
        return self.__extremums
    def get_range(self):
        print('Размах вариации:')
        print(self.__range)
        return self.__range

    def get_expected_value(self):
        print('Математическое ожидание:')
        print(self.__expected_value)
        return self.__expected_value

    def get_standard_deviation(self):
        print('Среднеквадратичное отклонение:')
        print(self.__standard_deviation)
        return self.__standard_deviation

    def get_impirical_distribution_function(self):
        print('Эмпирическая функция распределения:')
        print(self.__impirical_distribution_function)
        plt.step(self.__variation_series, self.__impirical_distribution_function, where='post')
        plt.xlabel('Значение')
        plt.ylabel('Эмпирическая функция распределения (ЭФР)')
        plt.title('Эмпирическая функция распределения для выборки')
        plt.show()
        return self.__impirical_distribution_function

    def __get_groups(self, groups=5):
        self.__groups = groups
        self.__interval = self.__range / self.__groups
        self.__data_grouped = [[] for _ in range(self.__groups)]
        for i in self.__data:
            self.__data_grouped[int((i - self.__extremums[0]) / self.__interval)].append(i)
        for i in range(len(self.__data_grouped)):
            if self.__data_grouped[i] == []:
                self.__data_grouped[i] = [0]
        self.__frequencies = []
        for i in self.__data_grouped:
            self.__frequencies.append(len(i) / self.__count)

    def get_polygon(self, groups=5):
        self.__get_groups(groups)
        self.__medians = [round((min(g) + max(g))/2, self.__round) for g in self.__data_grouped]

        plt.plot(self.__medians, self.__frequencies, marker='o', linestyle='-')
        plt.xlabel('x')
        plt.ylabel('p_i')
        plt.title('Полигон приведенных частот')
        plt.grid(True)
        plt.show()

    def get_histogram(self):
        self.__normalized_frequencies = [round(len(i)/len(self.__data_grouped), self.__round) for i in self.__data_grouped]
        plt.bar(self.__medians, self.__frequencies, width=self.__interval)
        plt.xlabel('Середины интервалов')
        plt.ylabel('Приведенные частоты')
        plt.title('Гистограмма приведенных частот')
        plt.show()
        print('Частности и частотности:')
        self.__fr = [self.__data.count(i) for i in self.__data]
        self.__frr = [i / self.__count for i in self.__fr]
        print(self.__fr)
        print(self.__frr)