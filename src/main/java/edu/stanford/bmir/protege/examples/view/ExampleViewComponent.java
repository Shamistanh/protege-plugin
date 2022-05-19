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

public class ExampleViewComponent extends AbstractOWLViewComponent{

    private static final Logger log = LoggerFactory.getLogger(ExampleViewComponent.class);

    private Metrics metricsComponent;


    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout());
        metricsComponent = new Metrics(getOWLModelManager());
        metricsComponent.setBackground(Color.green);
        add(metricsComponent, BorderLayout.CENTER);
        log.info("Example View Component initialized");

        MyWindow window = new MyWindow(getOWLModelManager());
        //window.setTitle("Another one");

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.setMinimumSize(new Dimension(150, 150));
        window.setLocationRelativeTo(null);
        window.setVisible(true);

//        window.setResizable(false);


        window.getContentPane().setBackground(Color.RED);
    }

	@Override
	protected void disposeOWLView() {
		metricsComponent.dispose();
	}



}
