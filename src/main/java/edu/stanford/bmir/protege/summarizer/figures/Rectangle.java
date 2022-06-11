package edu.stanford.bmir.protege.summarizer.figures;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(Boolean isFilled, Color color, int width, int height){
        super(color,width,height,isFilled);
        setName("rectangle");
    }
}
