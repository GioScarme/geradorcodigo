package br.com.geradorcodigo.services;

import br.com.geradorcodigo.classeBase.Atributo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;

@Service
public class GeradorCodigoService {
    @Autowired
    private SpringTemplateEngine templateEngine;


    public String gerarCodigo(String nomeClasse, List<Atributo> atributos) {

        // Cria um contexto Thymeleaf e adiciona as variáveis de modelo necessárias
        Context context = new Context();
        context.setVariable("nomeClasse", nomeClasse);
        context.setVariable("atributos", atributos);


        // Processa o template e retorna o resultado como uma string
        return templateEngine.process("TemplateClasse", context);
    }
}
