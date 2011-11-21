package cld.CommandlineLikeDisplay;

import android.os.Handler;

public class ExampleService {
	
	private int my_count_to;
	private Handler myHandler;
	
	//example service constructor
	public ExampleService( Handler a_Handler, int count_to ){
		my_count_to = count_to;
		myHandler = a_Handler;
	}
	
	public void stringToInt(String inString){
		if(inString.equals("one")){
			print_normal("1");
		}
		else if(inString.equals("two")){
			print_normal("2");
		}
		else if(inString.equals("three")){
			print_normal("3");
		}
		else if(inString.equals("four")){
			print_normal("4");
		}
		else if(inString.equals("five")){
			print_normal("5");
		}
		else if(inString.equals("six")){
			print_normal("6");
		}
		else if(inString.equals("seven")){
			print_normal("7");
		}
		else if(inString.equals("eight")){
			print_normal("8");
		}
		else if(inString.equals("nine")){
			print_normal("9");
		}
		else{
			print_normal(inString);
		}
	}
	
	//start the example service
	public void testFunction(){
		for(int i = 0; i < my_count_to; i++){
			print_debug("Hello " + i);
			print_normal("Hello " + i);
		}
	}
	
	//Print a debug message
	public void print_debug(String outString) {
		String debugString = "(Debug) " + outString;
		byte[] buffer = debugString.getBytes();

		myHandler.obtainMessage(Constants.MSG_DEBUG, buffer.length, -1, buffer)
				.sendToTarget();
	}

	//Print a normal message
	public void print_normal(String outString) {
		byte[] buffer = outString.getBytes();

		myHandler.obtainMessage(Constants.MSG_NORMAL, buffer.length, -1, buffer)
				.sendToTarget();
	}

}
