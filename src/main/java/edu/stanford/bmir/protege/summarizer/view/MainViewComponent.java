package edu.stanford.bmir.protege.summarizer.view;

import java.awt.*;

import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainViewComponent extends AbstractOWLViewComponent{

    private static final Logger log = LoggerFactory.getLogger(MainViewComponent.class);

    private Metrics metricsComponent;


    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout());
        metricsComponent = new Metrics(getOWLModelManager());
        add(metricsComponent);
        log.info("Example View Component initialized");

    }

	@Override
	protected void disposeOWLView() {
		metricsComponent.dispose();
	}

}
