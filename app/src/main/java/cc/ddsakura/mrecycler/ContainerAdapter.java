package cc.ddsakura.mrecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private static final int VIEW_TYPE_1 = 1;
    private static final int VIEW_TYPE_2 = 2;

    private ContactsDelegate mContactsDelegate = new ContactsDelegate(VIEW_TYPE_1);
    private ContactsDelegate mContactsDelegate2 = new ContactsDelegate(VIEW_TYPE_2);
    private List<List<Contact>> mContactss;

    private RecycledViewPool mRecycledViewPool = new RecycledViewPool();

    public ContainerAdapter() {
        mRecycledViewPool.setMaxRecycledViews(1, 100);
        mRecycledViewPool.setMaxRecycledViews(2, 100);
    }

    @NonNull
    @Override
    public ContainerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ContainerAdapter", "onCreateViewHolder");

        if (viewType == VIEW_TYPE_1) {
            return mContactsDelegate.onCreateViewHolder(parent, mRecycledViewPool);
        }
        return mContactsDelegate2.onCreateViewHolder(parent, mRecycledViewPool);
    }

    @Override
    public void onBindViewHolder(@NonNull ContainerAdapter.ViewHolder holder, int position) {
        Log.d("ContainerAdapter", "onBindViewHolder");
        List<Contact> contacts = mContactss.get(position);
        if (getItemViewType(position) == VIEW_TYPE_1) {
            mContactsDelegate.onBindViewHolder(holder, contacts);
        } else {
            mContactsDelegate2.onBindViewHolder(holder, contacts);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? VIEW_TYPE_1 : VIEW_TYPE_2;
    }

    @Override
    public int getItemCount() {
        return mContactss.size();
    }

    public void setContactss(List<List<Contact>> contactss) {
        mContactss = contactss;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
