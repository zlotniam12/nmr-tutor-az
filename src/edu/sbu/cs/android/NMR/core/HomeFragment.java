package edu.sbu.cs.android.NMR.core;

import edu.sbu.cs.android.NMR.core.ImageAdapter;
import edu.sbu.cs.android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class HomeFragment extends Fragment {
	  public interface OnSelectedListener {
	        public void onSelected(String text);

      }

    OnSelectedListener mListener;
	  @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
          this.getActivity();
          try {
	            mListener = (OnSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
	        }
	    }
    ///
//	    public void onGridItemClick(GridView gridview, View v, int position, long id) {
//	        // Append the clicked item's row ID with the content provider Uri
//	        
//	    	jsonAsset = id);
//	        // Send the event and Uri to the host activity
//	        mListener.onItemSelected(JsonAsset);
//	    }
GridView gridview;
//    Intent pi = new Intent(getActivity(), QuestionsFragment.class);
//    Intent pj = new Intent(getActivity(), SpectraFragment.class);
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//        Bundle bundle = new Bundle();
//        bundle.putFloat("0", 1);

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		gridview = (GridView)rootView.findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(gridview.getContext()));
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ((MainActivity) getActivity()).setProblem(position);
                switch (position) {
                    case 0:
                       // SpectraFragment.w.loadUrl("file:///android_asset/nmr0.html");
                        mListener.onSelected("file:///android_asset/nmr0.html");
//                        pi.putExtra("problem", "0");
//                        pj.putExtra("problem", "0");
//                        getActivity().startActivity(pi);
//                        getActivity().startActivity(pj);
                        FragmentManager fragMan;
                        fragMan = getFragmentManager();
                        Fragment frg= fragMan.findFragmentByTag("fragment_spectra");
                        Fragment frg2 = fragMan.findFragmentByTag("fragment_questions");
                        final FragmentTransaction ft = fragMan.beginTransaction();
                        ft.detach(frg);
                        ft.attach(frg);
                        ft.detach(frg2);
                        ft.attach(frg2);
                        ft.commit();

//                        Fragment spectra = getChildFragmentManager().findFragmentById(R.layout.fragment_spectra);
//                        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.detach(spectra);
//                        ft.attach(spectra);
//                        ft.commit();

                    case 1:
                     //   SpectraFragment.w.loadUrl("file:///android_asset/nmr1.html");
                        mListener.onSelected("file:///android_asset/nmr1.html");
//                        pi.putExtra("problem", "1");
//                        pj.putExtra("problem", "1");
//                        getActivity().startActivity(pi);
//                        getActivity().startActivity(pj);
                        fragMan = getFragmentManager();
                        frg= fragMan.findFragmentByTag("fragment_spectra");
                        frg2 = fragMan.findFragmentByTag("fragment_questions");
                        final FragmentTransaction ft2 = fragMan.beginTransaction();
                        ft2.detach(frg);
                        ft2.attach(frg);
                        ft2.detach(frg2);
                        ft2.attach(frg2);
                        ft2.commit();
                    case 2:
                //        SpectraFragment.w.loadUrl("file:///android_asset/nmr2.html");
                        mListener.onSelected("file:///android_asset/nmr2.html");
//                        pi.putExtra("problem", "2");
//                        pj.putExtra("problem", "2");
//                        getActivity().startActivity(pi);
                        fragMan = getFragmentManager();
                        frg= fragMan.findFragmentByTag("fragment_spectra");
                        frg2 = fragMan.findFragmentByTag("fragment_questions");
                        final FragmentTransaction ft3 = fragMan.beginTransaction();
                        ft3.detach(frg);
                        ft3.attach(frg);
                        ft3.detach(frg2);
                        ft3.attach(frg2);
                        ft3.commit();
                    default:
                        SpectraFragment.w.loadUrl("file:///android_asset/nmr0.html");
                }

                //               ((MainActivity) getActivity()).recreate();
//                Fragment fragment = new SpectraFragment();
//                bundle.putFloat("0", position);
                //               fragment.setArguments(bundle);
                // .addToBackStack(TAG_TO_FRAGMENT).commit(); TAG_TO_FRAGMENT
//                Intent intent = getIntent();
//                finish();
//                startActivity(intent);
                //               Fragment spectraFragment = getActivity().getSupportFragmentManager().findFragmentById(R.layout.fragment_spectra);
//                if (spectraFragment instanceof Fragment) {
//                    android.support.v4.app.FragmentTransaction fragTran;
//                    fragTran = (getActivity()).getSupportFragmentManager().beginTransaction();
//                    fragTran.detach(spectraFragment);
//                    fragTran.attach(spectraFragment);
//                    fragTran.commit();
//                }
                // ((MainActivity) getActivity()).onItemSelected();
                Toast.makeText(getActivity(), " " + position, Toast.LENGTH_SHORT).show();
            }
        });


      return rootView;

	}

}
        