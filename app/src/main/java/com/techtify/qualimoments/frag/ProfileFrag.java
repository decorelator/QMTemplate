package com.techtify.qualimoments.frag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techtify.qualimoments.R;
import com.techtify.qualimoments.adapters.ProfileAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends BaseFrag {


    public ProfileFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView profileList = view.findViewById(R.id.profileList);
        profileList.setAdapter(new ProfileAdapter(getActivity()));
    }
}
