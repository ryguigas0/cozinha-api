package com.espm.guilherme.cozinhaapi.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backoffice")
public class BackofficeResource {

    @GetMapping("/cozinha")
    public String painelCozinha() {
        return "cozinha";
    }
}
