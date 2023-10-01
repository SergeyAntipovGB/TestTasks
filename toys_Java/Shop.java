package toys_Java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Shop {
    public static void main(String[] args) {
        
        Map<Integer,Toys> toysMap = new HashMap<Integer,Toys>();
        toysMap.put(1, new Toys(1, "мишка", 60, 100));
        toysMap.put(2, new Toys(2, "мяч", 40, 60));
        toysMap.put(3, new Toys(3, "барабан", 40, 40));

        Queue<Integer> shopQueue = new LinkedList<Integer>();
        Randomizer rnd = new Randomizer();
        
        /** формирование списка вариантов для генерации с шансом */
        int count = 0;
        for (Toys itemToys : toysMap.values()) {
            for (int i = count; i < itemToys.getWeight(); i++) {
                rnd.setVariant(i, itemToys.getId());
            }
            count = itemToys.getWeight();
        }

        /** проверка остатка игрушек и помещение случайной игрушки
         * в очередь         */
        int drawingId = rnd.nextId();
        Toys tempToy = toysMap.get(drawingId);
        if (tempToy.getQuantity() == 0) {
            System.out.printf("в категории ''%s'' не осталось игрушек!\n", tempToy.getName());
        }else {
            tempToy.substractQuantity();
            toysMap.put(drawingId, tempToy);
            shopQueue.offer(drawingId);
        }

        System.out.println();
    }
}
