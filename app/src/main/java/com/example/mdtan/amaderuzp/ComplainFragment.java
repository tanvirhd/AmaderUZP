package com.example.mdtan.amaderuzp;


import android.app.Activity;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ComplainFragment extends Fragment implements AdapterView.OnItemSelectedListener {



    EditText topic,details,name,address,paddress,email;
    String doptor;


    //int id=0;String userid;

    Spinner doptorsomuho_spinner;
    ArrayAdapter<CharSequence> adapter_doptorlist;
    //=======================================================
    private FirebaseAuth firebaseAuth;
    EditText numberET,varifivationET;
    Button btn_verify,btn_send_complain;
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    //=======================================================
    FirebaseUser firebaseUser;
    private Firebase fRootRef;

    DatabaseReference rootReference;
    DatabaseReference userReference;
    DatabaseReference complainReference=FirebaseDatabase.getInstance().getReference("complain");

    ArrayList<String> ComplainList = new ArrayList<>();

    ListView complainlistLV;
    Activity context;


    public ComplainFragment() {
        //default constractor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();

        View view;
        view=inflater.inflate(R.layout.fragment_complain_layout, container, false);

        doptorsomuho_spinner=(Spinner)view.findViewById(R.id.spinner_doptorsomuho_xml);
        adapter_doptorlist=ArrayAdapter.createFromResource(getContext(),R.array.DoptorSomuho,  android.R.layout.simple_spinner_item);
        adapter_doptorlist.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        doptorsomuho_spinner.setAdapter(adapter_doptorlist);
        doptorsomuho_spinner.setOnItemSelectedListener(this);

        topic=(EditText)view.findViewById(R.id.compalin_topic_xml);
        details=(EditText)view.findViewById(R.id.compalin_details_xml);
        name=(EditText)view.findViewById(R.id.compalin_name_xml);
        address=(EditText)view.findViewById(R.id.compalin_address_xml);
        paddress=(EditText)view.findViewById(R.id.compalin_paddress_xml);
        email=(EditText)view.findViewById(R.id.compalin_email_xml);


        //==========================================================================================
        firebaseAuth=FirebaseAuth.getInstance();
        numberET=(EditText)view.findViewById(R.id.compalin_phonenumber_xml);
        varifivationET=(EditText)view.findViewById(R.id.verificationcode_xml);
        btn_verify=(Button)view.findViewById(R.id.btn_verify_xml);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberET.setEnabled(false);
                btn_verify.setEnabled(false);

                String number=numberET.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,getActivity(),mCallBacks);

            }
        });

        mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getContext(),"Error in Verification",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                /*btn_verify.setText("Verify Code");*/
            }
        };
      //=============================================================================================

        firebaseUser=firebaseAuth.getCurrentUser();
        /*fRootRef=new Firebase("https://complaindetails-9fc69.firebaseio.com/");*/

        rootReference = FirebaseDatabase.getInstance().getReference();



        if(firebaseUser!= null){
            userReference = rootReference.child(firebaseUser.getUid());
            //complainReference = userReference.child("Complain");
        }

        btn_send_complain=(Button)view.findViewById(R.id.btn_send_complain_xml);
        btn_send_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                Toast.makeText(getContext(),"Data Added Successfully.",Toast.LENGTH_SHORT).show();

                FirebaseAuth.getInstance().signOut();
                //Toast.makeText(getContext(),"Log out Successfully !",Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }//end of onCreat


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(getContext(),((TextView)view).getText(),Toast.LENGTH_SHORT).show();
        doptor=((TextView)view).getText().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    /*btn_verify.setText("verified");*/
                    btn_verify.setEnabled(false);
                    btn_send_complain.setEnabled(true);

                    Toast.makeText(getContext(),"Varification completed sucessfully.",Toast.LENGTH_SHORT).show();

                } else {

                    //toast

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(getContext() ,"The verification code entered was invalid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void addData(){

        /*userid=String.valueOf(id);*/

        //doptor
        String id=complainReference.push().getKey();
        String Topic=topic.getText().toString();
        String Details=details.getText().toString();
        String Name=name.getText().toString();
        String Address=address.getText().toString();
        String PAddress=paddress.getText().toString();
        String Email=email.getText().toString();
        String Phn_Number=numberET.getText().toString();


        Complain complain=new Complain(
                id, doptor,Topic,Details,Name,Address,PAddress,Email,Phn_Number
        );

        complainReference.child(id).setValue(complain);


        /*Firebase childRef1=fRootRef.child("Complains");
        Firebase childRef=childRef1.child(userid);
        Firebase subChild;

        subChild=childRef.child("Name");
        subChild.setValue(Name);

        subChild=childRef.child("Phone Number");
        subChild.setValue(Phn_Number);

        subChild=childRef.child("Assigned Officer");
        subChild.setValue(doptor);

        subChild=childRef.child("Complain Topic");
        subChild.setValue(Topic);

        subChild=childRef.child("Complain Detailse");
        subChild.setValue(Details);

        subChild=childRef.child("Present Address");
        subChild.setValue(Address);

        subChild=childRef.child("Permanent Address");
        subChild.setValue(PAddress);

        subChild=childRef.child("Email");
        subChild.setValue(Email);

      id++;*/
    }

}
