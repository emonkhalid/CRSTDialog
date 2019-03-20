package com.example.crstdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    private String selectedCourse = "";
    private ArrayList<String>courses;
    CheckBox cb1;
    RadioGroup rdg;
    private Spinner countrySpinner;
    private Calendar calendar;
    private int year,month,day,hour,minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization of Calendar
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        //ArrayList courses to store selected checkbox items
        courses = new ArrayList<>();


        RadioGroup rdg = findViewById(R.id.gender);
        RadioButton rb = findViewById(R.id.maleRadioButton);
        rb.setChecked(true);
        //Method for radio Group
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                String gender = rb.getText().toString();
                Log.e("Gender:",gender);
            }
        });

        countrySpinner = findViewById(R.id.spinner);
        //ArrayAdaper to set ArrayList countries values inside spinner view
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item,getCountries());
        countrySpinner.setAdapter(adapter);
        //Method for spinner view
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Method for checkbox view
    public void selectCourse(View view){

        CheckBox cb = (CheckBox) view;
        boolean status = cb.isChecked();
        selectedCourse = cb.getText().toString();

        switch (view.getId()){
            case R.id.cbJava:
                if(status){
                    courses.add(selectedCourse);
                }else{
                    courses.remove(selectedCourse);
                }
            break;

            case R.id.cbAnd:
                if(status){
                    courses.add(selectedCourse);
                }else{
                    courses.remove(selectedCourse);
                }
            break;

            case R.id.cbPhp:
                if(status){
                    courses.add(selectedCourse);
                }else{
                    courses.remove(selectedCourse);
                }
                break;
        }
        //Display courses item from checkbox inside log view
        for (String s: courses){
            Log.e("CheckBoxName",s);
        }

    }

    //Arraylist return type method that return countries to display it inside spinner.
    public ArrayList<String>getCountries(){
        ArrayList<String>countries = new ArrayList<>();
        countries.add("Bangladesh");
        countries.add("India");
        countries.add("Pakisthan");
        countries.add("Bhutan");
        countries.add("Nepal");
        countries.add("Maldivs");
        return countries;

    }


    //This is for date picker
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String userDate = sdf.format(calendar.getTime());
            ((Button)findViewById(R.id.selectDateButton)).setText(userDate);
        }
    };
    //This method for date picker
    public void selectDate(View view){
        DatePickerDialog datePickerDialog  = new DatePickerDialog(this,dateListener,year,month,day);
        datePickerDialog.show();
    }

    //This is for time picker
    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(year,month,day,hourOfDay,minute);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            String userTime = sdf.format(calendar.getTime());
            ((Button)findViewById(R.id.selectTimeButton)).setText(userTime);


        }
    };
    //This method for time picker
    public void selectTime(View view){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,timeListener,hour,minute,true);
        timePickerDialog.show();
    }


}
