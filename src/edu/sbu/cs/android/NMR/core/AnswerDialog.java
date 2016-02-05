package edu.sbu.cs.android.NMR.core;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sbu.cs.android.R;

import edu.sbu.cs.android.NMR.adapter.SlidingTabLayout;
import edu.sbu.cs.android.NMR.adapter.TabsPagerAdapter;;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.text.InputType;

import android.view.Menu;

import android.view.View;
import android.view.View.OnClickListener;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerDialog extends FragmentActivity implements OnClickListener {
	String push;
    String test;
    int num;
	EditText mEditText;
    Spinner spin;
String qAns, qBody=" h" ,correct , ansCorrect,path, isDrop;
TextView qData, feedback ,tvAns;
Button bt;
//Creates new button to clear selected answer.
Button bt2;
Intent intent;
File file;
ImageView img;
private SlidingTabLayout tabLayout;;
	private String[] spinloader;
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.question_dialog);
	path = getFilesDir().getAbsolutePath();
	file = new File(path + "/question.txt");
	qData = (TextView)findViewById(R.id.tvQuestion);
	 mEditText = (EditText)findViewById(R.id.etAnswer);
	spin = (Spinner) findViewById(R.id.answer_spinner);
	tvAns= (TextView)findViewById(R.id.tvAns);
	 mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
	feedback = (TextView)findViewById(R.id.tvFeedback);
	bt=(Button)findViewById(R.id.btAnsSubmit);
	//assigns value to clear button
	bt2=(Button)findViewById(R.id.bt2AnsClear);
	img=(ImageView)findViewById(R.id.imageView1);
	bt.setOnClickListener(this);
	intent = getIntent();
	this.spinloader = new String[] {
			"Word Answers here:", "singlet", "doublet", "triplet", "quartet", "d of d", "d of t", "multiplet",
            "CH", "CH2", "CH3", "CH2X", "CH3X", "CHX", "alkene", "alkyne", "phenyl", "aldehyde",
            "carboxylic acid", "exchangeable"
	};
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, spinloader);
	spin.setAdapter(adapter);
	correct=intent.getExtras().getString("isCorrect");
	qBody=intent.getExtras().getString("body");
	qAns=intent.getExtras().getString("ans");
   // isDrop=intent.getExtras().getString("isDropDown");
	qData.setText(qBody);
	if(correct.equals("true")){
		Toast.makeText(this, "already answered correctly ",Toast.LENGTH_LONG).show();
		tvAns.setText(qAns);
		feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
		img.setImageResource(R.drawable.correct);
	}else{
	qData.setText(qBody);
	}
