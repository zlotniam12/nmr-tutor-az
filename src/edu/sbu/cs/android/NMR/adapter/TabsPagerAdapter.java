package edu.sbu.cs.android.NMR.adapter;

import edu.sbu.cs.android.NMR.core.DrawFragment;
import edu.sbu.cs.android.NMR.core.SpectraFragment;
import edu.sbu.cs.android.NMR.core.HomeFragment;
import edu.sbu.cs.android.NMR.core.QuestionsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private CharSequence tabNames[];
    private int tabNum;
	public TabsPagerAdapter(FragmentManager fm, CharSequence mNames[], int mTabNum) {
		super(fm);
        this.tabNames = mNames;
        this.tabNum = mTabNum;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Home fragment activity
			return new HomeFragment();
		case 1:
			// Spectra fragment activity
			return new SpectraFragment();
		case 2:
			// Questions fragment activity
			return new QuestionsFragment();
		case 3:
			// draw fragment activity
			return new DrawFragment();
			
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return tabNum;
	}
	// gets title for ech tab in the strip
//	@Override
//	public CharSequence getPageTitle(int index) {
//        switch (index) {
//            case 0:
//                // Home fragment activity
//                return "Home";
//            case 1:
//                // Spectra fragment activity
//                return "Spectra";
//            case 2:
//                // Questions fragment activity
//                return "Questions";
//            case 3:
//                // draw fragment activity
//                return "SolveIt";
//
//        }
//
//        return null;
//
//	}
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

}
