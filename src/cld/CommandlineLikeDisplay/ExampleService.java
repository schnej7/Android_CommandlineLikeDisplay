package cld.CommandlineLikeDisplay;

import android.os.Handler;

public class ExampleService {
	
	private int my_count_to;
	private CLDMessage myCLDMessage;
	
	//example service constructor
	public ExampleService( Handler a_Handler, int count_to ){
		///////////////////////////////////////////////
		//TODO
		//
		//Create a CLDMessage with the parameter as the handler 
		//that you passed into the constructor of your class	
		///////////////////////////////////////////////	
		my_count_to = count_to;
		myCLDMessage = new CLDMessage(a_Handler);
	}
	
	//////////////////////////////////////////////////////////
	//Example Functions
	//////////////////////////////////////////////////////////
	public void stringToInt(String inString){
		myCLDMessage.print_normal("You typed: " + inString);
	}
	
	public void testFunction(){
		for(int i = 0; i < my_count_to; i++){
			myCLDMessage.print_debug("Hello " + i);
			myCLDMessage.print_normal("Hello " + i);
		}
	}
	//////////////////////////////////////////////////////////

}
