package com.maxcheung.command;

public enum CommandType {
	CONVERSATIONIDCHECK("conversationIdCheck"),
	PROGRAMMER("programmer"),
	POLITICIAN("politician"),
	DOMESTIC_ENGINEER("domesticEngineer");
	
	  private String commandType; 
	  
      private CommandType(String commandType) { 
          this.commandType = commandType; 
      }

	   @Override 
       public String toString(){ 
           return commandType; 
       }
}
