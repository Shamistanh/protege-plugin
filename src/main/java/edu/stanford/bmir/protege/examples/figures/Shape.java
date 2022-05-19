package edu.stanford.bmir.protege.examples.figures;

import java.awt.*;

public abstract class Shape {
    String name;
    Point p;
    Color color;
    int width;
    int height;
    boolean isFilled;

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                ", p=" + p +
                ", color=" + color +
                ", width=" + width +
                ", height=" + height +
                ", isFilled=" + isFilled +
                '}';
    }

    public Shape(){

    }

    public Shape(Point p, Color color, int width, int height, boolean isFilled) {
        this.p = p;
        this.color = color;
        this.width = width;
        this.height = height;
        this.isFilled = isFilled;
    }

    void setName(String name){
        this.name = name;
    }

    public Point getP() {
        return p;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFilled() {
        return isFilled;
    }
}
