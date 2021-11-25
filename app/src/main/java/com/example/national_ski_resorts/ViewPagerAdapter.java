package com.example.national_ski_resorts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items = new ArrayList<Fragment>();
    private ArrayList<String> itext = new ArrayList<String>();
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        items.add(new b_frag_total());
        items.add(new b_frag_map());
        items.add(new b_frag_review());

        itext.add("정보");
        itext.add("지도");
        itext.add("리뷰");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
