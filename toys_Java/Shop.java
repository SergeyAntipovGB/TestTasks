package toys_Java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Shop {
    static Map<Integer,Toys> toysMap = new HashMap<Integer,Toys>();
    static Queue<Integer> shopQueue = new LinkedList<Integer>();
    static Randomizer rnd = new Randomizer();

    public static void main(String[] args) {
        
        int choiseNumber = 0;
        while (choiseNumber != 6) {
            choiseNumber = menuQuestions();
            if (choiseNumber == 1) {
                toysAdder();
            }else if (choiseNumber == 2) {
                changeWeights();
            }else if (choiseNumber == 3) {
                listForRandom();
                randomId();
            }else if (choiseNumber == 4) {
                showQueue();
            }else if (choiseNumber == 5) {
                fileWrite();
            }else if (choiseNumber == 6) {
                System.out.println("Работа программы завершена!");
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Exception: Ошибка ожидания потока!");
                }
            }else {
                System.out.println("Введено не верное число, повторите!");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Exception: Ошибка ожидания потока!");
                }
            }
        }
        
        System.out.println(toysMap);
    }

    /** Опрашивает пользователя для выбора пункта меню
     * @return int число - пункт меню
     */
    static int menuQuestions() {
        System.out.printf("\nдобавить новые игрушки     - 1\n"
                            + "изменить вес игрушек       - 2\n"
                            + "провести розыгрыш игрушек  - 3\n"
                            + "показать очередь розыгрыша - 4\n"
                            + "выдать игрушку получателю  - 5\n"
                            + "завершить программу        - 6\n"
                            + "> Выберите необходимое действие и"
                            + " введите соответствующее число > ");
        Scanner in = new Scanner(System.in);
        int choiseNumber = 0;
        try {
            choiseNumber = in.nextInt();
        } catch (Exception e) {
            System.out.println("Exception: Ошибка ввода целого числа!");
        }
        return choiseNumber;
    }
    
    /** Добавление новой игрушки в перечень розыгрыша
     */
    static void toysAdder() {
        Scanner in = new Scanner(System.in);
        int count;
        if (toysMap.isEmpty()) count = 1;
        else count = toysMap.size() + 1;

        System.out.print("\nВведите название игрушки > ");
        String name = in.nextLine();

        System.out.print("Введите вес (вероятность в %) игрушки > ");
        int weight = 0;
        try {
            weight = in.nextInt();
        } catch (Exception e) {
            System.out.println("Exception: Ошибка ввода целого числа!");
        }
        if (countWeights(weight) != 100) {
            System.out.println("Сумма вероятностей всех игрушек должна быть равна 100! Измените вес игрушек!");
        }

        System.out.print("Введите количество игрушек > ");
        int quantity = 0;
        try {
            quantity = in.nextInt();
        } catch (Exception e) {
            System.out.println("Exception: Ошибка ввода целого числа!");
        }        

        toysMap.put(count, new Toys(count, name, weight, quantity));
        System.out.println("Запись о новых игрушках добавлена");
    }
    
    /** Суммирует вес (вероятности) всех игрушек и входной параметр
     * weight и возвращает сумму resultWeight
     * @param weight int
     * @return resultWeight int
     */
    static int countWeights(int weight) {
        int resultWeight = weight;
        for (Toys itemToys : toysMap.values()) {
            resultWeight += itemToys.getWeight();
        }
        return resultWeight;
    }

    /** Выводит текущие веса и просит пользователя ввести новый
     * вес (вероятность) каждой игрушки.
     */
    static void changeWeights() {
        Scanner in = new Scanner(System.in);
        int startWeight = countWeights(0);
        System.out.println("\nВероятность выпадения игрушек следующая:");
        for (Toys itemToys : toysMap.values()) {
            System.out.printf("'%s' - %d%%\n", itemToys.getName(), itemToys.getWeight());
        }
        System.out.printf("сумма равна = %d, а необходимо 100!\n", startWeight);
        int newWeight = 0;
        for (Toys itemToys : toysMap.values()) {
            System.out.printf("Введите вес %s > ", itemToys.getName());
            try {
                newWeight = in.nextInt();
            } catch (Exception e) {
                System.out.println("Exception: Ошибка ввода целого числа!");
            }
            itemToys.setWeight(newWeight);
        }
        if (countWeights(0) != 100) {
            System.out.println("Сумма вероятностей всех игрушек должна быть равна 100! Измените вес игрушек!");
        }
    }

    /** формирование списка вариантов для случайной генерации
     * с заданным шансом выпадения
     */
    static void listForRandom() {
        int count = 0;
        int limit = 0;
        for (Toys itemToys : toysMap.values()) {
            limit += itemToys.getWeight();
            for (int i = count; i < limit; i++) {
                rnd.setVariant(i, itemToys.getId());
            }
            count += itemToys.getWeight();
        }
    }
    
    /** проверка остатка игрушек и помещение случайной
     * игрушки в конец очереди 
     */
    static void randomId() {
        int drawingId = rnd.nextId();
        Toys tempToy = toysMap.get(drawingId);
        if (tempToy.getQuantity() == 0) {
            System.out.printf("\nв категории ''%s'' не осталось игрушек!\n", tempToy.getName());
        }else {
            tempToy.substractQuantity();
            toysMap.put(drawingId, tempToy);
            shopQueue.offer(drawingId);
            System.out.printf("\n%s помещена в очередь!", tempToy.getName());
        }
    }

    /** Выводит на экран очередь разыгранных игрушек
     */
    static void showQueue() {
        System.out.println("\nСписок разыгранных игрушек:");
        for (int item : shopQueue) {
            System.out.println(toysMap.get(item).getName());
        }
    }

    /** Записывает в файл выданные игрушки
     */
    static void fileWrite() {
        if (shopQueue.peek() == null) System.out.println("\nОчередь пустая!");
        else {
            try(FileWriter writer = new FileWriter("toys.txt", true)) {
                writer.write(toysMap.get(shopQueue.poll()).getName());
                writer.append('\n');
                writer.flush();
            }
            catch(IOException e){
                System.out.println("Ошибка записи в файл");
            }
            System.out.println("\nОчередная игрушка выдана победителю и занесена в файл");
        } 
    }

}
