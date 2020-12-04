package com.example.pokmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pokmon.domain.pokemon;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    private Integer ID;
    private EditText nomePoke;
    private EditText pokedexNumber;
    private Button salvar;

    DatabaseReference databasePokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        databasePokemon = FirebaseDatabase.getInstance().getReference("ProvaP2");
        nomePoke = findViewById(R.id.nomePokemon);
        pokedexNumber = findViewById(R.id.dexNumber);
        salvar = findViewById(R.id.cadastrarPo);
        salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adicionarItem();
            }
        });
    }

    public void adicionarItem(){
        String nome = nomePoke.getText().toString().trim();
        Integer dex = Integer.valueOf(pokedexNumber.getText().toString().trim());
        String id;

        if (!TextUtils.isEmpty(nome)) {
            id = databasePokemon.push().getKey();
            pokemon poke = new pokemon();
            poke.setID(id);
            poke.setName(nome);
            poke.setPokedexNumber(dex);

            databasePokemon.child(id).setValue(poke);
        }
        super.onBackPressed();
    }
}