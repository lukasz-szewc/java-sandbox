package org.luksze;


import java.util.ArrayList;

public class TailoredCollection {

    private final ArrayList<Integer> integers;

    public TailoredCollection() {
        integers = new ArrayList<>();
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
}
