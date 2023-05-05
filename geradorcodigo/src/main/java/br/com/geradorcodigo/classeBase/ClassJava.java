package br.com.geradorcodigo.classeBase;

import java.util.List;

public class ClassJava {
    private String nomeClasse;
    private List<Atributo> atributos;


    /*
    public ClassJava(String nomePackage, String nomeClasse, List<Atributo> atributos, List<String> metodos){
        this.nomePackage = nomePackage;
        this.nomeClasse = nomeClasse;
        this.atributos = atributos;
        this.metodos = metodos;
    }*/

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

}
