package cc.ddsakura.mrecycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment {

    private final List<List<Contact>> mContactsList = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView rvContacts = view.findViewById(R.id.rvContainer);
        for (int i = 0; i < 100; i++) {
            mContactsList.add(Contact.createContactsList(50));
        }
        ContainerAdapter adapter = new ContainerAdapter();
        rvContacts.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvContacts.setLayoutManager(linearLayoutManager);
        adapter.setContactss(mContactsList);
        return view;
    }

}
