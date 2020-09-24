package com.codelabs.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private EditText emailVal;
    private TextView valMesage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        emailVal = findViewById(R.id.email_input);
        valMesage = findViewById(R.id.val_msg);
        isEmpty();
    }

    public void isEmpty() {
        String email = emailVal.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(emailPattern) && email!= null){
        }else{
            emailVal.setError("Please change the data");
        }
    }
}