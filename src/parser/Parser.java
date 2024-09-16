package parser;

import java.io.BufferedReader;
import java.io.IOException;

import common.CommandType;
import common.CommandRegex;

public class Parser {
	
	private BufferedReader buffReader;
	private boolean isUpcomingCommandInitialized;
	private String currentCommand;
	private String upcomingCommand;
	
	public Parser(BufferedReader buffReader) throws IOException {
		this.buffReader = buffReader;
	}
	
	public boolean hasMoreCommands() throws IOException {
		
		if(!isUpcomingCommandInitialized) {
			initializeUpcomingCommand();
		}
		
		return upcomingCommand != null;
	}
	
	public void advance() throws IOException {
		setCurrentCommand();
		setUpcomingCommand();
	}
	
	public CommandType getCommandType() {
		
		if(isCommandTypeA()) { return CommandType.A_COMMAND; }
		if(isCommandTypeC()) { return CommandType.C_COMMAND; }
		if(isCommandTypeL()) { return CommandType.L_COMMAND; }
		if(isCommandTypeComment()) { return CommandType.COMMENT; }
		
		return CommandType.UKNOWKN;
	}
	
	public String getSymbol() {
		String symbol = null;
		
		if(isCommandTypeA()) { symbol = getSymbolFrom_A_Command(); }
		if(isCommandTypeL()) { symbol = getSymbolFrom_L_Command(); }
		
		return symbol;
	}
	
	public String getDest() {
		String dest = null;
		
		if(isDestPresent()) { dest = parseDest(); }
		
		return dest;
	}
	
	public String getJump() {
		String jump = null;
		
		if(isJumpPresent()) { jump = parseJump(); }
		
		return jump;
	}
	
	public String getComp() {
		int startIndex = currentCommand.indexOf('=') + 1;
		int endIndex = currentCommand.indexOf(';');
		
		if(startIndex == -1) { startIndex = 0; }
		if(endIndex == -1)   { endIndex = currentCommand.length(); }
		
		return currentCommand.substring(startIndex, endIndex);
	}
	
	private String parseDest() {
		int indexOfEqualSign = currentCommand.indexOf('=');
		return currentCommand.substring(0, indexOfEqualSign);
	}
	
	private String parseJump() {
		int indexOfSemicolon = currentCommand.indexOf(';');
		int indexOfLastChar = currentCommand.length() - 1;
		return currentCommand.substring(indexOfSemicolon + 1, indexOfLastChar + 1);
	}
	
	private boolean isDestPresent() {
		return currentCommand.contains("=");
	}
	
	private boolean isJumpPresent() {
		return currentCommand.contains(";");
	}
	
	private String getSymbolFrom_A_Command() {
		return currentCommand.substring(1);
	}
	
	private String getSymbolFrom_L_Command() {
		return currentCommand.substring(1, currentCommand.length()-1);
		
	}
	
	private void initializeUpcomingCommand() throws IOException {
		upcomingCommand = readNextCommand();
		isUpcomingCommandInitialized = true;
	}
	
	private void setCurrentCommand() {
		currentCommand = upcomingCommand;
	}
	
	private void setUpcomingCommand() throws IOException {
		upcomingCommand = readNextCommand();
	}
	
	private String readNextCommand() throws IOException {
		String line;
		
		if((line = buffReader.readLine()) != null) {
			line = line.replaceAll("\\s","");
		}
		
		return line;
	}
	
	private boolean isCommandTypeA() {
		return currentCommand.matches(CommandRegex.A_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeC() {
		return currentCommand.matches(CommandRegex.C_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeL() {
		return currentCommand.matches(CommandRegex.L_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeComment() {
		return currentCommand.matches(CommandRegex.COMMENT_PATTERN);
	}
}
