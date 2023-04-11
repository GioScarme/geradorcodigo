package br.com.geradorcodigo.classeBase;

import java.util.List;

public class ClassJava {
    private String nomePackage;
    private String nomeClasse;
    private List<String> campos;
    private List<String> metodos;

    public ClassJava(String nomePackage, String nomeClasse, List<String> campos, List<String> metodos){
        this.nomePackage = nomePackage;
        this.nomeClasse = nomeClasse;
        this.campos = campos;
        this.metodos = metodos;
    }

    public String getNomePackage() {
        return nomePackage;
    }

    public void setNomePackage(String nomePackage) {
        this.nomePackage = nomePackage;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public List<String> getCampos() {
        return campos;
    }

    public void setCampos(List<String> campos) {
        this.campos = campos;
    }

    public List<String> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<String> metodos) {
        this.metodos = metodos;
    }
}
