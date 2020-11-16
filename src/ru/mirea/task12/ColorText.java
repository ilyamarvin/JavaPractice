package ru.mirea.task12;

public enum ColorText {
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    BLUE ("\u001B[34m"),
    WHITE( "\u001B[37m"),
    GREEN( " \u001B[32m");

    private String colorType;


    ColorText(String colorType) {
        this.colorType = colorType;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }
}


