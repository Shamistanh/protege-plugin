package edu.stanford.bmir.protege.summarizer.figures;

import java.awt.*;

public class Circle extends Shape {

  public Circle(Boolean isFilled, Color color, int width, int height){
    super(color,width,height,isFilled);
    setName("circle");
  }
}
