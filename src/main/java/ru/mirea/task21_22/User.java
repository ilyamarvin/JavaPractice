package ru.mirea.task21_22;

public class User {
    public StringBuilder printChangeJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("1.Изменить локальный файл\n");
        sb.append("2.Изменить данные на сервере\n");
        sb.append("3.Выйти\n");
        sb.append("Выберите действие: ");
        return sb;
    }

    public StringBuilder printChangeLocalJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("Обработка локального файла\n");
        sb.append("Выберите\n");
        sb.append(printUserChange());
        return sb;
    }

    public StringBuilder printChangeHttpJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("Обработка данных на сервере\n");
        sb.append("Выберите\n");
        sb.append(printUserChange());
        return sb;
    }

    private StringBuilder printUserChange() {
        StringBuilder sb = new StringBuilder();
        sb.append("1.Получить список всех объектов\n");
        sb.append("2.Получить объект\n");
        sb.append("3.Добавить объект\n");
        sb.append("4.Изменить объект\n");
        sb.append("5.Удалить объект\n");
        sb.append("6.Выйти\n");
        sb.append("Выберите действие: ");
        return sb;
    }

    public StringBuilder printGetAllItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список всех объектов\n");
        return sb;
    }

    public StringBuilder printGetItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Введите id: ");
        return sb;
    }

    public StringBuilder printAddItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Введите данные объекта: ");
        return sb;
    }

    public StringBuilder printEditItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Введите id объекта и измененный объект: ");
        return sb;
    }

    public StringBuilder printDeleteItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("Введите id объекта, который хотите удалить: ");
        return sb;
    }

    public StringBuilder printError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ошибка!");
        return sb;
    }

    public StringBuilder printGetError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объекта с таким id не найдено!");
        return sb;
    }

    public StringBuilder printAddError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект не добавлен!");
        return sb;
    }

    public StringBuilder printEditError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект не был изменен!");
        return sb;
    }

    public StringBuilder printDeleteError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект не был удален!");
        return sb;
    }

    public StringBuilder printExit() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы вышли!");
        return sb;
    }

    public StringBuilder printAddSuccessful() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект успешно добавлен!");
        return sb;
    }

    public StringBuilder printEditSuccessful() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект был успешно изменен!");
        return sb;
    }

    public StringBuilder printDeleteSuccessful() {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект был успешно удален!");
        return sb;
    }

}