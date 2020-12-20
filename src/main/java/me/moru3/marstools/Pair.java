package me.moru3.marstools;

public class Pair<T, U> {
    private final T first;
    private final U second;
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    public Pair<U, T> reverse() {
        return new Pair<U, T>(second, first);
    }
}
