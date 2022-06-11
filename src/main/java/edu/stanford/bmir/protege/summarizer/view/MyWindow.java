package edu.stanford.bmir.protege.summarizer.view;

import edu.stanford.bmir.protege.summarizer.figures.Circle;
import edu.stanford.bmir.protege.summarizer.service.SummarizerService;
import org.protege.editor.owl.model.OWLModelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;


public class MyWindow extends JFrame implements ActionListener {
    private OWLModelManager owlModelManager;
    private SummarizerService summarizerService = new SummarizerService();
    private Boolean isClearClicked = true;
    private Boolean isShowFiguresClicked = false;
    private Boolean isShowSumClicked = false;
    private Boolean isWindowActive = false;
    private Boolean isRelationClicked = false;
    private JPanel jPanel;
    static JTextField textField;
    static JFrame f;

    public Boolean getWindowActive() {
        return isWindowActive;
    }

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
        JButton showButton = new JButton("Show Figures");
        showButton.addActionListener(this);
        JButton showRelationsButton = new JButton("Show Relations");
        showRelationsButton.addActionListener(this);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        JButton exitButton = new JButton("Minimize");
        exitButton.addActionListener(this);
        textField = new JTextField("Ontology");
        JButton submitButton = new JButton("Fetch");
        submitButton.addActionListener(this);

        rootCont.add(jPanel, BorderLayout.CENTER);
        gbc.gridx = 0;

        gbc.gridy = 0;
        jPanel.add(showButton, gbc);
        gbc.gridy = 1;
        jPanel.add(showRelationsButton, gbc);
        gbc.gridy = 2;
        jPanel.add(clearButton, gbc);
        gbc.gridy = 3;
        jPanel.add(exitButton, gbc);
        textField.setLocation(160,50);
        textField.setSize(700,20);
        this.add(textField);
        submitButton.setLocation(90,50);
        submitButton.setSize(60,20);
        this.add(submitButton);
        rootCont.add(jPanel, BorderLayout.CENTER);


    }

    private static final Logger log = LoggerFactory.getLogger(MyWindow.class);

    private final MainViewComponent mainViewComponent = new MainViewComponent();

    private void drawFigures() {

        if (!isClearClicked){
        if (isShowFiguresClicked) {
            int cnt = 0;
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
            Graphics gr = jPanel.getGraphics();
            int y = 200;
            int width = 50;
            int x = 40;
            for (String name : names) {

                if (cnt > 6){
                    y = y + 70;
                    x = 40;
                    cnt = 0;
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
                cnt++;
            }
        }
            if (isShowSumClicked) {
                showSummary();
            }else if (isRelationClicked){
                summarizerService.showSubclasses(owlModelManager.getActiveOntology(),this.getGraphics(), this.getHeight());
                isRelationClicked = false;
            }
        }
        log.info("Figure is drawn");
    }
    private void showSummary(){
        String data[][]={ {"Axiom Count",owlModelManager.getActiveOntology().getAxiomCount()+""},
                {"Logical Axiom Count",owlModelManager.getActiveOntology().getLogicalAxiomCount()+""},
                {"Nested Expression",owlModelManager.getActiveOntology().getNestedClassExpressions().iterator().next().toString()}};

        String column[]={"KEY","VALUE"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,400,400,300);
        jt.setSize(400,50);
        JScrollPane sp=new JScrollPane(jt);
        this.add(sp);
        jPanel.setLayout(jPanel.getLayout());
        jPanel.add(jt, BorderLayout.PAGE_END);
        jPanel.setVisible(true);
        if (isClearClicked){
            jPanel.setVisible(false);
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
                case "Fetch":
                    String path = textField.getText();
                    log.info("Fetched path " + path);
                    summarizerService.fetchOntology(path, owlModelManager, f);
                    break;
                case "Minimize":
                    reply = JOptionPane.showConfirmDialog(this, "Are you sure to Minimize?", "Sure?", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        this.dispose();
                    }
            }
        }

    }
}
