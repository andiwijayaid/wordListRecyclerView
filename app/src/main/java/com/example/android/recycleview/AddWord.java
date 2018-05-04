package com.example.android.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddWord extends AppCompatActivity {

    EditText wordEditText;
    Button addButton;

    private WordDBHandler dbHandler;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        dbHandler = new WordDBHandler(this);
        initComponents();
    }

    private void initComponents() {

        wordEditText = findViewById(R.id.word_edit_text);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidation();
            }
        });
    }

    private void formValidation() {
        String form_word = wordEditText.getText().toString();

        if (form_word.isEmpty()) {
            wordEditText.setError("please fill this form");
            wordEditText.requestFocus();
        } else {
            dbHandler.tambahWord(new Word(form_word));
            List<Word> wordList = dbHandler.getAllWord();
            recyclerAdapter = new RecyclerAdapter(wordList, getApplicationContext());
            recyclerAdapter.notifyDataSetChanged();

            wordEditText.getText().clear();
            Toast.makeText(AddWord.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}