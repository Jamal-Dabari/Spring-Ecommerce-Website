package com.ecommerce.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/hi")
  public String hello() {
    return "hello";
  }

}
