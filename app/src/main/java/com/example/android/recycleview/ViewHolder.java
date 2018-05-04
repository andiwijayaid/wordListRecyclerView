package com.example.android.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewHolder extends RecyclerView.ViewHolder {

    TextView wordText;
    Button deleteButton;
    private Button editButton;
    LinearLayout recyclerItemLayout;

    public ViewHolder(View itemView) {
        super(itemView);

        wordText = itemView.findViewById(R.id.wordText);
        deleteButton = itemView.findViewById(R.id.delete_button);
        editButton = itemView.findViewById(R.id.edit_button);
        recyclerItemLayout = itemView.findViewById(R.id.recyclerViewItem);
    }


}
