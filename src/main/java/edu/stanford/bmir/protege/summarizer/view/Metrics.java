package edu.stanford.bmir.protege.summarizer.view;

import edu.stanford.bmir.protege.summarizer.figures.Circle;
import edu.stanford.bmir.protege.summarizer.service.SummarizerService;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Metrics extends JPanel implements ActionListener {

    private static final Logger log = LoggerFactory.getLogger(Metrics.class);

    private JButton showButton = new JButton("Show");
    private SummarizerService summarizerService = new SummarizerService();

    private Boolean isClearClicked = true;
    private Boolean isShowFiguresClicked = false;
    private Boolean isShowSumClicked = false;
    private Boolean isRelationClicked = false;
    static JTextArea textField;
    static JFrame f;


    private JLabel textComponent = new JLabel("It is the summary of the ontology");
    private JLabel textComponentForOntology = new JLabel("It is the summary of the ontology");

    private OWLModelManager owlModelManager;


    private ActionListener showAction = e -> showClasses();

    private OWLModelManagerListener modelListener = event -> {
        if (event.getType() == EventType.ACTIVE_ONTOLOGY_CHANGED) {
            showClasses();
        }
    };

    public Metrics(OWLModelManager owlModelManager) {
        this.owlModelManager = owlModelManager;


        GridBagConstraints gbc = new GridBagConstraints();
        JButton showButton = new JButton("Show Figures");
        showButton.addActionListener(this);
        JButton showRelationsButton = new JButton("Show Relations");
        showRelationsButton.addActionListener(this);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        JButton maxButton = new JButton("Maximize");
        maxButton.addActionListener(this);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        textField = new JTextArea("Ontology");
        JButton submitButton = new JButton("Fetch");
        submitButton.addActionListener(this);

        gbc.gridx = 0;

        gbc.gridy = 0;
        this.add(showButton, gbc);
        gbc.gridy = 1;
        this.add(showRelationsButton, gbc);
        gbc.gridy = 2;
        this.add(clearButton, gbc);
        gbc.gridy = 3;
        this.add(exitButton, gbc);
        gbc.gridy = 4;
        this.add(maxButton, gbc);
        textField.setLocation(50,50);
        textField.setSize(100,20);
        this.add(textField);
        submitButton.setLocation(10,50);
        submitButton.setSize(30,20);
        this.add(submitButton);

    }

    public Metrics() {

    }


    public void dispose() {
        owlModelManager.removeListener(modelListener);
        showButton.removeActionListener(showAction);
    }

    private void showClasses() {
        owlModelManager.getActiveOntology();
        List<String> names = owlModelManager.getActiveOntology()
                .getClassesInSignature()
                .stream().map(Object::toString).map(s -> {
                    String word = s.split("#")[1];
                    return word.substring(0, word.length() - 1);
                }).collect(Collectors.toList());
        textComponent.setText("Classes = " + names);

        textComponentForOntology.setText("Owl ontology strategy name : " + owlModelManager.getActiveOntologiesStrategy().getName());
        textComponentForOntology.setVerticalTextPosition(SwingConstants.CENTER);
    }


    private void drawFigures() {
        if (!isClearClicked) {
            if (isShowFiguresClicked) {
                List<String> names = owlModelManager.getActiveOntology()
                        .getClassesInSignature()
                        .stream().map(Object::toString).map(s -> {
                            String word = s;
                            String[] split = s.split("#");
                            if (split.length==2){
                                word = split[1];
                                word = word.substring(0, word.length() - 1);
                            }
                            return word;
                        }).collect(Collectors.toList());
                Graphics gr = this.getGraphics();
                int y = 100;
                int width = 50;
                int x = 40;
                for (String name : names) {

                    if (this.getWidth() < x-10){
                        y = y + 70;
                        x = 40;
                    }
                    log.info(name);
                    Circle shape = GeneratorService.randomCircle();
                    gr.setColor(shape.getColor());
                    gr.drawString(name, x, y);
                    gr.drawOval(x,y, width, width);
                    if (shape.isFilled()) {
                        gr.fillOval(x, y,width, width);
                    }
                    x = x + 110;
                }
            }
            if (isShowSumClicked) {
                showSummary();
            }else if (isRelationClicked){
                summarizerService.showSubclasses(owlModelManager.getActiveOntology(),this.getGraphics(), this.getHeight());
                isRelationClicked = false;
            }
        }
        isClearClicked = true;
        log.info("Figure is drawn");
    }



    private void showSummary() {
        String data[][] = {{"Axiom Count", owlModelManager.getActiveOntology().getAxiomCount() + ""},
                {"Logical Axiom Count", owlModelManager.getActiveOntology().getLogicalAxiomCount() + ""},
                {"Nested Expression", owlModelManager.getActiveOntology().getNestedClassExpressions().iterator().next().toString()}};

        String column[] = {"KEY", "VALUE"};
        JTable jt = new JTable(data, column);
        jt.setBounds(30, 400, 400, 300);
        jt.setSize(400, 50);
        JScrollPane sp = new JScrollPane(jt);
        this.add(sp);
        this.setLayout(this.getLayout());
        this.add(jt, BorderLayout.PAGE_END);
        this.setVisible(true);
        if (isClearClicked) {
            this.setVisible(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFigures();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand().trim()) {
                case "Show Figures":
                    int reply = JOptionPane.showConfirmDialog(this, "Figure implementation will be shown up. Are you sure?", "Be Careful!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        isShowFiguresClicked = true;
                        isShowSumClicked = false;
                        isClearClicked = false;
                        isRelationClicked = false;
                        paint(this.getGraphics());
                    }
                    break;
                case "Show Relations":
                    isRelationClicked = true;
                    summarizerService.showSubclasses(owlModelManager.getActiveOntology(),this.getGraphics(), this.getHeight());
                    log.info("Relations is clicked");
                    break;

                case "Clear":
                    log.info("Clear is clicked");
                    isClearClicked = true;
                    repaint();
                    break;
                case "Maximize":
                    log.info("Maximize button is clicked");
                    MyWindow window = new MyWindow(owlModelManager);
                    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    window.setSize(900, 900);
                    window.setMinimumSize(new Dimension(150, 150));
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                    window.setResizable(false);
                    break;
                case "Fetch":
                    String path = textField.getText();
                    log.info("Fetched path " + path);
                    summarizerService.fetchOntology(path, owlModelManager, f);
                    break;

                case "Exit":
                    reply = JOptionPane.showConfirmDialog(this, "Are you sure to leave?", "Be Careful!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
            }
        }

    }
}
