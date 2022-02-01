package com.example.project1devon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onCancel(View view) {
        finish();
    }

    public void onViewList(View view) {
        finish();
        Intent intent = new Intent(this, ListView.class);
        startActivity(intent);
    }

    public void onClearAllRecords(View view) {
        SharedPreferences settings = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("events", "");
        editor.apply();
        Toast toast = Toast.makeText(getApplicationContext(), "Events removed", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onRemoveRecord(View view) {

        SharedPreferences settings = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        String eventStringList = settings.getString("events", "");
        SharedPreferences.Editor editor = settings.edit();
        String[] list = eventStringList.split("_");
        String eventRemoved = "";
        for (int i = 0; i < list.length-1; i++){
            eventRemoved += list[i] + "_";
        }
        editor.putString("events", eventRemoved);
        editor.apply();
        Toast toast = Toast.makeText(getApplicationContext(), "Event removed", Toast.LENGTH_SHORT);
        toast.show();
    }

}