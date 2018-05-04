package com.example.android.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private WordDBHandler dbHandler;
    private TextView txt_resultadapter;
    private List<Word> wordList = new ArrayList<>();
    private Word word;

    FloatingActionButton floatingActionButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddWord.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        initComponent();
        initRecyclerView();
        cekDataRecyclerView();
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new WordDBHandler(MainActivity.this);
        wordList = dbHandler.getAllWord();
        recyclerAdapter = new RecyclerAdapter(wordList, getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }

    private void initRecyclerView() {
        txt_resultadapter = findViewById(R.id.masih_kosong);
    }

    private void cekDataRecyclerView() {
        if (recyclerAdapter.getItemCount() == 0) {
            txt_resultadapter.setVisibility(View.VISIBLE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


}