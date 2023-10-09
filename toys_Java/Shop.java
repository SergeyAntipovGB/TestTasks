package toys_Java;

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
        // Scanner in = new Scanner(System.in);
        // toysAdder();//временно, до включения 1 пункта меню
        System.out.println(toysMap);
        // listForRandom(); //временно, до включения 3 пункта меню

        // for (int i = 0; i < 100; i++) {
        //     System.out.printf("rnd[%d] -> %d\t", i, rnd.getVariant(i));
        // }
        
        int choiseNumber = 0;
        while (choiseNumber != 5) {
            choiseNumber = menuQuestions();
            if (choiseNumber == 1) {
                toysAdder();
            }else if (choiseNumber == 2) {
                weightRedact();
            }else if (choiseNumber == 3) {
                listForRandom();
                randomId();//изменить процедуру на void
            }else if (choiseNumber == 4) {
            }else if (choiseNumber == 5) {
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
        // in.close();    
    }

    /** Опрашивает пользователя для выбора пункта меню
     * @return int число - пункт меню
     */
    static int menuQuestions() {
        System.out.printf("добавить новые игрушки    - 1\n"
                            + "изменить вес игрушек      - 2\n"
                            + "провести розыгрыш игрушек - 3\n"
                            + "выдать игрушку получателю - 4\n"
                            + "завершить программу       - 5\n"
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
        /* временный ввод данных */
        toysMap.put(1, new Toys(1, "мишка", 60, 100));
        toysMap.put(2, new Toys(2, "мяч", 20, 60));
        toysMap.put(3, new Toys(3, "барабан", 20, 40));
        /* временный ввод данных */

        Scanner in = new Scanner(System.in);
        int count;
        if (toysMap.isEmpty()) count = 1;
        else count = toysMap.size() + 1;
        System.out.print("Введите название игрушки > ");
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

    /** Изменение веса (вероятности выпадения игрушки)
     */
    static void weightRedact() {
        System.out.println("вероятность выпадения игрушек следующая:");
        for (Toys itemToys : toysMap.values()) {
            System.out.printf("'%s' - %d%%\n", itemToys.getName(), itemToys.getWeight());
            
        }
        System.out.println("Как вы хотите изменить соотношение?\n");


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
     * игрушки в очередь 
     */
    static int randomId() {
        int drawingId = rnd.nextId();
        Toys tempToy = toysMap.get(drawingId);
        if (tempToy.getQuantity() == 0) {
            System.out.printf("в категории ''%s'' не осталось игрушек!\n", tempToy.getName());
        }else {
            tempToy.substractQuantity();
            toysMap.put(drawingId, tempToy);
            shopQueue.offer(drawingId);
        }
        return drawingId;
    }

    // static void menu() {

    //     System.out.println("Введите номера искомых критериев через пробел:"
    //                         + "добавить новые игрушки      - 1"
    //                         + "бъем ЖД (в Гбайтах)  - 2"
    //                         + "Операционная система - 3"
    //                         + "Цвет                 - 4\n");
    //     Scanner in = new Scanner(System.in);
    //     String[] inputCriteria = in.nextLine().split(" ");
    //     int[] arrayCriteria = new int[5];
    //     for (String s: inputCriteria) {
    //         int i = Integer.parseInt(s);
    //         arrayCriteria[i] = i;
    //     }
    //     Notebook criteria = new Notebook();
    //     for (int i = 1; i < arrayCriteria.length; i++) {
    //         if (arrayCriteria[i] == 1){
    //             System.out.print("Введите целое значение ОЗУ в Мбайтах > ");
    //             criteria.setRam(in.nextInt());
    //         }else if (arrayCriteria[i] == 2){
    //             System.out.print("Введите целое значение объема ЖД в Гбайтах > ");
    //             criteria.setHdd(in.nextInt());
    //         }else if (arrayCriteria[i] == 3){
    //             System.out.print("Введите ОС (windows,linux,mac-os) > ");
    //             criteria.setOs(in.next());
    //         }else if (arrayCriteria[i] == 4){
    //             System.out.print("Введите цвет (white,black,silver,pink) > ");
    //             criteria.setColor(in.next());
    //         }
    //         criteriaMap.put(i, criteria);
    //     }
    //     in.close();
    // }
}
