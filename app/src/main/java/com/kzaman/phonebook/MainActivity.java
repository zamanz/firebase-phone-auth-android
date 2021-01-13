package com.kzaman.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Button send_otp_button;
    TextInputEditText input_phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_otp_button = findViewById(R.id.send_otp_button);
        input_phone_number = findViewById(R.id.input_phone_number);

        send_otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(input_phone_number.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Phone Number Can't be Empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    String phone_number = input_phone_number.getText().toString().trim();
//                    Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
//                    intent.putExtra("phone", phone_number);
//                    startActivity(intent);
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                }

                String phone_number = input_phone_number.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
                intent.putExtra("phone", phone_number);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}