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
import com.techtify.qualimoments.adapters.NotificationsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFrag extends BaseFrag {


    public NotificationsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView notificationsList = view.findViewById(R.id.notificationsList);
        notificationsList.setAdapter(new NotificationsAdapter(getActivity()));

    }
}
