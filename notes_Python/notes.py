import os

def showMenu():
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
    return showMenu()

def go_to_function(variant):
    '''Функция адресации выполнения задач
    '''
    while (variant != 0):
        if variant == 1:
            addNote(filename)
            print('\nзаметка сохранена !', end='')
            pause()
        elif variant == 2:
            finder = inputFinder('введите дату создания/изменения заметки')
            findNote(filename, finder)
            pause()
        elif variant == 3:
            for row in readAllNotes(filename):
                print(*row, end='')
            pause()              
        elif variant == 4:
            for row in readAllNotes(filename):
                print(*row, end='')
            finder = inputFinder('\n\nкакую заметку вы ходите изменить?\nВведите дату её создания/изменения')
            redactNote(filename, finder)
            pause()
        elif variant == 5:
            finder = inputFinder('введите фамилию или имя абонента')
            deleteNote(filename, finder)
            print('\nданные удалены !', end='')
            pause()
        variant = showMenu()

def pause():
    '''Функция ожидания нажатия клавиши пользователем
    '''
    input('\nнажмите Enter для продолжения...')

def addNote(filename, line = []):
    '''Функция добавления заметки в файл
    '''
    print('введите следующие данные для записи в справочник:')
    for item in fields:
        line.append(input(f'\n{item.strip()} > '))
    with open(filename, 'a', encoding='utf-8') as file:
        file.write(f'{";".join(line)}\n')

def inputFinder(message):
    '''Функция запроса данных от пользователя
    '''
    answer = input(f'\n{message} > ')
    print('\n')
    return answer

def findNote(filename, findParameter) -> list:
    '''Функция поиска заметки по дате изменения/создания
    '''
    allNotesRows = readAllNotes(filename)
    findList = []
    for row in allNotesRows:
        if findParameter in row:
            findList.append(row)
    if len(findList) == 0: print('нет записки с указанной датой!')
    else: 
        findList.insert(0, fields)
        for row in findList:
            print(*row, end='')
    return findList

def readAllNotes(filename):
    '''Функция импорта заметок из файла
    '''
    allNotesRows = [fields]
    with open(filename, 'r', encoding='utf-8') as file:
        for row in file:
            allNotesRows.append(row.split(';'))
    return allNotesRows

def writeNote(filename, phone_dict):
    '''Функция экспорта заметок в файл
    '''
    with open(filename, 'w', encoding='utf-8') as file:
        for line in phone_dict:
            file.write(";".join(line))

def redactNote(filename, findParameter):
    '''Функция изменения заметки
    '''
    findRow = findNote(filename, findParameter)
    if len(findRow) != 0: 
        headerNewNote = input(f'Введите новый заголовок > ')
        flag = False # Проверка наличия искомого абонента
        noteBook = readAllNotes(filename)[1:]
        for line in noteBook:
            if findParameter in line:
                flag = True
                line[2] = headerNewNote
                print(*line)
            if flag:
                writeNote(filename, noteBook)
                print('\nизменения внесены !', end='')
                return
    print("Вы ввели неверное значение!", end='')

def deleteNote(filename, find_param):
    '''Функция удаления заметки из файла
    '''
    phone_book = readAllNotes(filename)[1:]
    for line in phone_book:
        if find_param in line:
            print(*line)
            phone_book.remove(line)
    writeNote(filename, phone_book)

filename = './notes_Python/notes.csv'
fields = ["ID", "Дата_время", "Заголовок", "Тело_заметки\n\n"]
go_to_function(showMenu())
os.system('clear')
print("Работа программы завершена")