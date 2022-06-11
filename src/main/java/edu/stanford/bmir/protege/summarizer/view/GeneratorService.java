package edu.stanford.bmir.protege.summarizer.view;



import edu.stanford.bmir.protege.summarizer.figures.*;

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

    public static Circle randomCircle() {
        return new Circle((int) (Math.random() * 1.5) != 0, randomColor(), (int) (Math.random() * 50), (int) (Math.random() * 50));
    }


}
