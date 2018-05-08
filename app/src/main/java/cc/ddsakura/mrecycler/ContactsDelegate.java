package cc.ddsakura.mrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ContactsDelegate {

    private int mViewType;

    ContactsDelegate(int viewType) {
        mViewType = viewType;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, RecyclerView.RecycledViewPool mRecycledViewPool) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactContainerView = inflater.inflate(R.layout.recycler_contact, parent, false);

        return new ViewHolder(contactContainerView, mRecycledViewPool, mViewType);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, List<Contact> contacts) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.updateData(contacts);
    }

    public static class ViewHolder extends ContainerAdapter.ViewHolder {

        ContactsAdapter mAdapter;

        ViewHolder(View view, RecyclerView.RecycledViewPool mRecycledViewPool, int viewType) {
            super(view);
            RecyclerView rvContacts = view.findViewById(R.id.rvContacts);
            mAdapter = new ContactsAdapter(viewType, new DiffCallback());
            rvContacts.setAdapter(mAdapter);
            rvContacts.setRecycledViewPool(mRecycledViewPool);
            if (viewType == 1) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                linearLayoutManager.setRecycleChildrenOnDetach(true);
                rvContacts.setLayoutManager(linearLayoutManager);
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
                gridLayoutManager.setRecycleChildrenOnDetach(true);
                rvContacts.setLayoutManager(gridLayoutManager);
            }
        }

        void updateData(List<Contact> data) {
            mAdapter.submitList(data);
        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(Contact oldItem, Contact newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
            return oldItem.isOnline() == newItem.isOnline();
        }
    }
}
