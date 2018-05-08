package cc.ddsakura.mrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ContactsAdapter extends ListAdapter<Contact, ContactsAdapter.ViewHolder> {

    private int mViewType;

    ContactsAdapter(int viewType, @NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
        mViewType = viewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ContactsAdapter", "onCreateViewHolder");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d("ContactsAdapter", "onBindViewHolder");
        Contact contact = getItem(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());
        Button button = viewHolder.messageButton;
        button.setText(contact.isOnline() ? "Message" : "Offline");
        button.setEnabled(contact.isOnline());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.contact_name);
            messageButton = itemView.findViewById(R.id.message_button);
        }
    }
}
