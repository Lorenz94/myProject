package net.thumbtack.school.old.cars;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;
import net.thumbtack.school.old.colors.Colored;

public class Car implements Colored{
    private String model;
    private int weight;
    private int maxSpeed;
    private Color color;

    public Car(String model, int weight, int maxSpeed, Color color) throws ColorException {
        this.color = Color.colorNotNull(color);
        this.model = model;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
    }

    public Car(String model, int weight, int maxSpeed, String color) throws ColorException {
        this(model,weight,maxSpeed,Color.colorFromString(color));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        this.color = Color.colorNotNull(color);
    }

    @Override
    public void setColor(String string) throws ColorException {
        this.color = Color.colorFromString(string);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
