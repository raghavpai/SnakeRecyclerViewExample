package com.example.dasari.snakerecycler;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Dasari on 25-10-2017.
 */

public class SnakeItemTouchHelper extends ItemTouchHelper.Callback{
    private final RecyclerViewAdapter mAdapter;

    public SnakeItemTouchHelper(RecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        if(viewHolder.getAdapterPosition() < mAdapter.getItemCount()-1) {
            return makeMovementFlags(dragFlags, 0);
        } else {
            return makeMovementFlags(0, 0);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(target.getAdapterPosition() < mAdapter.getItemCount()-1) {
            mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.removeItem(viewHolder.getAdapterPosition());
    }
}
