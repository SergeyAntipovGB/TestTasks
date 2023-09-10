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

            pause()
        elif variant == 2:

            pause()
        elif variant == 3:

            pause()              
        elif variant == 4:

            pause()
        elif variant == 5:

            pause()
        variant = show_menu()
