package org.itrade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SemanticController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/crawler/analyst/thestreet/start", method = RequestMethod.GET)
    @ResponseBody
    public String startCrawlerTheStreet() {
        return "...";
    }

}
