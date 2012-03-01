package cld.CommandlineLikeDisplay;

import android.util.Log;
import blue.mesh.BlueMeshService;

public class ExampleService {
	
	private static final String TAG = "ExampleService";
	private CLDMessage myCLDMessage;
	private BlueMeshService bms;
	private boolean stop = false;
	
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
			myCLDMessage.print_normal("Your name is " + in); //what?!
		    // chica chica chica (slim shady!)
		}
	}
	*/
	public void stop(){
		this.stop = true;
		myCLDMessage.getLineFromInput("done");
		Log.d(TAG, "Stopping service");
	}
	
	public void start(){
		myCLDMessage.print_normal("one");
		try{
			bms = new BlueMeshService();
		}
		catch(NullPointerException e){
			Log.e(TAG, "BlueMeshService Constructor failed");
			myCLDMessage.print_normal("Bluetooth not enabled");
			return;
		}
		myCLDMessage.print_normal("two");
		bms.launch();
		myCLDMessage.print_normal("three");
		readThread reader = new readThread();
		reader.start();
		while( true ){
			
			if (this.stop)
			{
				Log.d(TAG, "2 Stopping service");
				break;
			}
				
			String input = myCLDMessage.getLine();
			if( input == null ) break;
			
			if( input.equals("devices") ){
				int devices = bms.getNumberOfDevicesOnNetwork();
				myCLDMessage.print_normal("Devices on network = " + devices);
			}
			
			myCLDMessage.print_normal("ME: " + input);
			String message = bms.getMyDeviceName() + ": " + input;
			bms.write(message.getBytes());
		}
		Log.d(TAG, "3 Stopping service");
		Log.d(TAG, "quit");
		bms.disconnect();
		reader.interrupt();
		return;
	}
	
	public class readThread extends Thread{
		public readThread(){
			
		}
		public void run(){
			while (true){
				if(stop){
					Log.d(TAG, "readThread interrupted");
					return;
				}
				byte bytes[] = bms.pull();
				if( bytes == null){
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
