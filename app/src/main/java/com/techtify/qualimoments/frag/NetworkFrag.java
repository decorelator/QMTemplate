package com.techtify.qualimoments.frag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.techtify.qualimoments.R;
import com.techtify.qualimoments.adapters.NetworkAdapter;
import com.techtify.qualimoments.model.NetworkItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFrag extends BaseFrag {


    private NetworkAdapter mapMenuAdapter;

    public NetworkFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_network, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView networkGrid = view.findViewById(R.id.networkGrid);


        ArrayList<NetworkItem> network = new ArrayList<>();

        network.add(new NetworkItem(R.drawable.ic_launcher, "Name1", 0, "tag"));
        network.add(new NetworkItem(R.drawable.ic_launcher, "Name2", 1, "tag"));
        network.add(new NetworkItem(R.drawable.ic_launcher, "Name3", 2, "tag"));
        network.add(new NetworkItem(R.drawable.ic_launcher, "Name4", 3, "tag"));
        network.add(new NetworkItem(R.drawable.ic_launcher, "Name5", 4, "tag"));
        network.add(new NetworkItem(R.drawable.ic_launcher, "Name6", 5, "tag"));

        mapMenuAdapter = new NetworkAdapter(getActivity(), network);
        networkGrid.setAdapter(mapMenuAdapter);

        EditText search = view.findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            public void afterTextChanged(Editable arg0) {
                mapMenuAdapter.getFilter().filter(arg0);

            }
        });
    }
}
