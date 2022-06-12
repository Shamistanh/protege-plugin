package edu.stanford.bmir.protege.summarizer.service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OntologyFileService {

    public void saveOntology(String path, OWLOntology o) throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException {
        OWLOntologyManager owlOntologyManager = OWLManager.createOWLOntologyManager();
        String[] pathSplit = path.split("/");
        File fileOut = new File(pathSplit[pathSplit.length - 1]);
        owlOntologyManager.saveOntology(o, new FunctionalSyntaxDocumentFormat(),
                new FileOutputStream(fileOut));


    }

}
