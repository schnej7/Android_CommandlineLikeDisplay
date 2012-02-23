package cld.CommandlineLikeDisplay;

import android.util.Log;
import blue.mesh.BlueMeshService;

public class ExampleService {
	
	private static final String TAG = "ExampleService";
	private CLDMessage myCLDMessage;
	private BlueMeshService bms;
	
	//example service constructor
	public ExampleService( CLDMessage a_CLDMessage ){
		///////////////////////////////////////////////
		//TODO
		//
		//Create a CLDMessage with the parameter as CLDMessage
		//which will be your object for io
		///////////////////////////////////////////////	
		myCLDMessage = a_CLDMessage;
	}
	
	//////////////////////////////////////////////////////////
	//Example Functions
	//////////////////////////////////////////////////////////
	//Example that using CLDMessage is thread safe :)
	/*
	public void start(){
		myCLDMessage.print_normal("Starting testThread!");
		testThread a_testThread = new testThread();
		a_testThread.start();
		myCLDMessage.print_normal("It's thread safe!");
	}
	
	class testThread extends Thread{
		public void run(){
			myCLDMessage.print_normal("What is your name?");
			String in = myCLDMessage.getLine();
			myCLDMessage.print_normal("Your name is " + in);
		}
	}
	*/
	
	public void start(){
		myCLDMessage.print_normal("one");
		try{
			bms = new BlueMeshService();
		}
		catch(NullPointerException e){
			Log.e(TAG, "BlueMeshService Constructor failed");
			myCLDMessage.print_normal("Bluetooth not enableded");
			return;
		}
		myCLDMessage.print_normal("two");
		bms.launch();
		myCLDMessage.print_normal("three");
		readThread reader = new readThread();
		reader.start();
		while( true ){
			String input = myCLDMessage.getLine();
			if( input.equals("quit") ){
				Log.e(TAG, "quit");
				bms.disconnect();
				reader.interrupt();
				return;
			}
			bms.write(input.getBytes());
		}
	}
	
	public class readThread extends Thread{
		public readThread(){
			
		}
		public void run(){
			while (true){
				if(this.isInterrupted()){
					Log.d(TAG, "readThread interrupted");
					return;
				}
				byte bytes[] = bms.pull();
				if( bytes == null){
					myCLDMessage.print_normal("<NOTHING>");
					try {
						readThread.sleep(1000);
					} catch (InterruptedException e) {
						myCLDMessage.print_debug("Could not wait");
						e.printStackTrace();
						return;
					}
				}
				else{
					String messageString = new String(bytes);
					myCLDMessage.print_normal(messageString);
				}
			}
		}
		
	}
	
	//////////////////////////////////////////////////////////

}
