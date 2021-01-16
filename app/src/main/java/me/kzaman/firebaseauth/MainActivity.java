package me.kzaman.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button send_otp_button;
    TextInputEditText input_phone_number;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        send_otp_button = findViewById(R.id.continue_btn);
        input_phone_number = findViewById(R.id.input_phone_number);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Toast.makeText(getApplicationContext(), "You Are Already Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

        send_otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpCode();
            }
        });
    }

    private void sendOtpCode(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+8801716724245") // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this) // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String verifiedId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                Toast.makeText(getApplicationContext(), "OTP Successfully Send, and ID is: " + verifiedId, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
                                intent.putExtra("OTP_ID", verifiedId);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(getApplicationContext(), "OTP Verification Complete", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}