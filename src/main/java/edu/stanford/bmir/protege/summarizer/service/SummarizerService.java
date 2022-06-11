package edu.stanford.bmir.protege.summarizer.service;

import edu.stanford.bmir.protege.summarizer.view.GeneratorService;
import edu.stanford.bmir.protege.summarizer.view.Metrics;
import org.protege.editor.owl.model.OWLModelManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class SummarizerService {
    private static final Logger log = LoggerFactory.getLogger(Metrics.class);

    public void showSubclasses(OWLOntology owlOntology, Graphics gr, int windowHeight){
        int x = 50;
        int y = 100;
        int width = 80;
        int height = 80;
        for (final OWLSubClassOfAxiom subClasse : owlOntology.getAxioms(AxiomType.SUBCLASS_OF))
        {
            if (subClasse.getSuperClass() instanceof OWLClass
                    && subClasse.getSubClass() instanceof OWLClass)
            {
                String subclass = subClasse.getSubClass().toString().split("#")[1];
                subclass = subclass.substring(0, subclass.length()-1);
                String superclass = subClasse.getSuperClass().toString().split("#")[1];
                superclass = superclass.substring(0, superclass.length()-1);
                drawFigure(gr,subclass,superclass, x,y,width,height);

                y = y + 110;
                if (y > windowHeight-100){
                    y = 100;
                    x = x + 300;
                }

                log.info(subClasse.getSubClass()
                        + " extends " + subClasse.getSuperClass());
            }
        }
    }
    public void fetchOntology(String path, OWLModelManager owlModelManager, JFrame f) {
        try {
            OWLOntologyManager man = OWLManager.createOWLOntologyManager();
            IRI webOntology = IRI.create(path);
            OWLOntology o = man.loadOntology(webOntology);
            owlModelManager.setActiveOntology(o);
        }catch (Exception ex){

            log.error("Following error occurred " + ex.getMessage());
            f=new JFrame();
            JOptionPane.showMessageDialog(f, "We are facing problem while fetching ontology");
            ex.printStackTrace();
        }
    }



    private void drawFigure(Graphics gr, String subclass, String superclass, int x, int y, int width, int height){
        gr.setColor(GeneratorService.randomColor());
        gr.drawString(subclass, x, y);
        gr.drawOval(x,y + 10, width, height);
        gr.fillOval(x,y + 10, width, height);

        gr.setColor(GeneratorService.randomColor());
        gr.drawString(superclass, x + 110, y);
        gr.drawRect(x + 110,y + 10, width, height);
        gr.fillRect(x + 110,y + 10 , width, height);
    }

}
