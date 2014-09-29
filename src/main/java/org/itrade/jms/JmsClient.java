package org.itrade.jms;

import org.itrade.commons.jms.ITradeJmsMessage;
import org.itrade.commons.jms.ITradeMessageStatus;
import org.itrade.commons.jms.ITradeMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsClient {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessageToInjection(String content, ITradeMessageType type, String category) {
        logger.debug("Send message: {}", content);
        try {
            ITradeJmsMessage iTradeJmsMessage = new ITradeJmsMessage();
            iTradeJmsMessage.setCategory(category);
            iTradeJmsMessage.setType(type);
            iTradeJmsMessage.setContent(content);
            iTradeJmsMessage.setStatus(ITradeMessageStatus.PROCESSED);
            jmsTemplate.convertAndSend(iTradeJmsMessage);
        } catch (JmsException e) {
            logger.error("Error when sending event: " + content, e);
        }
    }
}
