package ru.mirea.task25_26;

public class Test {
    public static void main(String[] args) {
        UniqueHashMap<Integer, String> uniqueHashMap = new UniqueHashMap<>();
        uniqueHashMap.add(1, "firstKey");
        uniqueHashMap.add(2, "secondKey");
        uniqueHashMap.add(3, "thirdKey");
        uniqueHashMap.add(4, "fourthKey");
        uniqueHashMap.add(5, "fifthKey");

        System.out.println("'1' value =" + uniqueHashMap.get(1));
        System.out.println("'3' value =" + uniqueHashMap.get(3));
        System.out.println("'5' value =" + uniqueHashMap.get(5));
        System.out.println("'2' value =" + uniqueHashMap.get(2));
        System.out.println("'4' value =" + uniqueHashMap.get(4));

        uniqueHashMap.remove(5);
        uniqueHashMap.add(5, "sixthKey");
        System.out.println("'5' value =" + uniqueHashMap.get(5));

        for (String V : uniqueHashMap) {
            System.out.println(V);
        }

    }
}
