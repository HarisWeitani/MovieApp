package com.example.sirwarfox.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sirwarfox.movieapp.R;
import com.example.sirwarfox.movieapp.model.Trailer;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.List;

/**
 * Created by Sir.WarFox on 13/02/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trailer> trailerList;

    public TrailerAdapter(Context mContext, List<Trailer> trailerList) {
        this.mContext = mContext;
        this.trailerList = trailerList;
    }

    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrailerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(trailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail;
        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.movietitle);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if( pos != RecyclerView.NO_POSITION){
                        Trailer clickedDataItems = trailerList.get(pos);
                        String videoId = trailerList.get(pos).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
                        intent.putExtra("VIDEO_ID", videoId);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "You Clicked" + clickedDataItems.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
