package com.example.project1devon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ListView extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    String[] events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView=findViewById(R.id.recyclerView);
        SharedPreferences settings = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        String eventStringList = settings.getString("events", "");
        if (eventStringList.equals("")){
            return;
        }
        events= eventStringList.split("_");
        adapter=new CustomAdapter(events,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onAddEvent(View view) {finish();
    }

    public void onSettings(View v) {
        finish();
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public class CustomAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        String[] data;
        Context context;

        public CustomAdapter(String[] data, Context context) {
            this.data = data;
            this.context = context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.textView.setText(data[position]);

        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            TextView textView;
            CardView cardView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view);
                cardView = itemView.findViewById(R.id.card_view);
            }
        }
    }
}