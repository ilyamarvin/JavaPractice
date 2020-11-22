package ru.mirea.task21_22;

public interface ItemsStore {
    public String get(int id);

    public String addItem(Item item);

    public String editItem(int id, Item item);

    public String deleteItem(int id);
}
