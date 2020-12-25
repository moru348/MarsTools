package me.moru3.marstools;

public interface ForFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}
