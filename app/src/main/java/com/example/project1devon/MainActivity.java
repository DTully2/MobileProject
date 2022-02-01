package com.example.project1devon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static TextInputEditText time;
    static TextInputEditText date;
    static TextInputEditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=findViewById(R.id.outlinedTextField5);
        date=findViewById(R.id.outlinedTextField4);
        name=findViewById(R.id.outlinedTextField2);
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            time.setText(hourOfDay+":"+minute);
        }
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date.setText(day+"/"+month+"/"+year);

        }


    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onViewList(View v) {
        Intent intent = new Intent(this, ListView.class);
        startActivity(intent);
    }

    public void onSettings(View v) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onAddEvent(View v) {
        String eventToAdd = name.getText().toString()+"\nDate "+date.getText().toString() + "\nTime: "+time.getText().toString()+"_";
        Log.d("addEvent", eventToAdd);
        SharedPreferences settings = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        String eventStringList = settings.getString("events", "");
        eventStringList += eventToAdd;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("events", eventStringList);
        editor.apply();
        Toast toast = Toast.makeText(getApplicationContext(), "Event has been added", Toast.LENGTH_SHORT);
        toast.show();
        name.setText("");
        date.setText("");
        time.setText("");
    }
}