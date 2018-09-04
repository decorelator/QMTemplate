package com.techtify.qualimoments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.techtify.qualimoments.custom.PlaceholderFragment;
import com.techtify.qualimoments.frag.HomeFrag;
import com.techtify.qualimoments.frag.NetworkFrag;
import com.techtify.qualimoments.frag.NewsFeedFrag;
import com.techtify.qualimoments.frag.NotificationsFrag;
import com.techtify.qualimoments.frag.ProfileFrag;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private HomeFrag homeFrag;
    private NewsFeedFrag newsFeedFrag;
    private NetworkFrag networkFrag;
    private NotificationsFrag notificationsFrag;
    private ProfileFrag profileFrag;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                if (homeFrag == null) {
                    homeFrag = new HomeFrag();
                }
                return homeFrag;
            case 1:
                if (newsFeedFrag == null) {
                    newsFeedFrag = new NewsFeedFrag();
                }
                return newsFeedFrag;
            case 2:
                if (networkFrag == null)
                    networkFrag = new NetworkFrag();
                return networkFrag;
            case 3:
                if (notificationsFrag == null)
                    notificationsFrag = new NotificationsFrag();

                return notificationsFrag;
            case 4:
                if (profileFrag == null)
                    profileFrag = new ProfileFrag();
                return profileFrag;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
            case 3:
                return "SECTION 4";
            case 4:
                return "SECTION 5";
        }
        return null;
    }
}