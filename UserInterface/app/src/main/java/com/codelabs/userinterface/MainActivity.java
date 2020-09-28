package com.codelabs.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codelabs.userinterface.database.AppDatabase;
import com.codelabs.userinterface.entities.User;
import com.codelabs.userinterface.utility.AppExecutors;
import com.codelabs.userinterface.views.UserList;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private AppDatabase appDatabase;
    DatePickerDialog picker;
    private EditText name, phone, address, city, zip, email, bDay;
    private Spinner area, state;
    private String areaVal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initializeDatabase();


        //Birthday - date picker
//        bDay = findViewById(R.id.edit_birthday);
//        bDayInput.setInputType(InputType.TYPE_NULL);
        bDay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        bDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });


//        Spinner area = findViewById(R.id.spinner_area);
        area.setOnItemSelectedListener(this);
        List<String> areaList = new ArrayList<String>();
        areaList.add("Area");
        areaList.add("0161");
        areaList.add("020");
        areaList.add("0141");
        areaList.add("0117");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areaList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area.setAdapter(dataAdapter);

        //STATE - drop down
//        Spinner state = findViewById(R.id.spinner_state);
        state.setOnItemSelectedListener(this);
        List<String> stateList = new ArrayList<String>();
        stateList.add("State");
        stateList.add("Greater London");
        stateList.add("Cheshire");
        stateList.add("Kent");
        stateList.add("Worcestershire");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stateList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter2);

    }

    public void initViews() {
        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        area = findViewById(R.id.spinner_area);
        address = findViewById(R.id.edit_address);
        city = findViewById(R.id.edit_city);
        state = findViewById(R.id.spinner_state);
        zip = findViewById(R.id.edit_zip);
        email = findViewById(R.id.edit_email);
        bDay = findViewById(R.id.edit_birthday);

    }

    public void initializeDatabase() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    public void saveUser(View view) {
        final User user = new User(
                name.getText().toString(),
                phone.getText().toString(),
                area.getSelectedItem().toString(),
                address.getText().toString(),
                city.getText().toString(),
                state.getSelectedItem().toString(),
                zip.getText().toString(),
                email.getText().toString(),
                bDay.getText().toString()
        );

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao().insertUser(user);
            }
        });
        nextScreenOnDataSuccess();
    }

    private void nextScreenOnDataSuccess() {
        Intent intent = new Intent(MainActivity.this, UserList.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //areaVal = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
       //Toast.makeText(parent.getContext(), "Selected: " + areaVal, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}

