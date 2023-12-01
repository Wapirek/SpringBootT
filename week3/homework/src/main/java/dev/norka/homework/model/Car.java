package dev.norka.homework.model;

import org.springframework.lang.NonNull;

public class Car {
    @NonNull
    private Long id;
    @NonNull
    private String mark;
    @NonNull
    private String model;
    @NonNull
    private String color;

    public Car(long id, @NonNull String mark, @NonNull String model, @NonNull String color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getMark() {
        return mark;
    }

    public void setMark(@NonNull String mark) {
        this.mark = mark;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getColor() {
        return color;
    }

    public void setColor(@NonNull String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
