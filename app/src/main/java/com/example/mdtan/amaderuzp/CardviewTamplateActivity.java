package com.example.mdtan.amaderuzp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CardviewTamplateActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_template_layout);

        Intent intent=getIntent();
        int position=intent.getExtras().getInt("position_value");

        fragmentManager=getSupportFragmentManager();

        if(findViewById(R.id.fragment_container)!=null) {

            if(savedInstanceState!=null){
                return;
            }

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            /*if(position==0){
                HistoryFragment historyFragment=new HistoryFragment();
                fragmentTransaction.add(R.id.fragment_container,historyFragment,null);
                fragmentTransaction.commit();*/
            if(position==0){
                UpozelaParishadFragment upozelaParishadFragment=new UpozelaParishadFragment();
                fragmentTransaction.add(R.id.fragment_container,upozelaParishadFragment,null);
                fragmentTransaction.commit();
            }else if(position==1){
                GovtMembersFragment govtMembersFragment=new GovtMembersFragment();
                fragmentTransaction.add(R.id.fragment_container,govtMembersFragment,null);
                fragmentTransaction.commit();
            }else if(position==2){
                ImportentLinksFragment importentLinksFragment=new ImportentLinksFragment();
                fragmentTransaction.add(R.id.fragment_container,importentLinksFragment,null);
                fragmentTransaction.commit();
            }else if(position==3){
                EducationFragment educationFragment=new EducationFragment();
                fragmentTransaction.add(R.id.fragment_container,educationFragment,null);
                fragmentTransaction.commit();
            }else if(position==4){
                ComplainFragment complainFragment=new ComplainFragment();
                fragmentTransaction.add(R.id.fragment_container,complainFragment,null);
                fragmentTransaction.commit();

            }else if(position==5){
                TraingFragment traingFragment=new TraingFragment();
                fragmentTransaction.add(R.id.fragment_container,traingFragment,null);
                fragmentTransaction.commit();

            }/*else if(position==7){
                RationFragment rationFragment=new RationFragment();
                fragmentTransaction.add(R.id.fragment_container,rationFragment,null);
                fragmentTransaction.commit();
            }*/
        }

    }
}
