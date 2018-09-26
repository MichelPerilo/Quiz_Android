package br.ufrpe.msilva.mathquiz.model;

public class Questao {

    private String texto;
    private double respC;
    private double respI;

    public Questao (String texto, double respC, double respI){
        this.texto = texto;
        this.respC = respC;
        this.respI = respI;
    }

    public String getTexto() {
        return texto;
    }

    public double getRespC() {
        return respC;
    }

    public double getRespI() {
        return respI;
    }
}
