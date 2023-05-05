package br.com.geradorcodigo.controller;

import br.com.geradorcodigo.classeBase.Atributo;
import br.com.geradorcodigo.classeBase.ClassJava;
import br.com.geradorcodigo.services.GeradorCodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GeradorCodigoController {

    @Autowired
    private GeradorCodigoService geradorCodigoService;

    @GetMapping("/gerador-codigo")
    public String geradorCodigo(Model model){
        // Crie uma instância de ClassJava com dados de exemplo

        List<Atributo> atributoList = new ArrayList<>();
        Atributo atributo = new Atributo();
        Atributo atributo2 = new Atributo();

        atributo.setNome("nome");
        atributo.setTipo("String");

        atributo2.setNome("idade");
        atributo2.setTipo("Integer");

        atributoList.add(atributo);
        atributoList.add(atributo2);


        ClassJava classJava = new ClassJava();
        classJava.setNomeClasse("Pessoa");
        classJava.setAtributos(atributoList);

         //Adicione os dados ao modelo
        model.addAttribute("classJava", classJava);

        // Retorne o nome do template Thymeleaf
        return "TemplateClasse";
    }



    @PostMapping("/gerar-codigo")
    public ResponseEntity<String> gerarCodigo(@RequestBody ClassJava classJava) {

        // Chama o serviço de geração de código para gerar o código-fonte da classe
        String codigo = geradorCodigoService.gerarCodigo(classJava.getNomeClasse(), classJava.getAtributos());

        // Retorna o resultado como um arquivo de texto
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(codigo, headers, HttpStatus.OK);
    }

    /*
    @PostMapping("/gerar-classeJava")
    public String gerarClasseJava(@RequestBody ClassJava classJava){
        Context contexto = new Context();
        contexto.setVariable("nomeClasse", classJava.getNomeClasse());
        contexto.setVariable("atributos", classJava.getAtributos());
        String codigo = templateEngine.process("TemplateClasse", contexto);
        return codigo;
    }
    @PostMapping("/gerar-classe")
    public ResponseEntity<ByteArrayResource> gerarClasse(@RequestBody ClassJava classJava){
        // Implemente o código aqui para criar a classe com base no modelo de template e nos dados da classe recebidos como entrada.

        // Carrega o modelo de template da classe.
        String template = "TemplateClasse";

        // Define o contexto do Thymeleaf e adiciona os valores necessários.
        Context context = new Context();
        //context.setVariable("package", classJava.getNomePackage());
        context.setVariable("nomeClasse", classJava.getNomeClasse());
        context.setVariable("atributos", classJava.getAtributos());
        //context.setVariable("metodos", classJava.getMetodos());

        // Renderiza o template com base nos valores do contexto.
        String renderedTemplate = templateEngine.process(template, context);

        // Cria um recurso de array de bytes com o conteúdo do arquivo gerado.
        ByteArrayResource resource = new ByteArrayResource(renderedTemplate.getBytes());

        // Define o cabeçalho de resposta para indicar que o corpo da resposta é um arquivo de texto Java.
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=classe.java");

        // Retorna a resposta com o recurso da classe Java gerada no corpo.
        String mediaType = "text/plain";

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("text/plain"))
                .body(resource);
    }*/

}
