package br.ufrpe.msilva.mathquiz.model;

import java.util.ArrayList;
import java.util.List;

public class QuestaoRepositorio {


    public List<Questao> getListaQuestoes(){

        ArrayList<Questao> questoes = new ArrayList();

        questoes.add(new Questao("A raiz quadrada de 676 é igual a:", 26, 23));
        questoes.add(new Questao("A raiz quadrada de 121 é igual a:", 11, 7));
        questoes.add(new Questao("A raiz quadrada de 441 é igual a:", 21, 14));


        return questoes;
    }


}
