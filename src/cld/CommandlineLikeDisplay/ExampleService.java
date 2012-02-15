package cld.CommandlineLikeDisplay;

import blue.mesh.BlueMeshService;

public class ExampleService {
	
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
		bms = new BlueMeshService();
		myCLDMessage.print_normal("two");
		bms.launch();
		myCLDMessage.print_normal("three");
		readThread reader = new readThread();
		reader.start();
		while( true ){
			bms.write(myCLDMessage.getLine().getBytes());
		}
	}
	
	public class readThread extends Thread{
		public readThread(){
			
		}
		public void run(){
			while (true){
				byte bytes[] = bms.pull();
				if( bytes == null){
					myCLDMessage.print_normal("<NOTHING>");
					try {
						readThread.sleep(1000);
					} catch (InterruptedException e) {
						myCLDMessage.print_debug("Could not wait");
						e.printStackTrace();
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
