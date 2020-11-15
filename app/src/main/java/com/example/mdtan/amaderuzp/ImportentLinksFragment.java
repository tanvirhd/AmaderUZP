package com.example.mdtan.amaderuzp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImportentLinksFragment extends Fragment {


    Button click1,click2,click3,click4;
    public ImportentLinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_importent_links_layout, container, false);
        /*click1=(Button)view.findViewById(R.id.click1);*/

        /*click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("www.bangladesh.gov.bd");
            }
        });*/
        return view;
    }

 /*   public void openLink(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }*/
 @Override
 public void onActivityCreated(@Nullable Bundle savedInstanceState) {
     super.onActivityCreated(savedInstanceState);

     click1 = (Button)getActivity().findViewById(R.id.click1);
     click1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse("https://www.bangladesh.gov.bd"));
             getActivity().startActivity(i);

         }
     });

     click2 = (Button)getActivity().findViewById(R.id.click2);
     click3 = (Button)getActivity().findViewById(R.id.click3);
     click4 = (Button)getActivity().findViewById(R.id.click4);

     click2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse("https://www.btrc.gov.bd"));
             getActivity().startActivity(i);

         }
     });
     click3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse("https://www.immi.gov.bd"));
             getActivity().startActivity(i);

         }
     });
     click4.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse("https://www.brtc.gov.bd"));
             getActivity().startActivity(i);

         }
     });

 }

}
