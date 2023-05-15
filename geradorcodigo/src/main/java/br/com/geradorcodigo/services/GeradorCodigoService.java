package br.com.geradorcodigo.services;

import br.com.geradorcodigo.entity.Atributo;
import br.com.geradorcodigo.entity.ClassJava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class GeradorCodigoService {
    @Autowired
    private SpringTemplateEngine templateEngine;


    public String gerarCodigoTemplate(ClassJava classJava) {

        // Cria um contexto Thymeleaf e adiciona as variáveis de modelo necessárias
        Context context = new Context();
        context.setVariable("classJava", classJava);

        // Processa o template e retorna o resultado como uma string
        return templateEngine.process("TemplateClasse", context);
    }

    public String gerarClasseEntity(ClassJava classJava) {
        StringBuilder sb = new StringBuilder();

        // Class definition
        sb.append("@Entity\n");
        sb.append("@Table(name = \"").append(classJava.getNomeClasse()).append("\")\n");
        sb.append("public class ").append(classJava.getNomeClasse()).append(" {\n\n");

        // Fields
        for (Atributo field : classJava.getAtributos()) {
            sb.append("    @Column(name = ").append(field.getNome()).append(")").append(";\n");
            sb.append("    private ").append(field.getTipo()).append(" ").append(field.getNome()).append(";\n");
        }
        sb.append("\n");

        // Constructors
        sb.append("    public ").append(classJava.getNomeClasse()).append("() {\n");
        sb.append("        // Default constructor\n");
        sb.append("    }\n\n");

        // Getters and setters
        for (Atributo field : classJava.getAtributos()) {
            String fieldName = field.getNome();
            String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            sb.append("    public ").append(field.getTipo()).append(" get").append(capitalizedFieldName).append("() {\n");
            sb.append("        return ").append(fieldName).append(";\n");
            sb.append("    }\n\n");

            sb.append("    public void set").append(capitalizedFieldName).append("(").append(field.getTipo()).append(" ").append(fieldName).append(") {\n");
            sb.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            sb.append("    }\n\n");
        }

        // End of class definition
        sb.append("}\n");

        return sb.toString();
    }

    public String gerarClasseRepository(ClassJava classJava) {
        StringBuilder sb = new StringBuilder();

        // Adiciona a annotation @Repository
        sb.append("\n@Repository\n");

        // Gera a classe
        sb.append("public interface ").append(classJava.getNomeClasse()).append("Repository extends JpaRepository<").append(classJava.getNomeClasse()).append(", Long> {\n\n");

        // Adiciona os métodos
        sb.append("    // Adicione aqui seus métodos personalizados\n");

        //sb.append("    @Override\n");
        sb.append("    public List<").append(classJava.getNomeClasse()).append("> findAll() {\n");
        sb.append("        String query = \"SELECT * FROM ").append(classJava.getNomeClasse()).append("\";\n");
        sb.append("        TypedQuery<").append(classJava.getNomeClasse()).append("> query = entityManager.createQuery(query, ").append(classJava.getNomeClasse()).append(".class);\n");
        sb.append("        return query.getResultList();\n");
        sb.append("    }\n");

        //sb.append("    @Override\n");
        sb.append("    public Optional<").append(classJava.getNomeClasse()).append("> findById(Long id) {\n");
        sb.append("        return repository.findById(id);\n");
        sb.append("    }\n");

        //sb.append("    @Override\n");
        sb.append("    public ").append(classJava.getNomeClasse()).append(" save(").append(classJava.getNomeClasse()).append(" ").append(classJava.getNomeClasse().toLowerCase()).append(") {\n");
        sb.append("        return ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.save(").append(classJava.getNomeClasse().toLowerCase()).append(");\n");
        sb.append("    }\n");

        sb.append("    public void delete(Long id) {\n");
        sb.append("        ").append(classJava.getNomeClasse()).append(" ").append("id").append(" = findById(id);\n");
        sb.append("        if (").append("id").append(" != null) {\n");
        sb.append("            entityManager.remove(").append("id").append(");\n");
        sb.append("        }\n");
        sb.append("    }\n");

        // Fecha a classe
        sb.append("}\n");

        return sb.toString();
    }

    public String gerarClasseController(ClassJava classJava) {
        StringBuilder sb = new StringBuilder();

        // Adiciona o cabeçalho da classe

        sb.append("@Controller\n");
        sb.append("@RequestMapping(\"/").append(classJava.getNomeClasse().toLowerCase()).append("\")\n");
        sb.append("public class ").append(classJava.getNomeClasse()).append("Controller {\n\n");

        // Adiciona a dependência do repository
        sb.append("    @Autowired\n");
        sb.append("    private ").append(classJava.getNomeClasse()).append("Repository ").append(classJava.getNomeClasse().toLowerCase()).append("Repository;\n\n");

        // Adiciona o método GET para buscar todas as entidades
        sb.append("    @GetMapping\n");
        sb.append("    public ResponseEntity<List<").append(classJava.getNomeClasse()).append(">> buscarTodos() {\n");
        sb.append("        List<").append(classJava.getNomeClasse()).append("> ").append(classJava.getNomeClasse().toLowerCase()).append("s = ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.findAll();\n");
        sb.append("        return new ResponseEntity<>(").append(classJava.getNomeClasse().toLowerCase()).append("s, HttpStatus.OK);\n");
        sb.append("    }\n\n");

        // Adiciona o método GET para buscar uma entidade por ID
        sb.append("    @GetMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(classJava.getNomeClasse()).append("> buscarPorId(@PathVariable Long id) {\n");
        sb.append("        ").append(classJava.getNomeClasse()).append(" ").append(classJava.getNomeClasse().toLowerCase()).append(" = ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.findById(id).orElse(null);\n");
        sb.append("        if (").append(classJava.getNomeClasse().toLowerCase()).append(" == null) {\n");
        sb.append("            return new ResponseEntity<>(HttpStatus.NOT_FOUND);\n");
        sb.append("        }\n");
        sb.append("        return new ResponseEntity<>(").append(classJava.getNomeClasse().toLowerCase()).append(", HttpStatus.OK);\n");
        sb.append("    }\n\n");

        // Adiciona o método POST para criar uma nova entidade
        sb.append("    @PostMapping\n");
        sb.append("    public ResponseEntity<").append(classJava.getNomeClasse()).append("> criar(@RequestBody ").append(classJava.getNomeClasse()).append(" ").append(classJava.getNomeClasse().toLowerCase()).append(") {\n");
        sb.append("        ").append(classJava.getNomeClasse()).append(" nova").append(classJava.getNomeClasse()).append(" = ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.save(").append(classJava.getNomeClasse().toLowerCase()).append(");\n");
        sb.append("        return new ResponseEntity<>(nova").append(classJava.getNomeClasse()).append(", HttpStatus.CREATED);\n");
        sb.append("    }\n\n");

        // Adiciona o método PUT para atualizar uma entidade existente
        sb.append("    @PutMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(classJava.getNomeClasse()).append("> atualizar").append(classJava.getNomeClasse()).append("(@PathVariable Long id, @RequestBody ").append(classJava.getNomeClasse()).append(" novo").append(classJava.getNomeClasse()).append(") {\n");
        sb.append("        Optional<").append(classJava.getNomeClasse()).append("> ").append(classJava.getNomeClasse().toLowerCase()).append(" = ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.findById(id);\n");
        sb.append("        if (").append(classJava.getNomeClasse().toLowerCase()).append(".isPresent()) {\n");
        sb.append("            ").append(classJava.getNomeClasse()).append(" ").append(classJava.getNomeClasse().toLowerCase()).append("Salvo = ").append(classJava.getNomeClasse().toLowerCase()).append(".get();\n");

        for (Atributo atributo : classJava.getAtributos()) {
            sb.append("            ").append("if (novo").append(classJava.getNomeClasse()).append(".").append(atributo.getNome()).append("() != null) {\n");
            sb.append("                ").append(classJava.getNomeClasse().toLowerCase()).append("Salvo.set").append(capitalizeFirstLetter(atributo.getNome())).append("(novo").append(classJava.getNomeClasse()).append(".").append(atributo.getNome()).append("());\n");
            sb.append("            }\n");
        }

        sb.append("            ").append(classJava.getNomeClasse()).append(" ").append(classJava.getNomeClasse().toLowerCase()).append("Atualizado = ").append(classJava.getNomeClasse().toLowerCase()).append("Repository.save(").append(classJava.getNomeClasse().toLowerCase()).append("Salvo);\n");
        sb.append("            return ResponseEntity.ok(").append(classJava.getNomeClasse().toLowerCase()).append("Atualizado);\n");
        sb.append("        }\n");
        sb.append("        return ResponseEntity.notFound().build();\n");
        sb.append("    }\n");

        return sb.toString();


    }
    public String gerarClasseService(ClassJava classJava) {
        StringBuilder sb = new StringBuilder();

        sb.append("@Service\n");
        sb.append("public class ").append(classJava.getNomeClasse()).append("Service {\n\n");
        sb.append("    @Autowired\n");
        sb.append("    private ").append(classJava.getNomeClasse()).append("Repository ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append("Repository;\n\n");
        sb.append("    public ").append(classJava.getNomeClasse()).append(" salvar").append(classJava.getNomeClasse()).append("(").append(classJava.getNomeClasse()).append(" ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append(") {\n");
        sb.append("        return ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append("Repository.save(").append(lowercaseFirstLetter(classJava.getNomeClasse())).append(");\n");
        sb.append("    }\n\n");
        sb.append("    public void excluir").append(classJava.getNomeClasse()).append("(Long id) {\n");
        sb.append("        ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append("Repository.deleteById(id);\n");
        sb.append("    }\n\n");
        sb.append("    public List<").append(classJava.getNomeClasse()).append("> buscarTodas").append(classJava.getNomeClasse()).append("s() {\n");
        sb.append("        return ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append("Repository.findAll();\n");
        sb.append("    }\n\n");
        sb.append("    public ").append(classJava.getNomeClasse()).append(" buscar").append(classJava.getNomeClasse()).append("PorId(Long id) {\n");
        sb.append("        return ").append(lowercaseFirstLetter(classJava.getNomeClasse())).append("Repository.findById(id).orElse(null);\n");
        sb.append("    }\n\n");
        sb.append("}\n");

        return sb.toString();
    }

    // Método usado para converter o primeiro caractere de uma string em minúsculo
    private String lowercaseFirstLetter(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    //Método usado para transformar a primeira letra de uma string em maiúscula
    public static String capitalizeFirstLetter(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }



}
