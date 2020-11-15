package com.example.mdtan.amaderuzp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComplainListActivity extends AppCompatActivity {

    ListView listView;
    List<Complain> complains;
    DatabaseReference refComplain = FirebaseDatabase.getInstance().getReference("complain");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);

        listView = (ListView)findViewById(R.id.list_complain_xml);

        complains = new ArrayList<>();

        refComplain.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complains.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Complain complain=ds.getValue(Complain.class);
                    complains.add(complain);
                }
                ComplainAdapter complainAdapter=new ComplainAdapter(complains,ComplainListActivity.this);
                listView.setAdapter(complainAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Complain complain=complains.get(position);
                delete(complain.getId());
                Toast.makeText(ComplainListActivity.this, "Complain Deleted Successfully.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void delete (String id){
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("complain").child(id);
        ref.removeValue();
    }
}
