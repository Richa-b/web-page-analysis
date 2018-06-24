package com.challenge.analysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index() {
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        return "index";
    }
}
