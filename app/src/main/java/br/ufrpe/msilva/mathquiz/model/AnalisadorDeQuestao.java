package br.ufrpe.msilva.mathquiz.model;

public class AnalisadorDeQuestao {

    public boolean isCorreto(Questao questao, double resposta){
        return questao.getRespC() == resposta;
    }
}
