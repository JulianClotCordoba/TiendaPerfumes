package com.perfumes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SobreNosotrosController {

    @GetMapping("/sobrenosotros")
    public String sobreNosotros() {
        return "sobrenosotros";
    }
}
