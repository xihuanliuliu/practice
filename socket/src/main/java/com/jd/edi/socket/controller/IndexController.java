package com.jd.edi.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {

    @GetMapping(value = "/toIndex")
    public String toIndexPage() {
        return "index";
    }

    @GetMapping(value = "/")
    public String toIndexPageDirectly() {
        return "index";
    }


}
