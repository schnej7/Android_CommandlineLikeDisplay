package cld.CommandlineLikeDisplay;

import android.os.Handler;

public class CLDMessage {
	
	Handler myHandler;
	
	//Constructor
	CLDMessage(Handler a_Handler){
		myHandler = a_Handler;
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
