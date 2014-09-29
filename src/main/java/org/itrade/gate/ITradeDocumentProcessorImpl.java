package org.itrade.gate;

import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.LanguageAnalyser;
import gate.util.*;
import gate.util.GateException;

import java.util.List;
import java.util.stream.Collectors;

public class ITradeDocumentProcessorImpl implements ITradeDocumentProcessor {
    /**
     * The analyser used to process documents.
     */
    private LanguageAnalyser analyser;

    /**
     * Corpus used to contain the document being processed.
     */
    private Corpus corpus;

    public ITradeDocumentProcessorImpl() {
    }

    @Override
    public void processDocuments(List<Document> docs) throws GateException {
        if (corpus == null) {
            corpus = Factory.newCorpus("DocumentProcessor corpus");
        }
        try {
            corpus.addAll(docs.stream().collect(Collectors.toList()));
            analyser.setCorpus(corpus);
            analyser.execute();
        } finally {
            analyser.setCorpus(null);
            corpus.clear();
        }
    }

    /**
     * Set the controller used to process documents.
     */
    public void setAnalyser(LanguageAnalyser a) {
        this.analyser = a;
    }

    /**
     * Clean up resources.  Should be called when this processor is no longer
     * required.
     */
    public synchronized void cleanup() {
        Factory.deleteResource(analyser);
        if (corpus != null) {
            Factory.deleteResource(corpus);
        }
    }
}



