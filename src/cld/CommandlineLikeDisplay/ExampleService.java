package cld.CommandlineLikeDisplay;

import java.util.Random;

public class ExampleService {
	
	private CLDMessage myCLDMessage;
	
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
	
	public void start(){
		myCLDMessage.print_normal("Guessing game 0 - 100");
		int user_input = Integer.valueOf(myCLDMessage.getLine()).intValue();
		Random a_random= new Random();
		int rand = a_random.nextInt(100);
		while (user_input != rand){
			if(user_input > rand) myCLDMessage.print_normal("Too High!");
			else myCLDMessage.print_normal("Too Low!");
			user_input = Integer.valueOf(myCLDMessage.getLine()).intValue();
		}
		myCLDMessage.print_normal("You Win!");
	}
	//////////////////////////////////////////////////////////

}
