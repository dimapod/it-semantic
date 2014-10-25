package org.itrade.gate;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.Factory;
import gate.creole.ResourceInstantiationException;
import org.itrade.commons.jms.ITradeJmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnalystManager {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ITradeDocumentProcessor iTradeDocumentProcessor;

    public void processAnalystMessage(ITradeJmsMessage message) {
        logger.debug("ANALYST: {}", message);

        List<Document> documents;
        try {
            List<URL> urls = extractUrls(message);
            documents = createDocuments(urls);
        } catch (ResourceInstantiationException e) {
            throw new GateException("Error when creating GATE documents", e);
        } catch (MalformedURLException e) {
            throw new GateException("Error when init URLs", e);
        }

        try {
            iTradeDocumentProcessor.processDocuments(documents);
        } catch (gate.util.GateException e) {
            throw new GateException("Error when processing GATE document", e);
        }

        for (Document document: documents) {
            logger.info("-----------------------------------------------");
            logger.info("URL: {}", document.getSourceUrl().toString());
            AnnotationSet annotations = document.getAnnotations();
            logger.info("ANNOTATIONS: {}", annotations.toString());
        }
    }

    private List<URL> extractUrls(ITradeJmsMessage message) throws MalformedURLException {
        String content = message.getContent();
        String[] urlsArr = content.split(";");
        List<String> urlStrings = Arrays.asList(urlsArr);

        List<URL> urls = new ArrayList<>();
        for (String urlString: urlStrings) {
            urls.add(new URL(urlString));
        }

        return urls;
    }

    private List<Document> createDocuments(List<URL> urls) throws ResourceInstantiationException {
        List<Document> documents = new ArrayList<>();
        for (URL url : urls) {
            documents.add(Factory.newDocument(url));
        }
        return documents;
    }
}
