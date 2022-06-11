package edu.stanford.bmir.protege.summarizer.figures;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Figure {
    Circle,
    Ellipsis,
    Rectangle,
    Triangle;

    private static final List<Figure> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Figure randomFigure()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
