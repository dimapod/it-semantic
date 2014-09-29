package org.itrade.semantic;

import org.itrade.commons.jms.ITradeJmsMessage;
import org.itrade.gate.AnalystManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AnalystManager analystManager;

    public void onMessage(ITradeJmsMessage message) {
        logger.debug("Message received: " + message);
        String category = message.getCategory();

        switch (category) {
            case "ANALYST":
                analystManager.processAnalystMessage(message);
                break;

            default:
                logger.warn("Unknown message category: '{}'", message.getCategory());
                break;
        }
    }
}
