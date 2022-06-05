package edu.stanford.bmir.protege.examples.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import edu.stanford.bmir.protege.examples.figures.Circle;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import static edu.stanford.bmir.protege.examples.view.GeneratorService.randomColor;
import static edu.stanford.bmir.protege.examples.view.GeneratorService.randomPoint;

public class ExampleViewComponent extends AbstractOWLViewComponent{

    private static final Logger log = LoggerFactory.getLogger(ExampleViewComponent.class);

    private Metrics metricsComponent;


    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout());
        metricsComponent = new Metrics(getOWLModelManager());
        add(metricsComponent, BorderLayout.CENTER);
        log.info("Example View Component initialized");
        if (metricsComponent.getMaxButton()){
        MyWindow window = new MyWindow(getOWLModelManager());

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.setMinimumSize(new Dimension(150, 150));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        }


    }

	@Override
	protected void disposeOWLView() {
		metricsComponent.dispose();
	}



    public static Circle randomCircle() {
        return new Circle((int) (Math.random() * 1.5) != 0, randomColor(), randomPoint(), (int) (Math.random() * 50), (int) (Math.random() * 50));
    }


}
