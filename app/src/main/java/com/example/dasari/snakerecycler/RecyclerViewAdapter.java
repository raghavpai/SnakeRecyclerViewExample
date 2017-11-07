package com.example.dasari.snakerecycler;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dasari.recyclerviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dasari on 25-10-2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    ArrayList<DataModel> mValues;
    protected ItemListener mListener;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {
        mContext = context;
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void removeItem(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int position, DataModel data) {
        mValues.add(position, data);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        DataModel item;
        ImageView closeTile;
        ImageView contentImage;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
        }

        public void setData(DataModel item) {
            this.item = item;
            closeTile = itemView.findViewById(R.id.imageView_close);
            contentImage = itemView.findViewById(R.id.imageView_content);
            textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

            if (item.getTileType() == DataModel.TileType.REGULAR) {
                relativeLayout.setBackgroundColor(Color.BLACK);
                closeTile.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                Picasso.with(mContext).load(Uri.parse(item.getImageUrl())).into(contentImage);
                closeTile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeItem(getAdapterPosition());
                    }
                });
                textView.setText(item.getNumber());
            } else {
                relativeLayout.setBackgroundColor(Color.parseColor("#8000ff"));
                contentImage.setImageResource(R.mipmap.add_icon);
                closeTile.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }

}