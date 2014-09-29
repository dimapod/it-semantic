package org.itrade.gate;

import org.itrade.commons.jms.ITradeJmsMessage;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnalystManagerTest {
    @Test
    public void should_init_gate_app() {

        // open/read the application context file
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test-root-context.xml");

        // instantiate our spring dao object from the application context
        AnalystManager analystManager = (AnalystManager) ctx.getBean("analystManager");

        ITradeJmsMessage message = new ITradeJmsMessage();
        message.setContent("http://www.thestreet.com/story/12893498/1/analysts-actions-ratings-changes-on-dsw-graftech-international.html");
        analystManager.processAnalystMessage(message);
    }
}