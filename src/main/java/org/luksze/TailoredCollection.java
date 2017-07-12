package org.luksze;

import java.util.ArrayList;
import java.util.List;

public class TailoredCollection {

    private final ArrayList<Integer> integers;

    public TailoredCollection() {
        integers = new ArrayList<>();
    }

    public TailoredCollection(List<Integer> copy) {
        integers = new ArrayList<>(copy);
    }

    public void add(Integer integer) {
        integers.add(integer);
    }

    public void addAll(TailoredCollection other) {
        integers.addAll(other.integers);
    }

    @Override
    public String toString() {
        return integers.toString();
    }

    public TailoredCollection addAndReturn(Integer integer) {
        ArrayList<Integer> copy = new ArrayList<>(this.integers);
        copy.add(integer);
        return new TailoredCollection(copy);
    }

    public TailoredCollection addAllAndReturn(TailoredCollection other) {
        ArrayList<Integer> copy = new ArrayList<>(integers);
        copy.addAll(other.integers);
        return new TailoredCollection(copy);

    }

    public boolean containsAll(List<Integer> list) {
        return integers.containsAll(list);
    }
}
