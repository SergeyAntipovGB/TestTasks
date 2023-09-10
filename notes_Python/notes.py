import os

def show_menu():
    '''Функция меню
    '''
    os.system('clear')
    print("\nВыберите необходимое действие:\n"
          "1. Создать новую записку\n"
          "2. \n"
          "3. \n"
          "4. \n"
          "5. \n"
          "6. \n"
          "7. \n"
          "8. Завершить работу программы\n")
    choice = input('> ')
    if choice.isdigit(): return int(choice)
    return show_menu()
