package br.com.geradorcodigo.controller;

import br.com.geradorcodigo.classeBase.ClassJava;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class GeradorCodigoController {

    private TemplateEngine templateEngine;

    @GetMapping("/gerador-codigo")
    public String geradorCodigo(Model model){
        // Crie uma instância de ClassJava com dados de exemplo
        ClassJava classJava = new ClassJava("com.exemplo", "Pessoa", Arrays.asList("nome",
                "idade"), Arrays.asList("getNome", "getIdade"));

        // Adicione os dados ao modelo
        model.addAttribute("classJava", classJava);

        // Retorne o nome do template Thymeleaf
        return "TemplateClasse";
    }
}
