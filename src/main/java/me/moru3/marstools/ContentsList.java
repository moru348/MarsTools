package me.moru3.marstools;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;

public class ContentsList<T> extends ArrayList<T> {

    public ContentsList(T[] list) {
        Collections.addAll(this, list);
    }

    public ContentsList(T value) {
        this.add(value);
    }

    public ContentsList() {}

    public ContentsList<T> filter(@NotNull Function<T, Boolean> filter) {
        ContentsList<T> temp = new ContentsList<>();
        this.forEach(value -> { if(filter.apply(value)) { temp.add(value); } });
        return temp;
    }

    public <P> ContentsList<P> toArray(@NotNull Class<P> type) {
        ContentsList<P> temp = new ContentsList<>();
        this.forEach(value -> temp.add((type).cast(value)));
        return temp;
    }

    public ContentsList<Object> map(@NotNull Function<T, Object> map) {
        ContentsList<Object> temp = new ContentsList<>();
        this.forEach(value -> temp.add(map.apply(value)));
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

    public ContentsMap<Integer, T> enumerate() {
        ContentsMap<Integer, T> temp = new ContentsMap<>();
        this.forEach((value, index) -> temp.put(index, value));
        return temp;
    }

    @SuppressWarnings("unchecked")
    public ContentsList<ContentsList<T>> divide(int count) {
        ContentsList<ContentsList<T>> temp = new ContentsList<>();
        int pageCount = (int) Math.ceil(this.size()/(double) count);
        for(int i = 1;i<=pageCount;i++) {
            temp.add(this.slice(i-1, (i*count)-1));
        }
        return temp;
    }

    public ContentsList<T> slice(int min, int max) {
        ContentsList<T> temp = new ContentsList<>();
        for(int i = min;i <= (this.size()>max?max:this.size()-1);i++) {
            temp.add(this.get(i));
        }
        return temp;
    }

    public ContentsList<T> slice(int min) {
        ContentsList<T> temp = new ContentsList<>();
        for(int i = min;i<=this.size()-1;i++) {
            temp.add(this.get(i));
        }
        return temp;
    }

    public ContentsList<T> slice(int min, int max, int until) {
        ContentsList<T> temp = new ContentsList<>();
        for(int i = min;i<=max;i++) {
            if(i==min) {temp.add(this.get(i));continue;}
            temp.add(this.get((i*(until+1))-1));
        }
        return temp;
    }

    public ContentsList<T> replace(T oldValue, T newValue) {
        ContentsList<T> temp = new ContentsList<>();
        this.forEach(i -> {
            if(oldValue==i) {
                temp.add(newValue);
                return;
            }
            temp.add(i);
        });
        return temp;
    }

    public <P> ContentsMap<T, P> coalesce(ContentsList<P> contentsList) {
        ContentsMap<T, P> temp = new ContentsMap<>();
        ContentsList<T> content = fillSpaceNull(this.size()-1);
        contentsList.forEach((value, index) -> temp.put(content.get(index), value));
        return temp;
    }

    public ContentsList<T> fillSpaceNull(int size) {
        ContentsList<T> temp = new ContentsList<>();
        for(int i = 0;i<=size;i++) {
            if(i<this.size()) {
                T temp2 = this.get(i);
                temp.add(temp2);
            } else {
                temp.add(null);
            }
        }
        return temp;
    }

    public T random() {
        Random rand = new Random();
        return this.get(rand.nextInt(this.size()));
    }
}
