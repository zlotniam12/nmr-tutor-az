package edu.sbu.cs.android.NMR.core;

import edu.sbu.cs.android.NMR.core.ImageAdapter;
import edu.sbu.cs.android.NMR.core.HomeFragment;
import edu.sbu.cs.android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
//	  public interface OnItemSelectedListener {
//	        public void onItemSelected();
//	    }
//	  OnItemClickListener mListener;
//	  @Override
//	    public void onAttach(Activity activity) {
//	        super.onAttach(activity);
//	        try {
//	            mListener = (OnItemClickListener) activity;
//	        } catch (ClassCastException e) {
//	            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
//	        }
//	    }
//	    public void onGridItemClick(GridView gridview, View v, int position, long id) {
//	        // Append the clicked item's row ID with the content provider Uri
//	        
//	    	jsonAsset = id);
//	        // Send the event and Uri to the host activity
//	        mListener.onItemSelected(JsonAsset);
//	    }
GridView gridview;


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
                        SpectraFragment.w.loadUrl("file:///android_asset/nmr0.html");
//                        Fragment spectra = getChildFragmentManager().findFragmentById(R.layout.fragment_spectra);
//                        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.detach(spectra);
//                        ft.attach(spectra);
//                        ft.commit();
                    case 1:
                        SpectraFragment.w.loadUrl("file:///android_asset/nmr1.html");
                    case 2:
                        SpectraFragment.w.loadUrl("file:///android_asset/nmr2.html");
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
        