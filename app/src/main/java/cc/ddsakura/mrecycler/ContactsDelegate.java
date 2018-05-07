package cc.ddsakura.mrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ContactsDelegate {

    ArrayList<Contact> contacts;

    private int mViewType = 1;

    public ContactsDelegate(int viewType) {
        mViewType = viewType;
        contacts = Contact.createContactsList(20);
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, RecyclerView.RecycledViewPool mRecycledViewPool) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactContainerView = inflater.inflate(R.layout.recycler_contact, parent, false);

        return new ViewHolder(contactContainerView, mRecycledViewPool, mViewType);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.updateData(contacts);
    }

    public static class ViewHolder extends ContainerAdapter.ViewHolder {

        ContactsAdapter mAdapter;

        public ViewHolder(View view, RecyclerView.RecycledViewPool mRecycledViewPool, int viewType) {
            super(view);
            RecyclerView rvContacts = view.findViewById(R.id.rvContacts);
            mAdapter = new ContactsAdapter();
            rvContacts.setAdapter(mAdapter);
            rvContacts.setRecycledViewPool(mRecycledViewPool);
            if (viewType == 1) {
                rvContacts.setLayoutManager(new LinearLayoutManager(view.getContext()));
            } else {
                rvContacts.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
            }
        }

        public void updateData(ArrayList<Contact> data) {
            mAdapter.updateData(data);
        }
    }
}
