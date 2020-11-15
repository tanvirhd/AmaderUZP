package com.example.mdtan.amaderuzp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<HomeElementListClass> elementList;
    public static FragmentManager fragmentManager;

    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        //==================================================================================
        toolbar=(Toolbar)findViewById(R.id.toolbar_xml);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout_xml);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view_xml);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.adminpanel_menu_xml:
                        Intent intent2=new Intent(MainActivity.this,AuthActivity2.class);
                        startActivity(intent2);
                        break;
                    case R.id.logout_menu_xml:
                        firebaseAuth.signOut();
                        Toast.makeText(MainActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share1:
                        //Toast.makeText(MainActivity.this,"Option1 selected",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,ComplainListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.share2:
                        Toast.makeText(MainActivity.this,"Option2 selected",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share3:
                        Toast.makeText(MainActivity.this,"Option3 selected",Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


       //=================================================================================

        elementList=new ArrayList<>();
      /*  elementList.add(new HomeElementListClass(R.drawable.cityscape,getString(R.string.element1)));*/
        elementList.add(new HomeElementListClass(R.drawable.temple,getString(R.string.element2)));//উপজেলা পরিষদ
        elementList.add(new HomeElementListClass(R.drawable.man,"সংসদ সদস্য ও চেয়ারম্যান"));//সংসদ সদস্য
        elementList.add(new HomeElementListClass(R.drawable.www,getString(R.string.element6)));//ুরুত্বপূর্ন ওয়েবসাইট লিংক
        elementList.add(new HomeElementListClass(R.drawable.school,getString(R.string.element7)));
        elementList.add(new HomeElementListClass(R.drawable.conference,"অভিযোগ"));
        elementList.add(new HomeElementListClass(R.drawable.conference,"প্রশিক্ষণ সমূহ"));// প্রশিক্ষণ সমূহ
       /* elementList.add(new HomeElementListClass(R.drawable.conference,"ভাতা"));*/



        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_xml);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,elementList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recyclerViewAdapter);


    }//onCreat ends here


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
