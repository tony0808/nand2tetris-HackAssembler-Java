package parser;

import common.CommandType;

public class Parser {
	
	private String command;
	private long commandLine;
	
	public Parser() {
		command = null;
		commandLine = 0;
	}
	
	public void setCommand(String command) {
		commandLine++;
		if(command != null) {
			this.command = command.trim();
		}
		else {
			this.command = null;
		}
	}
	
	public String getCommand() {
		return command;
	}
	
	public long getCommandLine() {
		return commandLine;
	}
	
	public void resetCommandLine() {
		commandLine = 0;
	}
	
	public CommandType getCommandType() {
		
		if(isCommandTypeComment()) { return CommandType.COMMENT; }
		if(isCommandTypeA()) { return CommandType.A_COMMAND; }
		if(isCommandTypeC()) { return CommandType.C_COMMAND; }
		if(isCommandTypeL()) { return CommandType.L_COMMAND; }
		if(isCommandTypeWhitespace() ) { return CommandType.WHITESPACE; }
		
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
		int startIndex = command.indexOf('=') + 1;
		int endIndex = command.indexOf(';');
		
		if(startIndex == -1) { startIndex = 0; }
		if(endIndex == -1)   { endIndex = command.length(); }
		
		return command.substring(startIndex, endIndex);
	}
	
	private String parseDest() {
		int indexOfEqualSign = command.indexOf('=');
		return command.substring(0, indexOfEqualSign);
	}
	
	private String parseJump() {
		int indexOfSemicolon = command.indexOf(';');
		int indexOfLastChar = command.length() - 1;
		return command.substring(indexOfSemicolon + 1, indexOfLastChar + 1);
	}
	
	private boolean isDestPresent() {
		return command.contains("=");
	}
	
	private boolean isJumpPresent() {
		return command.contains(";");
	}
	
	private String getSymbolFrom_A_Command() {
		return command.substring(1);
	}
	
	private String getSymbolFrom_L_Command() {
		return command.substring(1, command.length()-1);
	}
	
	private boolean isCommandTypeA() {
		boolean isACommand = false;
		if(command.length() > 1 && command.charAt(0) == '@') {
			isACommand = true;
		}
		return isACommand;
	}
	
	private boolean isCommandTypeC() {
		boolean isCcommand = false;
		
		int indexOfEqual = command.indexOf('=');
		
		if(indexOfEqual != -1) {
			isCcommand = is_C_CommandWhenEqualSignPresent();
		}
		else {
			isCcommand = is_C_CommandWhenEqualSignMissing();
		}
		
		return isCcommand;
	}
	
	private boolean is_C_CommandWhenEqualSignPresent() {
		boolean isCcommand = false;
		
		int indexOfSemicolon = command.indexOf(';');
		int indexOfEqualSign = command.indexOf('=');
		
		isCcommand = indexOfSemicolon == -1 || indexOfSemicolon > indexOfEqualSign;
	
		return isCcommand;
	}
	
	private boolean is_C_CommandWhenEqualSignMissing() {
		boolean isCcommand = false;
		
		if(command.indexOf(';') != -1) {
			isCcommand = true;
		}
		
		return isCcommand;
	}
	
	private boolean isCommandTypeL() {
		boolean isLabel = false;
		if(command.length() > 2 && command.charAt(0) == '(' && command.charAt(command.length() - 1) == ')') {
			isLabel = true;
		}
		return isLabel;
	}
	
	private boolean isCommandTypeComment() {
		boolean isComment = false;
		if(command.length() > 2 && command.substring(0, 2).matches("//")) {
			isComment =  true;
		}
		return isComment;
	}
	
	private boolean isCommandTypeWhitespace() {
		boolean isWhitespace = false;
		if(command.length() == 0) {
			isWhitespace = true;
		}
		return isWhitespace;
	}
}
