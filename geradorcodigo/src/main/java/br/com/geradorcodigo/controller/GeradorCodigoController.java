package br.com.geradorcodigo.controller;

import br.com.geradorcodigo.entity.Atributo;
import br.com.geradorcodigo.entity.ClassJava;
import br.com.geradorcodigo.services.GeradorCodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
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

    @PostMapping("/gerar-classe-entity")
    public ResponseEntity<String> gerarCodigoEntity(@RequestBody ClassJava classJava) {

        // Chama o serviço de geração de código para gerar o código-fonte da classe
        String codigo = geradorCodigoService.gerarClasseEntity(classJava);

        // Retorna o resultado como um arquivo de texto
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(codigo, headers, HttpStatus.OK);
    }

    @PostMapping("/gerar-classe-repository")
    public ResponseEntity<String> gerarCodigoRepository(@RequestBody ClassJava classJava) {

        // Chama o serviço de geração de código para gerar o código-fonte da classe
        String codigo = geradorCodigoService.gerarClasseRepository(classJava);

        // Retorna o resultado como um arquivo de texto
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(codigo, headers, HttpStatus.OK);
    }

    @PostMapping("/gerar-classe-controller")
    public ResponseEntity<String> gerarCodigoController(@RequestBody ClassJava classJava) {

        // Chama o serviço de geração de código para gerar o código-fonte da classe
        String codigo = geradorCodigoService.gerarClasseController(classJava);

        // Retorna o resultado como um arquivo de texto
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(codigo, headers, HttpStatus.OK);
    }

    @PostMapping("/gerar-classe-service")
    public ResponseEntity<String> gerarCodigoService(@RequestBody ClassJava classJava) {

        // Chama o serviço de geração de código para gerar o código-fonte da classe
        String codigo = geradorCodigoService.gerarClasseService(classJava);

        // Retorna o resultado como um arquivo de texto
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(codigo, headers, HttpStatus.OK);
    }


}
