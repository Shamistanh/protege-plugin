package edu.stanford.bmir.protege.examples.view;

import edu.stanford.bmir.protege.examples.figures.Circle;
import org.protege.editor.owl.model.OWLModelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import static edu.stanford.bmir.protege.examples.view.GeneratorService.randomColor;
import static edu.stanford.bmir.protege.examples.view.GeneratorService.randomPoint;

public class MyWindow extends JFrame implements ActionListener {
    private OWLModelManager owlModelManager;
    private Boolean isClearClicked = true;
    private JPanel jPanel;

    public MyWindow(OWLModelManager owlModelManager) {
        this.owlModelManager = owlModelManager;
        init();

    }

    private void init() {

        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        Container rootCont = this.getContentPane();
        jPanel = new JPanel();
        JButton showButton = new JButton("Show");
        showButton.addActionListener(this);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        rootCont.add(jPanel, BorderLayout.CENTER);
        gbc.gridx = 0;

        gbc.gridy = 0;
        jPanel.add(showButton, gbc);
        gbc.gridy = 1;
        jPanel.add(clearButton, gbc);
        gbc.gridy = 2;
        jPanel.add(exitButton, gbc);
        rootCont.add(jPanel, BorderLayout.CENTER);

    }

    private static final Logger log = LoggerFactory.getLogger(MyWindow.class);

    private final ExampleViewComponent exampleViewComponent = new ExampleViewComponent();

    private void drawFigures() {
        List<String> names = owlModelManager.getActiveOntology()
                .getClassesInSignature()
                .stream().map(Object::toString).map(s -> {
                    String word = s.split("#")[1];
                    return word.substring(0, word.length() - 1);
                }).collect(Collectors.toList());
        Graphics gr = jPanel.getGraphics();
        for (String name : names) {
            log.info(name);
            Circle shape = GeneratorService.randomCircle();
            gr.setColor(shape.getColor());
            gr.drawString(name, shape.getP().getX(), shape.getP().getY());
            gr.drawOval(shape.getP().getX(), shape.getP().getY(), shape.getWidth(), shape.getHeight());
            if (shape.isFilled()) {
                gr.fillOval(shape.getP().getX(), shape.getP().getY(), shape.getWidth(), shape.getHeight());
            }
        }
        log.info("Figure is drawn");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!isClearClicked) {
            drawFigures();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand().trim()) {
                case "Show":
                    int reply = JOptionPane.showConfirmDialog(this, "Figure implementation will be shown up. Are you sure?", "Be Careful!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        drawFigures();
                    }
                    break;
                case "Clear":
                    log.info("Clear is clicked");
                    isClearClicked = true;
                    repaint();
                    break;
                case "Exit":
                    reply = JOptionPane.showConfirmDialog(this, "Are you sure to leave?", "Be Careful!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }

            }
        }

    }

    public static Circle randomCircle() {
        return new Circle((int) (Math.random() * 1.5) != 0, randomColor(), randomPoint(), (int) (Math.random() * 50), (int) (Math.random() * 50));
    }
}
