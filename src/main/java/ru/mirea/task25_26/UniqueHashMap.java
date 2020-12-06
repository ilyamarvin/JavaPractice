package ru.mirea.task25_26;

import java.util.ArrayList;
import java.util.Iterator;

public class UniqueHashMap<K, V> implements HashMapInterface<K, V> {
    private final ArrayList<ArrayList<Item<K, V>>> hashMap;
    private final int UniqueHashMapSize = 4;

    public UniqueHashMap() {
        hashMap = new ArrayList<>();
        for (int i = 0; i < UniqueHashMapSize; i++) {
            hashMap.add(new ArrayList<>());
        }
    }

    @Override
    public void add(K key, V value) {
        boolean bool = false;
        int index = key.hashCode() % hashMap.size();
        System.out.println("Proverka");
        System.out.println(index);
        System.out.println("Proverka end");
        if (hashMap.get(index).size() == 0) {
            hashMap.get(index).add(new Item<>(key, value));
        } else {
            for (int i = 0; i < hashMap.get(index).size(); i++) {
                if (hashMap.get(index).get(i).getKey().equals(key)) {
                    hashMap.get(index).set(i, new Item<>(key, value));
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                hashMap.get(index).add(new Item<>(key, value));
                System.out.println("Proverka");
                System.out.println(hashMap.get(index).add(new Item<>(key, value)));
                System.out.println("Proverka end");
            }
        }
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % hashMap.size();
        for (int i = 0; i < hashMap.get(index).size(); i++) {
            if (hashMap.get(index).get(i).getKey().equals(key)) {
                return hashMap.get(index).get(i).getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = key.hashCode() % hashMap.size();
        for (int i = 0; i < hashMap.get(index).size(); i++) {
            if (hashMap.get(index).get(i).getKey().equals(key)) {
                Item<K, V> item = new Item<>();
                item.setValue(hashMap.get(index).get(i).getValue());
                hashMap.get(index).remove(hashMap.get(index).get(i));
                return item.getValue();
            }
        }
        return null;
    }

    @Override
    public Iterator<V> iterator() {
        return (new Iterator<V>() {
            int currentIndexOfItem = 0, currentIndexOfArray = 0;

            @Override
            public boolean hasNext() {
                if (currentIndexOfArray == UniqueHashMapSize - 1)
                    return false;

                while (hashMap.get(currentIndexOfArray + 1).size() == 0) {
                    currentIndexOfArray++;
                    currentIndexOfItem = 0;
                    if (currentIndexOfArray == UniqueHashMapSize - 1)
                        return false;
                }

                if (currentIndexOfItem == hashMap.get(currentIndexOfArray).size()) {
                    currentIndexOfArray++;
                    currentIndexOfItem = 0;
                }
                return (currentIndexOfArray < UniqueHashMapSize) &&
                        (currentIndexOfItem < hashMap.get(currentIndexOfArray).size());
            }

            @Override
            public V next() {
                return hashMap.get(currentIndexOfArray).get(currentIndexOfItem++).getValue();
            }
        });
    }
}

