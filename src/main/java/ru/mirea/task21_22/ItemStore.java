package ru.mirea.task21_22;

import java.util.List;

public interface ItemStore {
    List<Item> getAll();

    Item get(int id);

    boolean addItem(Item item);

    boolean editItem(int id, Item item);

    boolean deleteItem(int id);
}
