package br.ufrpe.msilva.mathquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrpe.msilva.mathquiz.R;

public class InicioActivity extends AppCompatActivity {

    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        entrar = findViewById(R.id.button);

        View.OnClickListener listenerEntrar = new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent in = new Intent(InicioActivity.this, QuizActivity.class);
                startActivity(in);

            }
        };


        entrar.setOnClickListener(listenerEntrar);


    }
}
