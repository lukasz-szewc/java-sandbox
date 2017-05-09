package org.luksze;


class YearOfLife {
    private int age;

    YearOfLife(int age) {
        this.age = age;
    }

    YearOfLife nextOne() {
        return new YearOfLife(age + 1);
    }

    @Override
    public String toString() {
        return "YearOfLife{" +
                "age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YearOfLife that = (YearOfLife) o;

        return age == that.age;
    }

    @Override
    public int hashCode() {
        return age;
    }
}
