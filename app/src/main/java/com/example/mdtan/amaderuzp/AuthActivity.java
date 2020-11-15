package com.example.mdtan.amaderuzp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText numberET,varifivationET;
    Button btn_verify;
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        firebaseAuth=FirebaseAuth.getInstance();
        numberET=(EditText)findViewById(R.id.phn_number_ET_xml);
        varifivationET=(EditText)findViewById(R.id.varifivation_ET_xml);
        btn_verify=(Button)findViewById(R.id.btn_signup_xml);


        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberET.setEnabled(false);
                btn_verify.setEnabled(false);

                String number=numberET.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,AuthActivity.this,mCallBacks);


            }
        });

        mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(AuthActivity.this,"Error in Verification",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                btn_verify.setText("Verify Code");

                // ...
            }
        };

    }//end of oncreat

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();
                            Intent intent1=new Intent(AuthActivity.this,MainActivity.class);
                            startActivity(intent1);
                            Toast.makeText(AuthActivity.this,"Singging up completed sucessfully.",Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(AuthActivity.this,"Error in login",Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(AuthActivity.this,"Already Signed Up.",Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(AuthActivity.this,MainActivity.class);
            startActivity(intent1);
            //finish();
        }
    }
}
