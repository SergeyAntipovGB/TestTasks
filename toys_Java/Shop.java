package toys_Java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
}
