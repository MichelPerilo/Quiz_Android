package br.ufrpe.msilva.mathquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufrpe.msilva.mathquiz.R;
import br.ufrpe.msilva.mathquiz.model.AnalisadorDeQuestao;
import br.ufrpe.msilva.mathquiz.model.Questao;
import br.ufrpe.msilva.mathquiz.model.QuestaoRepositorio;

public class QuizActivity extends AppCompatActivity {

    public static final String INDICE = "INDICE";
    private QuestaoRepositorio rep = new QuestaoRepositorio();
    private AnalisadorDeQuestao analisadorDeQuestao = new AnalisadorDeQuestao();
    private int indiceq = 0;
    private Button btnC;
    private Button btnI;
    private TextView textViewTextoPergunta;
    private Button btnN;
    private int pontos = 0;
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewTextoPergunta = findViewById(R.id.textoPergunta);
        btnN = findViewById(R.id.botaoPP);
        btnI = findViewById(R.id.botaoRespI);
        btnC = findViewById(R.id.botaoRespC);

        if (savedInstanceState != null){
            indiceq = savedInstanceState.getInt(INDICE);
        }
        showQuestao(indiceq);

        View.OnClickListener listenerResp = new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String reposta = ((Button)v).getText().toString();
                String mensagem;

                Questao qest = rep.getListaQuestoes().get(indiceq);

                if (analisadorDeQuestao.isCorreto(qest, Double.valueOf(reposta))){
                    ((Button)v).setBackgroundColor(-16711936);
                    ((Button)v).setTextColor(-1);
                    ((Button)v).setActivated(false);
                    mensagem = "Correto!";
                    pontos++;
                }else{
                    mensagem = "Incorreto!";
                    ((Button)v).setBackgroundColor(-65536);
                    ((Button)v).setTextColor(-1);
                    ((Button)v).setActivated(false);
                }
                Toast.makeText(QuizActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener listenerProxima = new View.OnClickListener(){

            @Override
            public void onClick(View v){

                incrementa(indiceq);



                Questao q = rep.getListaQuestoes().get(indiceq);

                showQuestao(indiceq);

            }
        };

        btnC.setOnClickListener(listenerResp);
        btnI.setOnClickListener(listenerResp);
        btnN.setOnClickListener(listenerProxima);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(INDICE, indiceq);
    }

    private void showQuestao(int indiceq){
        Questao q = rep.getListaQuestoes().get(indiceq);
        textViewTextoPergunta.setText(q.getTexto());
        btnC.setTextColor(-16777216);
        btnC.setBackgroundColor( -7829368);

        btnI.setTextColor(-16777216);
        btnI.setBackgroundColor( -7829368);

        Random gerador = new Random();

        Button[] bts = new Button[2];
        bts[gerador.nextInt(2)] = btnC;
        if (bts[0] != null){
            bts[1] = btnI;
        }else{
            bts[0] = btnI;
        }

        bts[0].setText(Double.toString(q.getRespC()));
        bts[1].setText(Double.toString(q.getRespI()));

    }

    private void incrementa(int indiceq){
        if (indiceq == rep.getListaQuestoes().size()-1) {
            alerta_fim();
        }else{
            this.indiceq++;
        }
    }

    private void alerta_fim(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FIM!");
        builder.setMessage("Você acertou "+this.pontos+" questões.");


        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(QuizActivity.this, QuizActivity.class);
                startActivity(in);
            }
        });
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(QuizActivity.this, InicioActivity.class);
                startActivity(in);
            }
        });

        alerta = builder.create();
        alerta.show();

    }


}
