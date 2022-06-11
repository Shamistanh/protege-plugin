package edu.stanford.bmir.protege.summarizer.figures;

import java.awt.*;

public abstract class Shape {
    String name;
    Color color;
    int width;
    int height;
    boolean isFilled;

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", width=" + width +
                ", height=" + height +
                ", isFilled=" + isFilled +
                '}';
    }

    public Shape(){

    }

    public Shape( Color color, int width, int height, boolean isFilled) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.isFilled = isFilled;
    }

    void setName(String name){
        this.name = name;
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
