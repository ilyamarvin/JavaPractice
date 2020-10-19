package ru.mirea.task13;

public class TrapHouse {

    private int peopleCounter;

    public void addPeopleCounter(int peopleCounter) throws MyOwnException {
            if (peopleCounter < 0) {
                throw new MyOwnException();
            }
            this.peopleCounter += peopleCounter;
    }

    @Override
    public String toString() {
        return "TrapHouse{" +
                "peopleCounter=" + peopleCounter +
                '}';
    }
}
