package me.moru3.marstools;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;

public class ContentsList<T> extends ArrayList<T> {

    public ContentsList<T> filter(@NotNull Function<T, Boolean> filter) {
        ContentsList<T> temp = new ContentsList<>();
        this.forEach(value -> { if(filter.apply(value)) { temp.add(value); } });
        return temp;
    }

    public ContentsList<Object> map(@NotNull Function<T, Object> map) {
        ContentsList<Object> temp = new ContentsList<>();
        this.forEach(value -> temp.add(map.apply(value)));
        return temp;
    }

    public <P> ContentsList<P> toArray(@NotNull Class<P> type) {
        ContentsList<P> temp = new ContentsList<>();
        this.forEach(value -> temp.add((type).cast(value)));
        return temp;
    }

    public void forEach(@NotNull ObjIntConsumer<T> action) {
        int index = 0;
        for(T value : this) { action.accept(value, index);index++; }
    }

    public T first() {
        return this.get(0);
    }

    public T last() {
        return this.get(this.size()-1);
    }

    public void run(Consumer<T> consumer) {
        this.forEach(consumer);
    }

    public ContentsList<T> filterNotNull() {
        ContentsList<T> temp = new ContentsList<>();
        this.forEach(value -> { if(value!=null) { temp.add(value); } });
        return temp;
    }
}
