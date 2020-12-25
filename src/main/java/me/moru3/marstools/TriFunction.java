package me.moru3.marstools;

public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
