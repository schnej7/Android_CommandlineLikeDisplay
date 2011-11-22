package cld.CommandlineLikeDisplay;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CommandlineLikeDisplayActivity extends Activity {
	
		private ArrayAdapter <String> mMessageArray;
		private ListView mMessageView;
		private ExampleService myService;
        private EditText txtInput;
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        setup();
	    }
	    
	    //Used to setup the display and the service
	    private void setup(){
	    	//Sets up the UI
	    	mMessageArray = new ArrayAdapter<String>(this, R.layout.message);
	    	mMessageView = (ListView) findViewById(R.id.ListMessages);
	    	mMessageView.setAdapter(mMessageArray);
	    	txtInput = (EditText)findViewById(R.id.txtInput);
	    	
    		///////////////////////////////////////////////
    		//TODO
    		//
    		//Here you call the constructor or a setup function for
	    	//your class, it is important to pass in mHandler so
	    	//that in your class you can create a CLDMessage object
	    	//used for sending data to the UI
    		///////////////////////////////////////////////
	    	myService = new ExampleService(mHandler, 10);
	    	
	        //button listener
	        final Button buttonCls = (Button) findViewById(R.id.btnCls);
	        buttonCls.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                clearDisplay();
	            }
	        });
	        
	        //button listener
	        final Button buttonStart = (Button) findViewById(R.id.btnStart);
	        buttonStart.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	        		///////////////////////////////////////////////
	        		//TODO
	        		//
	        		//Here you can set the start button to 
	            	//call a function in your class 	
	        		///////////////////////////////////////////////
	                myService.testFunction();
	            }
	        });
	        
	        final Button buttonGo = (Button) findViewById(R.id.btnGo);
	        buttonGo.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					getText();
				}
	        });
	        
	        txtInput.setOnKeyListener(new View.OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {	
					if( keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
						getText();
						return true;
					}
					else{
						return false;
					}
				}
			});
	        
	        final Button buttonClr = (Button) findViewById(R.id.btnClr);
	        buttonClr.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					txtInput.setText("");
				}
	        });
	    }
	    
	    private void getText(){
			String input = txtInput.getText().toString();
			///////////////////////////////////////////////
			//TODO
			//
			//Here you can set the go button to call a function
			//in your class with the parameter input which is
			//a string containing the data from the textual input
			///////////////////////////////////////////////
			myService.example(input);					
	    }
	    
	    //Used to clear the display
	    private void clearDisplay(){
	    	mMessageArray.clear();
	    }
	    
	    //Handler used for receiving messages from the service
	    private final Handler mHandler = new Handler(){
	    	@Override
	    	public void handleMessage( Message msg ){
	    		byte[] writeBuf;
	    		String messageString;
		    	switch(msg.what){
		    	case Constants.MSG_DEBUG:
		    		if(Constants.DEBUG){
			    		writeBuf = (byte[]) msg.obj;
			    		messageString = new String(writeBuf);
			    		mMessageArray.add(messageString);
			    		mMessageView.setSelection(mMessageView.getCount() - 1);
		    		}
		    		break;
		    	case Constants.MSG_NORMAL:
		    		writeBuf = (byte[]) msg.obj;
		    		messageString = new String(writeBuf);
		    		mMessageArray.add(messageString);
		    		mMessageView.setSelection(mMessageView.getCount() - 1);
		    		break;
		    	}
	    	}
	    };
	}