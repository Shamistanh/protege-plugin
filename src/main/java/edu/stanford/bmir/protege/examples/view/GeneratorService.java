package edu.stanford.bmir.protege.examples.view;



import edu.stanford.bmir.protege.examples.figures.*;
import edu.stanford.bmir.protege.examples.figures.Point;
import edu.stanford.bmir.protege.examples.figures.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GeneratorService extends JFrame {


    public static Color randomColor() {
        Random rnd = new Random();
        float r = rnd.nextFloat();
        float g = rnd.nextFloat();
        float b = rnd.nextFloat();
        return new Color(r, g, b);
    }


    public static Point randomPoint() {
        return new Point((int) (Math.random() * 500), (int) (Math.random() * 500));

    }

    public int randomLenght() {
        return (int) (Math.random() * 50);

    }

    public Rectangle randomRectangle() {
        return new Rectangle((int) (Math.random() * 1.5) != 0, randomColor(), randomPoint(), (int) (Math.random() * 50), (int) (Math.random() * 50));
    }

    public static Circle randomCircle() {
        return new Circle((int) (Math.random() * 1.5) != 0, randomColor(), randomPoint(), (int) (Math.random() * 50), (int) (Math.random() * 50));
    }


}
