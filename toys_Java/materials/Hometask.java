package toys_Java.materials;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Hometask {
    public static void main(String[] args) {
        
        Set<Notebook> set = new HashSet<>(); // каталог ноутбуков
        fillSet(set); // заполнение каталога
        Map<Integer,Notebook> criteriaMap = new TreeMap<>(); // справочник критериев выборки ноутбуков
        inputFindCriteria(criteriaMap); // опрос критериев от пользователя
        Set<Notebook> needNotebooks = new HashSet<>(); // каталог отобранных ноутбуков
        findNotebooks(set, criteriaMap, needNotebooks); // формирование каталога
        
        if(needNotebooks.size() == 0) {// Вывод результата в консоль
            System.out.println("\n\nУказанным вами параметрам не соответствует ни один ноутбук");
        }else {
            System.out.println("\n\nПо указанным вами параметрам подходят следующие ноутбуки:");
            for (Notebook catalogPosition: (Set<Notebook>) needNotebooks) { 
                System.out.println(catalogPosition);
            }
        }
        System.out.println();
    }

    static void fillSet(Set<Notebook> set) {
        Notebook notebook1 = new Notebook(2,250,"linux","white");
        Notebook notebook2 = new Notebook(3,500,"linux","black");
        Notebook notebook3 = new Notebook(4,750,"windows","silver");
        Notebook notebook4 = new Notebook(8,800,"windows","black");
        Notebook notebook5 = new Notebook(6,500,"linux","silver");
        Notebook notebook6 = new Notebook(12,1200,"mac-os","pink");
        Notebook notebook7 = new Notebook(10,2000,"mac-os","silver");
        set.add(notebook1);
        set.add(notebook2);
        set.add(notebook3);
        set.add(notebook4);
        set.add(notebook5);
        set.add(notebook6);
        set.add(notebook7);
    }

    static void inputFindCriteria(Map<Integer,Notebook> criteriaMap) {

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

    static void findNotebooks(Set<Notebook> set, Map<Integer,Notebook> criteriaMap, Set<Notebook> needNotebooks) {
        for (Notebook catalogPosition: (Set<Notebook>) set) {
            boolean critRam = catalogPosition.getRam() >= criteriaMap.get(1).getRam();
            boolean critHdd = catalogPosition.getHdd() >= criteriaMap.get(2).getHdd();
            boolean critOs = criteriaMap.get(3).getOs().equals("none") || catalogPosition.getOs().equals(criteriaMap.get(3).getOs());
            boolean critColor = criteriaMap.get(4).getColor().equals("none") || catalogPosition.getColor().equals(criteriaMap.get(4).getColor());
            if (critRam & critHdd & critOs & critColor) {
                needNotebooks.add(catalogPosition);
            }
        }
    }
}
