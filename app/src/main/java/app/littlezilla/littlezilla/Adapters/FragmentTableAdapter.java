package app.littlezilla.littlezilla.Adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import app.littlezilla.littlezilla.Fragments.Fragment1;
import app.littlezilla.littlezilla.Fragments.Fragment2;
import app.littlezilla.littlezilla.Fragments.Fragment3;

public class FragmentTableAdapter extends FragmentPagerAdapter {

    public FragmentTableAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
