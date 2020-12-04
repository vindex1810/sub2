package com.example.pokmon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pokmon.domain.pokemon;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ListView listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listagem = findViewById(R.id.listagem);
        iniciarFirebase();
        atualizarLista();
    }

    public void registrar(View view){
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);
    }

    private void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference("ProvaP2");
    }

    public void atualizarLista(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<pokemon> pokemons = new ArrayList<pokemon>();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    pokemon pokemon = child.getValue(pokemon.class);
                    pokemons.add(pokemon);
                }

                ArrayAdapter<pokemon> adapter = new ArrayAdapter<pokemon>(MainActivity.this,
                        android.R.layout.simple_list_item_1, pokemons);
                listagem.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Error", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}