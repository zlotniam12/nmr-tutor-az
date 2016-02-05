package edu.sbu.cs.android.NMR.core;


import java.io.File;

import edu.sbu.cs.android.NMR.adapter.SlidingTabLayout;
import edu.sbu.cs.android.NMR.adapter.TabsPagerAdapter;
//import edu.sbu.cs.android.NMR.core.HomeFragment.OnItemSelectedListener;
import edu.sbu.cs.android.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends FragmentActivity implements HomeFragment.OnSelectedListener
{
	Bundle bd;
	Intent intent = new Intent();

	private NonSwipeableViewPager viewPager;
	//DrawingView dv=new DrawingView(this,);
	private TabsPagerAdapter mAdapter;
    private int tabsNum = 4;
    SlidingTabLayout tabLayout;
//	Toolbar toolbar;
Bundle pdata=new Bundle();
	//private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Home", "Spectra", "Questions", "SolveIt" };
	private boolean lock=false;
	File file;
	String path;
	private int problem = 0;
//    Intent peakdata = new Intent(MainActivity.this, QuestionsFragment.class);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
		viewPager.invalidate();
    //    toolbar = (Toolbar) findViewById(R.id.tool_bar);
		bd=new Bundle();
//		peakdata.putExtra("", 0);
		path = getFilesDir().getAbsolutePath();
		file = new File(path + "/question.txt");
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), tabs, tabsNum);
		viewPager.setAdapter(mAdapter);
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabLayout.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabLayout.setViewPager(viewPager);
        tabLayout.setVisibility(View.VISIBLE);

      //  toolbar.setLogo(R.drawable.chalkboardicon_2);


//        actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setIcon(R.drawable.chalkboardicon_2);
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		// Adding Tabs
//		for (String tab_name : tabs) {
//			actionBar.addTab(actionBar.newTab().setText(tab_name)
//					.setTabListener(this));
//		}
		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if(lock){
				}else{
					viewPager.setCurrentItem(position, true);
				//	actionBar.setSelectedNavigationItem(position);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

//	public void writeToString(File file,String str){
//		FileOutputStream stream = null;
//		try {
//			stream = new FileOutputStream(file);
//			stream.write(str.getBytes());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				stream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public String loadJSONFromAsset() {
//		String json = null;
//		try {
//
//			InputStream is = getAssets().open("peak.json");
//			
//			//InputStream is= getApplicationContext().getResources().openRawResource(R.raw.peak);
//			int size = is.available();
//
//			byte[] buffer = new byte[size];
//
//			is.read(buffer);
//
//			is.close();
//
//			json = new String(buffer, "UTF-8");
//
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return null;
//		}
//		return json;
//
//	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_NMR:
	    	SpectraFragment.w.loadUrl("file:///android_asset/nmr.html");
	    	SpectraFragment.w.getSettings().setBuiltInZoomControls(true);
	    	SpectraFragment.w.getSettings().setDisplayZoomControls(false);

	       return true;
	    case R.id.action_IR:
            SpectraFragment.w.loadUrl("file:///android_asset/ir.html");
	    	SpectraFragment.w.getSettings().setBuiltInZoomControls(true);
	    	SpectraFragment.w.getSettings().setDisplayZoomControls(false);
            pdata.putString("problem", "" + getProblem());
            //set Fragmentclass Arguments
            QuestionsFragment fragobj=new QuestionsFragment();
            fragobj.setArguments(pdata);
            Fragment frg = null;
            Fragment frg2 = null;
            FragmentManager fragMan;
            fragMan = getSupportFragmentManager();
            frg = fragMan.findFragmentByTag("fragment_spectra");
            frg2 = fragMan.findFragmentByTag("fragment_questions");
            final FragmentTransaction ft = fragMan.beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.detach(frg2);
            ft.attach(frg2);
            ft.commit();

        //    android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.attach(questions);
         //   ft.commit();
	        return true;
	    case R.id.action_CNMR:
	    	SpectraFragment.w.loadUrl("file:///android_asset/cnmr.html");
	    	SpectraFragment.w.getSettings().setBuiltInZoomControls(true);
	    	SpectraFragment.w.getSettings().setDisplayZoomControls(false);
	        return true;
	    	//writeToString(file,loadJSONFromAsset());
	    	//Toast.makeText(getApplication(),"reset act",Toast.LENGTH_SHORT).show();
	    case R.id.action_Lock:
	    	lock=!lock;
	    	if(lock)
                tabLayout.setVisibility(View.INVISIBLE);
	  //  	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    	else
	    //		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                tabLayout.setVisibility(View.VISIBLE);
	    	viewPager.setBlockSwipe(lock);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item2) {
//	    // Handle item selection
//	    switch (item.getItemId()) {
//	    case R.id.action_SPECTRUM1:
//	    	HomeFragment.w.loadUrl("file:///android_asset/nmr.html");
//	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
//	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
//	       return true;
//	    case R.id.action_IR:
//	    	HomeFragment.w.loadUrl("file:///android_asset/ir.html");
//	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
//	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
//	        return true;
//	    case R.id.action_CNMR:
//	    	HomeFragment.w.loadUrl("file:///android_asset/cnmr.html");
//	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
//	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
//	        return true;
//	    case R.id.action_Reset:
//	    	//writeToString(file,loadJSONFromAsset());
//	    	//Toast.makeText(getApplication(),"reset act",Toast.LENGTH_SHORT).show();
//	        return true;
//	    case R.id.action_Lock:
//	    	lock=!lock;
//	    	if(lock)
//	    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//	    	else
//	    		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//	    	
//	    	viewPager.setBlockSwipe(lock);
//	        return true;
//	    default:
//	        return super.onOptionsItemSelected(item);
//	    }
//	}
//	@Override
//	public void onTabReselected(Tab tab, FragmentTransaction ft) {
//	}
//
//	@Override
//	public void onTabSelected(Tab tab, FragmentTransaction ft) {
//		// on tab selected
//		// show respected fragment view
//		viewPager.setCurrentItem(tab.getPosition());
//	}
//
//	@Override
//	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
////        ft.detach(questionsfragment);
////        ft.attach(tab);
////        ft.commit();
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void onBackPressed() {
	    moveTaskToBack(true);
		//wv.loadUrl("file:///android_asset/index.html");
	}
	public int getProblem(){
	    return this.problem;
	}

	public void setProblem(int num){
	    this.problem = num;
	}

	@Override
	public void onSelected() {
//        android.support.v4.app.FragmentManager fragMan;
//                fragMan = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragMan.beginTransaction();
//        android.support.v4.app.Fragment newFragment = new SpectraFragment();
//        android.support.v4.app.FragmentTransaction transaction;
//        transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.layout.fragment_spectra, newFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
        Fragment frg = null;
        Fragment frg2 = null;
        FragmentManager fragMan;
        fragMan = getSupportFragmentManager();
        frg = fragMan.findFragmentByTag("fragment_spectra");
        frg2 = fragMan.findFragmentByTag("fragment_questions");
        final FragmentTransaction ft = fragMan.beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.detach(frg2);
        ft.attach(frg2);
        ft.commit();

//		Fragment spectraFragment = getSupportFragmentManager().findFragmentById(R.layout.fragment_spectra);
//		if (spectraFragment instanceof Fragment) {
//			android.support.v4.app.FragmentTransaction fragTran;
//			fragTran = (getSupportFragmentManager().beginTransaction());
//			fragTran.detach(spectraFragment);
//			fragTran.attach(spectraFragment);
//			fragTran.commit();

		}

}
