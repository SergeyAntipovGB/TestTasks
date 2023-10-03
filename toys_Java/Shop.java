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
        toysAdder();
        System.out.println(toysMap);
        listForRandom(); 
        // for (int i = 0; i < 100; i++) {
        //     System.out.printf("rnd[%d] -> %d\t", i, rnd.getVariant(i));
        // }
        
        System.out.println();
        System.out.println(randomId());
        System.out.println(randomId());
        System.out.println(randomId());
        System.out.println(randomId());
        System.out.println(toysMap);
            
    }
    
    static void toysAdder() {
        /* временный ввод данных */
        toysMap.put(1, new Toys(1, "мишка", 60, 100));
        toysMap.put(2, new Toys(2, "мяч", 20, 60));
        toysMap.put(3, new Toys(3, "барабан", 20, 40));
        /* временный ввод данных */

        // int count;
        // if (toysMap.isEmpty()) count = 1;
        // else count = toysMap.size() + 1;
        
    }
    
    static void weightRedact() {
        for (Toys itemToys : toysMap.values()) {
            System.out.println("вероятность выпадения игрушек следующая:\n");
            System.out.printf("'%s' - %d%%\n", itemToys.getName(), itemToys.getWeight());
            System.out.println("Как вы хотите изменить соотношение?\n");

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

    static void menu() {

        System.out.println("Введите номера искомых критериев через пробел:");
        System.out.println("ОЗУ (в Мбайтах)      - 1");
        System.out.println("бъем ЖД (в Гбайтах)  - 2");
        System.out.println("Операционная система - 3");
        System.out.println("Цвет                 - 4\n");
        Scanner in = new Scanner(System.in);
        String[] inputCriteria = in.nextLine().split(" ");
        int[] arrayCriteria = new int[5];
        for (String s: inputCriteria) {
            int i = Integer.parseInt(s);
            arrayCriteria[i] = i;
        }
        Notebook criteria = new Notebook();
        for (int i = 1; i < arrayCriteria.length; i++) {
            if (arrayCriteria[i] == 1){
                System.out.print("Введите целое значение ОЗУ в Мбайтах > ");
                criteria.setRam(in.nextInt());
            }else if (arrayCriteria[i] == 2){
                System.out.print("Введите целое значение объема ЖД в Гбайтах > ");
                criteria.setHdd(in.nextInt());
            }else if (arrayCriteria[i] == 3){
                System.out.print("Введите ОС (windows,linux,mac-os) > ");
                criteria.setOs(in.next());
            }else if (arrayCriteria[i] == 4){
                System.out.print("Введите цвет (white,black,silver,pink) > ");
                criteria.setColor(in.next());
            }
            criteriaMap.put(i, criteria);
        }
        in.close();
    }
}
