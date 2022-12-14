import math

flag = True 

def correctInput(msg):
    correct = False
    while not correct:
        i = input(msg)
        try:
            i = int(i)
            correct = True
        except:
            print('Некорректный ввод!')
    return i

while flag:
    print('\n[0] - Площадь круга')
    print('[1] - Площадь прямоугольника')
    print('[2] - Площадь треугольника')
    print('[Q] - Выход\n')
    mode = input('Выберите действие: ')

    if mode.lower() == 'q':
        flag = False 
        break 
    elif mode == '0':
        r = correctInput('Введите радиус: ')
        s = math.pi * r**2
        if r > 0:
            print('Площадь круга:', s)
        else:
            print('Радиус меньше нуля!')
    elif mode == '1':
        a = correctInput('Введите первую сторону: ')
        b = correctInput('Введите вторую сторону: ')
        
        s = a*b
        if a > 0 and b > 0:
            print('Площадь прямоугольника:', s)
        else:
            print('Некорректный ввод!')
    elif mode == '2':
        a = correctInput('Введите первую сторону: ')
        b = correctInput('Введите вторую сторону: ')     
        c = correctInput('Введите третью сторону: ') 
        p = a+b+c
        s = (p*(p-a)*(p-b)*(p-c))**(1/2)
        if (a+b) > c and (a + c) > b and (b+c) > a:
            print('Площадь треугольника:', s)
        else:
            print('Треугольника не существует')
    else:
        print('Некорректный ввод!')