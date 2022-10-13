package com.example.finalproject.RoomsLogic;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemTouchHelper extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter m_ItemTouchHelperAdapter;

    public MyItemTouchHelper(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        m_ItemTouchHelperAdapter = itemTouchHelperAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override //call when we drag the item inside the recyclerView
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder i_FromViewHolder, @NonNull RecyclerView.ViewHolder i_targetViewHolder) {
        return true;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Context mContext = null;
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        m_ItemTouchHelperAdapter.OnItemSwiped(viewHolder.getAdapterPosition());
    }
}
