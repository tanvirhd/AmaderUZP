package com.example.mdtan.amaderuzp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ComplainAdapter extends BaseAdapter {

    List<Complain> complainList;
    private Activity context;

    public ComplainAdapter(List<Complain> complainList, Activity context) {
        this.complainList = complainList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return complainList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.complain_listview_layout,null,true);

        TextView tvDoptor=(TextView)view.findViewById(R.id.tvdoptor);
        TextView tvTopic=(TextView)view.findViewById(R.id.tvtopic);
        TextView tvDetails=(TextView)view.findViewById(R.id.tvdetails);
        TextView tvName=(TextView)view.findViewById(R.id.tvName);
        TextView tvpreAdd=(TextView)view.findViewById(R.id.tvPresentAdd);
        TextView tvperAdd=(TextView)view.findViewById(R.id.tvpermanentAdd);
        TextView tvEmail=(TextView)view.findViewById(R.id.tvEmail);
        TextView tvPhn=(TextView)view.findViewById(R.id.tvPhn);

        Complain complain=complainList.get(position);

        tvDoptor.setText(complain.getDoptor());
        tvTopic.setText(complain.getTopic());
        tvDetails.setText(complain.getDetails());
        tvDetails.setText(complain.getDetails());
        tvName.setText(complain.getName());
        tvpreAdd.setText(complain.getAddress());
        tvperAdd.setText(complain.getPaddress());
        tvEmail.setText(complain.getEmail());
        tvPhn.setText(complain.getPhn_number());

        return view;
    }
}
