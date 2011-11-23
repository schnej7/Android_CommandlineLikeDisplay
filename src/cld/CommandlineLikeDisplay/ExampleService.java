package cld.CommandlineLikeDisplay;

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
		String input = myCLDMessage.getLine();
		myCLDMessage.print_normal("You write: " + input);
	}
	//////////////////////////////////////////////////////////

}
