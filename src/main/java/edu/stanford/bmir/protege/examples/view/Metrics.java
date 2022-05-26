package edu.stanford.bmir.protege.examples.view;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Metrics extends JPanel {

    private static final Logger log = LoggerFactory.getLogger(Metrics.class);

    private JButton showButton = new JButton("Show");

    private JLabel textComponent = new JLabel("It is the summary of the ontology");
    private JLabel textComponentForOntology = new JLabel("It is the summary of the ontology");

    private OWLModelManager modelManager;


    private ActionListener showAction = e -> showClasses();

    private OWLModelManagerListener modelListener = event -> {
        if (event.getType() == EventType.ACTIVE_ONTOLOGY_CHANGED) {
            showClasses();
        }
    };

    public Metrics(OWLModelManager modelManager) {
        this.modelManager = modelManager;
        showClasses();

        modelManager.addListener(modelListener);
        showButton.addActionListener(showAction);

        add(textComponent);
        add(textComponentForOntology);
        add(showButton);
    }

    public Metrics() {

    }


    public void dispose() {
        modelManager.removeListener(modelListener);
        showButton.removeActionListener(showAction);
    }

    private void showClasses() {
        List<String> names = modelManager.getActiveOntology()
                .getClassesInSignature()
                .stream().map(Object::toString).map(s -> {
                    String word = s.split("#")[1];
                    return word.substring(0, word.length() - 1);
                }).collect(Collectors.toList());
        textComponent.setText("Classes = " + names);

        textComponentForOntology.setText("Owl ontology strategy name : " + modelManager.getActiveOntologiesStrategy().getName());
        textComponentForOntology.setVerticalTextPosition(SwingConstants.CENTER);
    }
}
