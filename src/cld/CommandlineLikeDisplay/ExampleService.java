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
