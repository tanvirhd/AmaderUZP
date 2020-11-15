package com.example.mdtan.amaderuzp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

     Context context;
     List<HomeElementListClass> elementList;
     public static FragmentManager fragmentManager;

    public RecyclerViewAdapter(Context context, List<HomeElementListClass> elementList) {
        this.context = context;
        this.elementList = elementList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.cardview_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            }
        });*/

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.imageView.setImageResource(elementList.get(position).getPhoto());
        holder.title.setText(elementList.get(position).getTitle());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(context,CardviewTamplateActivity.class);
                    intent.putExtra("position_value",position);

                    context.startActivity(intent);
                    Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();

                }
            });

    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }


    //this is the viewholder class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.cardview_image_xml);
            title=(TextView)itemView.findViewById(R.id.cardview_title_xml);
            cardView=(CardView)itemView.findViewById(R.id.cardview_xml);
        }
    }
}
