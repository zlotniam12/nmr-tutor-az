package edu.sbu.cs.android.NMR.core;

import edu.sbu.cs.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class SpectraFragment extends Fragment {

static WebView  w;
String str;
	public void updateText(String text){
		setStr(text);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//        Bundle bundle = this.getArguments();
//        float flo = bundle.getFloat("0");
		View rootView = inflater.inflate(R.layout.fragment_spectra, container, false);
//        Intent pj = getActivity().getIntent();
//        String problem = pj.getStringExtra("problem");
		w=(WebView)rootView.findViewById(R.id.imgView);
        w.loadUrl(getStr());
//		w.loadUrl("file:///android_asset/nmr" + problem + ".html");
        w.getSettings().setBuiltInZoomControls(true);
		w.getSettings().setDisplayZoomControls(false);
		return rootView;

	}

    private void setStr (String text){
        str = text;
    }

    private String getStr (){
        return str;
    }
}



