package edu.stanford.bmir.protege.examples.figures;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(Boolean isFilled, Color color, Point point, int width, int height){
        super(point,color,width,height,isFilled);
        setName("rectangle");
    }
}
