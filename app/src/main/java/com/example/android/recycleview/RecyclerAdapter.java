package com.example.android.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Word> wordList = new ArrayList<>();
    static int lastCheckedPos = -1;
    private Context context;
    Word word;

    public RecyclerAdapter (List<Word> wordList, Context context) {
        this.wordList = wordList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.wordText.setText(wordList.get(position).wordItem);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wordList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemChanged(position, wordList.size());
                WordDBHandler dbHandler = new WordDBHandler(context);
                dbHandler.hapusDataWord(new Word(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}