package me.moru3.marstools;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ContentsMap<K, V> extends HashMap<K, V> {
    public ContentsMap<K, V> filter(BiFunction<K, V, Boolean> biFunction) {
        ContentsMap<K, V> temp = new ContentsMap<>();
        this.forEach((key, value) -> { if(biFunction.apply(key, value)) { temp.put(key, value); } });
        return temp;
    }

    public void run(BiConsumer<K, V> biConsumer) {
        this.forEach(biConsumer);
    }
}
