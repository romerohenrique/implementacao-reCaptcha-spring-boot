package com.login.reCaptchaImplementation.controller;

import com.login.reCaptchaImplementation.model.Cadastro;
import com.login.reCaptchaImplementation.model.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CadastroController {

    @Autowired
    RestTemplate restTemplate;

    List<Cadastro> cadastros = new ArrayList<>();

    @GetMapping("/cadastro")
    public String home() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public void  cadastra(Cadastro cadastro, @RequestParam(name = "g-recaptcha-response") String captchaResponse) {

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LeS16wmAAAAAEV3biEMwhaGM3skN4xBJ6rqN6I5&response=" + captchaResponse;

        RecaptchaResponse recaptcha = restTemplate.exchange(url + params, HttpMethod.POST, null, RecaptchaResponse.class).getBody();
        if (recaptcha.isSuccess()) {
            Long id = cadastros.size() + 1L;
            cadastros.add(new Cadastro(id, cadastro.getEmail(), cadastro.getSenha()));

            System.out.println("foi cadastrado o email: "+cadastro.getEmail());
        }
    }
}
