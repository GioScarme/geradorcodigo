package br.com.geradorcodigo.services;

import br.com.geradorcodigo.classeBase.Atributo;
import br.com.geradorcodigo.classeBase.ClassJava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;

@Service
public class GeradorCodigoService {
    @Autowired
    private SpringTemplateEngine templateEngine;


    public String gerarCodigo(ClassJava classJava) {

        // Cria um contexto Thymeleaf e adiciona as variáveis de modelo necessárias
        Context context = new Context();
        context.setVariable("classJava", classJava);

        // Processa o template e retorna o resultado como uma string
        return templateEngine.process("TemplateClasse", context);
    }
}
