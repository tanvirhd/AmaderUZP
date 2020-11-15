package com.example.mdtan.amaderuzp;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Complain_view_fragment extends Fragment {

    ListView listView;
    List<Complain> complains;
    DatabaseReference refComplain = FirebaseDatabase.getInstance().getReference("complain");
    private Activity context;


    public Complain_view_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complain_view_layout, container, false);
        listView = (ListView) view.findViewById(R.id.list_complain_xml);

        complains = new ArrayList<>();

        refComplain.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complains.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Complain complain=ds.getValue(Complain.class);
                    complains.add(complain);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

}