//    if(isDrop.equals("true")){
//        mEditText.setBackgroundColor(Color.TRANSPARENT);
//    }else{
//        spin.setBackgroundColor(Color.TRANSPARENT);
//    }
	
	tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
	//tabLayout.setTitle(intent.getExtras().getString("title"));
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {

	return super.onCreateOptionsMenu(menu);
}
@Override
public void onClick(View v) {
	if(correct.equals("true")){
		Toast.makeText(this, "already answered correctly ",Toast.LENGTH_LONG).show();
		tvAns.setText(qAns);
		feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
		img.setImageResource(R.drawable.correct);
	}else{


       //if(isDrop.equals("true")){
//            if(qAns.equals(spin.getSelectedItem().toString())){
//                    QuestionsFragment.temp.setValid("true");
//                    //Toast.makeText(this, "Your Answer is Correct",Toast.LENGTH_LONG).show();
//                    feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
//                    feedback.setTextColor(Color.GREEN);
//                    ansCorrect="true";
//                    AnswerTask at=new AnswerTask(ansCorrect,qBody);
//                    img.setImageResource(R.drawable.correct);
//                    at.execute();
//
//
//            }
//            else{
//                QuestionsFragment.temp.setValid("false");
//                Toast.makeText(this, "" + spin.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
//                Toast.makeText(this, "" + qAns, Toast.LENGTH_LONG).show();
//                Toast.makeText(this, "Your Answer is Incorrect",Toast.LENGTH_LONG).show();
//                feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
//                feedback.setTextColor(Color.RED);
//                img.setImageResource(R.drawable.wrong);
//            }
        //}else{
            if(qAns.equals(mEditText.getText().toString()) || qAns.equals(spin.getSelectedItem().toString())){
                if(mEditText.getText().toString().isEmpty() && !qAns.equals(spin.getSelectedItem().toString())){
                    QuestionsFragment.temp.setValid("false");
                    Toast.makeText(this, "Empty Answer ",Toast.LENGTH_LONG).show();
                    feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
                    feedback.setTextColor(Color.RED);
                    img.setImageResource(R.drawable.wrong);
                }
                else{
                    QuestionsFragment.temp.setValid("true");
                    //Toast.makeText(this, "Your Answer is Correct",Toast.LENGTH_LONG).show();
                    feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
                    feedback.setTextColor(Color.GREEN);
                    ansCorrect="true";
                    AnswerTask at=new AnswerTask(ansCorrect,qBody);
                    img.setImageResource(R.drawable.correct);
                    at.execute();

                }
            }
            else{
                QuestionsFragment.temp.setValid("false");
                Toast.makeText(this, "Your Answer is Incorrect",Toast.LENGTH_LONG).show();
                feedback.setText("Feedback: " + intent.getExtras().getString("feedback"));
                feedback.setTextColor(Color.RED);
                img.setImageResource(R.drawable.wrong);
            }
       // }bigelse

	}

}
//assigns value to button
public void clearAnswer(View v) 
	{
	QuestionsFragment.temp.setValid("false");
	tvAns.setHint("Answer Here");
        tvAns.setText("");
        mEditText.getText().clear();
        spin.setSelection(0);
	feedback.setText(null);
	ansCorrect="false";
	AnswerTask at = new AnswerTask(ansCorrect,qBody);
	img.setImageBitmap(null);
	at.execute();
	}
public void writeToString(File file,String str){
	FileOutputStream stream = null;
	try {
		stream = new FileOutputStream(file);
		stream.write(str.getBytes());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public String readFromFile(){
	int length = (int) file.length();
	byte[] bytes = new byte[length];
	FileInputStream in =null;
	try {
		in= new FileInputStream(file);
		in.read(bytes);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String contents = new String(bytes); 
	return contents;

}
private class AnswerTask extends AsyncTask<Void, Void,Void>{

String jsondata , valid, q, body ,newJSONdata;
public  AnswerTask(String v, String b){
	valid=v;
	body=b;
}
public String loadJSONFromAsset() {
    String json = null;
    try {
    
        //InputStream is = getAssets().open("peak.json");
    	InputStream is= getApplicationContext().getResources().openRawResource(R.raw.peak);
        int size = is.available();

        byte[] buffer = new byte[size];

        is.read(buffer);

        is.close();

        json = new String(buffer, "UTF-8");


    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }
    return json;

}
	@Override
	protected void onPreExecute() {
		jsondata= readFromFile();
		//Toast.makeText(getApplicationContext(),body+" : "+q,Toast.LENGTH_LONG).show();
		super.onPreExecute();
	}
	@Override
	protected Void doInBackground(Void... params) {

		
		JSONArray ja,newArray;

		try{
			ja=new JSONArray(jsondata);
		        for(int i=0;i<ja.length();i++){
		          JSONObject json_data = ja.getJSONObject(i);
		          q=json_data.getString("Question");
		          if(q.equals(body)){
		        	  
		        	 // q=json_data.getString("Question");
		        	  json_data.put("isCorrect",valid); 
		        	  newArray =new JSONArray(ja.toString());
		        	  newJSONdata=newArray.toString();
		          }
		         
		        }
		}catch (JSONException e) {
		        e.printStackTrace();
		    }
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(),body+" : "+q,Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(),newJSONdata,Toast.LENGTH_LONG).show();
		writeToString(file,newJSONdata);
		super.onPostExecute(result);
	} 
}
}
