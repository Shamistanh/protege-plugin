package edu.stanford.bmir.protege.examples.view;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class Metrics extends JPanel  {

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


    public void dispose() {
        modelManager.removeListener(modelListener);
        showButton.removeActionListener(showAction);
    }

    private void showClasses() {
        textComponent.setText("Classes = " + modelManager.getActiveOntology()
                .getClassesInSignature()
                .stream().map(Object::toString).map(s -> {
            String word = s.split("#")[1];
            return word.substring(0, word.length() - 1);
            }).collect(Collectors.toList()));

        textComponentForOntology.setText("Owl ontology strategy name : " + modelManager.getActiveOntologiesStrategy().getName());
        textComponentForOntology.setVerticalTextPosition(SwingConstants.CENTER);
    }
}
