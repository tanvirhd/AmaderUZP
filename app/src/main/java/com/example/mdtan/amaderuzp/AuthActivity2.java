package com.example.mdtan.amaderuzp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
/*import com.google.firebase.database.DataSnapshot;*/

import java.util.ArrayList;

public class AuthActivity2 extends AppCompatActivity {

    EditText et1,et2;
    Button btn_login2;
    private ListView mlistView;
    private ArrayList<String> muserlist=new ArrayList<>();

    Firebase mRef;

    Firebase rootRef;
    Firebase userRef;
    Firebase complainRef;
    String v1,v2;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);
        et1=(EditText)findViewById(R.id.email_et_xml);
        et2=(EditText)findViewById(R.id.pass_et_xml);
        btn_login2=(Button)findViewById(R.id.btn_login_xml);
      /*  mlistView=(ListView)findViewById(R.id.badboy_listview);*/

        firebaseAuth =FirebaseAuth.getInstance();


        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= et1.getText().toString();
                String password=et2.getText().toString();
                Task<AuthResult> authResultTask=firebaseAuth.signInWithEmailAndPassword(email,password);

                authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(AuthActivity2.this,"LogIn successfull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(AuthActivity2.this,ComplainListActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AuthActivity2.this,"LogIn faild", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


       /* mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Toast.makeText(AuthActivity2.this,"ok gese",Toast.LENGTH_SHORT).show();
                    String value=dataSnapshot.getValue(String.class);
                    muserlist.add(value);
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/



    }//end of oncreat

   /*@Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        v1=intent.getStringExtra("value1");
        v2=intent.getStringExtra("value2");

        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,muserlist);

        mlistView.setAdapter(adapter);

        rootRef=new Firebase("https://complaindetails-9fc69.firebaseio.com/");
        userRef=rootRef.child(v1);
        complainRef=userRef.child("Complain");
        mRef=complainRef.child(v2);


        //mRef=new Firebase("https://complaindetails-9fc69.firebaseio.com/Bghqjs0fx7ZIShpavZWhIhHYfF03/Complain/-LQj5zdIaGmVvIY5VAhD");


            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(AuthActivity2.this, "yo",Toast.LENGTH_SHORT).show();

                    muserlist.clear();
                    for(DataSnapshot data: dataSnapshot.getChildren()) {
                        String value=dataSnapshot.getValue(String.class);
                        muserlist.add(value);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });


    }*/
}
