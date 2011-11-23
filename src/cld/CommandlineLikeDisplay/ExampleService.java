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
	
	private boolean isNumber(String input){
		return ( input.matches("[0-9]+") );
	}
	
	//////////////////////////////////////////////////////////
	//Example Functions
	//////////////////////////////////////////////////////////
	
	public void start(){
		myCLDMessage.print_normal("Guessing game 0 - 100");
		int user_input = -1;
		Random a_random= new Random();
		int rand = a_random.nextInt(100);
		while (user_input != rand){
			myCLDMessage.print_normal("Guess a number:");
			String inString = myCLDMessage.getLine();
			myCLDMessage.print_normal(inString);
			if(!isNumber(inString)){
				myCLDMessage.print_normal("Please enter a number");
				continue;
			}
			user_input = Integer.valueOf(inString).intValue();
			if(user_input > rand) myCLDMessage.print_normal("Too High!");
			else myCLDMessage.print_normal("Too Low!");
		}
		myCLDMessage.print_normal("You Win!");
	}
	//////////////////////////////////////////////////////////

}
