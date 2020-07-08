package com.example.lenovo.mytvshows;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelClass> imagesModels;

    public ShowsAdapter(Context context, ArrayList<ModelClass> imagesModels) {
        this.context = context;
        this.imagesModels = imagesModels;
    }

    @NonNull
    @Override
    public ShowsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.poster_design,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowsAdapter.MyViewHolder myViewHolder, final int i) {
        ModelClass modelClass=imagesModels.get(i);
        Picasso.with(context).load(context.getString(R.string.image_path)+modelClass.poster).placeholder(R.drawable.ic_launcher_foreground).into(myViewHolder.iv);
        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] str=new String[8];
                str[0] = imagesModels.get(i).getPoster();
                str[1] = imagesModels.get(i).getName();
                str[2] = imagesModels.get(i).getPopularity();
                str[3] = imagesModels.get(i).getInfo();
                str[4] = imagesModels.get(i).getBackposter();
                str[5] = imagesModels.get(i).getFirstdate();
                str[6] = imagesModels.get(i).getOriginal_name();
                str[7] = imagesModels.get(i).getId();
                Intent intent = new Intent(context, TvShows_Details.class);
                intent.putExtra(context.getString(R.string.info), str  );
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imageview);
        }
    }
}
