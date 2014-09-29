package org.itrade.gate;

import gate.Document;

import java.util.List;

public interface ITradeDocumentProcessor {
    public void processDocuments(List<Document> doc) throws gate.util.GateException;
}
