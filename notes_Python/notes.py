import os

def show_menu():
    '''Функция меню
    '''
    os.system('clear')
    print("\nВыберите необходимое действие "
          "и введите соответствующее число:\n"
          "1 - Создать новую записку\n"
          "2 - Найти заметку\n"
          "3 - Показать все заметки\n"
          "4 - Изменить существующую заметку\n"
          "5 - Удалить заметку\n"
          "0 - Завершить работу программы\n")
    choice = input('> ')
    if choice.isdigit(): return int(choice)
    return show_menu()

def go_to_function(variant):
    '''Функция адресации выполнения задач
    '''
    while (variant != 0):
        if variant == 1:
            add_note(filename)
            print('\nданные внесены !', end='')
            pause()
        elif variant == 2:
            finder = input_finder('введите дату последнего изменения заметки')
            find_note(filename, finder)
            pause()
        elif variant == 3:
            for line in read_all_notes(filename):
                print(*line, end='')
            pause()              
        elif variant == 4:
            for line in read_all_notes(filename):
                print(*line, end='')
            finder = input_finder('\n\nкакую заметку вы ходите изменить?\nВведите дату её создания/изменения')
            redact_note(filename, finder)
            pause()
        elif variant == 5:
            finder = input_finder('введите фамилию или имя абонента')
            delete_note(filename, finder)
            print('\nданные удалены !', end='')
            pause()
        variant = show_menu()

def pause():
    '''Функция ожидания нажатия клавиши пользователем
    '''
    input('\nнажмите Enter для продолжения...')

def add_note(filename, line = []):
    '''Функция добавления заметки в файл
    '''
    print('введите следующие данные для записи в справочник:')
    for item in fields:
        line.append(input(f'\n{item.strip()} > ').title())
    with open(filename, 'a', encoding='utf-8') as file:
        file.write(f'{";".join(line)}\n')

def input_finder(message):
    '''Функция запроса данных от пользователя
    '''
    answer = input(f'\n{message} > ').title()
    print('\n')
    return answer

def find_note(filename, find_param):
    '''Функция поиска строки данных по запросу пользователя
    '''
    phone_list = read_all_notes(filename)
    find_list = []
    for line in phone_list:
        if find_param in line:
            find_list.append(line)
    if len(find_list) == 0: print('нет данных!')
    else: 
        find_list.insert(0, fields)
        for line in find_list:
            print(*line, end='')
        return find_list

def read_all_notes(filename):
    '''Функция импорта заметок из файла
    '''
    phone_list = [fields]
    with open(filename, 'r', encoding='utf-8') as file:
        for line in file:
            phone_list.append(line.split(';'))
    return phone_list

def write_note(filename, phone_dict):
    '''Функция экспорта заметок в файл
    '''
    with open(filename, 'w', encoding='utf-8') as file:
        for line in phone_dict:
            file.write(";".join(line))

def redact_note(filename, find_param):
    '''Функция изменения заметки
    '''
    list_word = ["ID", "Заголовок", "Тело_заметки", "Дата_время"]
    print('какие старые данные необходимо изменить:')
    for word in list_word:
        print(f'{list_word.index(word) + 1} - {word}')
    ind = (input('введите соответствующее число > '))
    if ind.isdigit():
        ind = int(ind)
        if 1 <= ind <= 4:
            new_data = input(f'Теперь новое значение - введите {list_word[ind-1]} > ').title()
            flag = False # Проверка наличия искомого абонента
            phone_book = read_all_notes(filename)[1:]
            for line in phone_book:
                if find_param in line:
                    flag = True
                    line[ind-1] = new_data
                    print(*line)
            if flag:
                write_note(filename, phone_book)
                print('\nизменения внесены !', end='')
                return
    print("Вы ввели неверное значение!", end='')

def delete_note(filename, find_param):
    '''Функция удаления заметки из файла
    '''
    phone_book = read_all_notes(filename)[1:]
    for line in phone_book:
        if find_param in line:
            print(*line)
            phone_book.remove(line)
    write_note(filename, phone_book)





filename = './notes_Python/notes.csv'
fields = ["ID", "Заголовок", "Тело_заметки", "Дата_время\n\n"]
go_to_function(show_menu())