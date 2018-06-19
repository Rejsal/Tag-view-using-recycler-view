package com.example.toobler.recyclerviewtags;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by toobler on 19/6/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterHolder> {
    Context context;
    ArrayList<RecyclerModel> model = new ArrayList<>(  );

    public RecyclerAdapter(Context context,ArrayList<RecyclerModel> model){
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);

        return new RecyclerAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterHolder holder, final int position) {
        final RecyclerModel mod = model.get( position );
        holder.tagTextView.setText( mod.getTagText() );
        holder.tagImageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.remove( position );
                notifyDataSetChanged();
            }
        } );
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class RecyclerAdapterHolder extends RecyclerView.ViewHolder {
        public TextView tagTextView;
        public ImageView tagImageView;
        public RecyclerAdapterHolder(View itemView) {
            super( itemView );
            tagTextView = itemView.findViewById( R.id.tag_textView );
            tagImageView = itemView.findViewById( R.id.tag_closeBtn );
        }
    }
}

