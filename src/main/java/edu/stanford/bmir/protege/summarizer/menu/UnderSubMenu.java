package edu.stanford.bmir.protege.summarizer.menu;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.*;

import edu.stanford.bmir.protege.summarizer.service.OntologyFileService;
import edu.stanford.bmir.protege.summarizer.view.Metrics;
import org.protege.editor.owl.ui.action.ProtegeOWLAction;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UnderSubMenu extends ProtegeOWLAction {
	private static final Logger log = LoggerFactory.getLogger(Metrics.class);
	OntologyFileService ontologyFileService = new OntologyFileService();
	static JFrame f;

	public void initialise() throws Exception {
	}

	public void dispose() throws Exception {
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			OWLOntology activeOntology = getOWLModelManager().getActiveOntology();
			log.info("Command " + arg0.getActionCommand());
			if (arg0.getActionCommand().equals("Save")){
				saveOntology(activeOntology);
			}else {
				StringBuilder s = new StringBuilder();
				for(OWLAxiom axiom : activeOntology.getAxioms()){
					if (axiom.toString().split("#").length==2){
						s.append(axiom.toString().split("#")[1]).append("\n");
					}
				}
				JOptionPane.showMessageDialog(f, "Axioms are following \n " + s);
			}

		}catch (Exception e){
			f=new JFrame();
			JOptionPane.showMessageDialog(f, "We are facing s following problem \n " + e.getMessage());
			log.error("Following error occurred while saving active ontology " + e.getMessage());
		}

	}

	private void saveOntology(OWLOntology o) {
		try {
			String path = "/ontology_ " + new Date();
			ontologyFileService.saveOntology(path, o);
			JOptionPane.showMessageDialog(getOWLWorkspace(), "The active ontology is saved.");
		}catch (Exception exception){
			f=new JFrame();
			JOptionPane.showMessageDialog(f, "We are facing problem while fetching ontology");
		}
	}
}
