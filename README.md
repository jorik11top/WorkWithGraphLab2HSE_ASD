# WorkWithGraphLab2HSE_ASD
Выполнил студент Чайкин Георгий Максимович 21ПИ3 
Почта с контеста georg.tchaikin@yandex.ru

1 алгоритм BruteForce

Суть состоит в том, что мы сохраняем все прямоугольники и точку проверяем на каждом прямоугольнике
Временная сложность(N) поиска где N - кол-во прямоугольников

2 алгоритм CreateMap

Мы создаём карту по сжатым точкам, и каждый раз сжимаем точку, для чего нам необходимо подготовка мапы (N^3), а сам поиск идёт за logN ибо мы сжимаем точку с помощью бинарного поиска

3 алгоритм PersistentTree

Сжимаем координаты и создаём массив деревьев по x, с этим модификатором, после чего находим сумму обходя дерево. Подготовка за (NlogN) поиск за logN.

Тесты

Каждый Запуск Тестов прогоняется по 10 раз
Вот ссылка графики и значения тестов
https://docs.google.com/spreadsheets/d/1-WKGGRD6xD8VxugWuvqiavTa4M6tVEdzFKoDDW6v2vo/edit?usp=sharing

Вывод

На начальных этапах, первый алгоритм эффективнее для запуска, но при более больших данных становится неэффективен для поиска точки.
Второй алгоритм, работает быстро за счёт чистый logN поиска, но тратиться большое кол-во врмени на создание карты, и сложно даже для тестов, ибо время создания, очень большое
Третий Алгоритм затратен на начальных этапах, но имеет меньшее времени подготовки чем 2 алогоритм, и мы получаем сложность поиска 2logN(поиск и спуск по дереву) ну или O(logN)
