package parser;

import common.AssemblySpecs;
import common.CommandType;

public class Parser {
	
	public String command;
	
	public void setCommand(String command) {
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
		return command.matches(AssemblySpecs.A_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeC() {
		return command.matches(AssemblySpecs.C_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeL() {
		return command.matches(AssemblySpecs.L_COMMAND_PATTERN);
	}
	
	private boolean isCommandTypeComment() {
		boolean isComment = false;
		if(command.length() > 2 && command.substring(0, 2).matches("//")) {
			isComment =  true;
		}
		return isComment;
	}
	
	private boolean isCommandTypeWhitespace() {
		return command.matches(AssemblySpecs.WHITETSPACE_PATTERN);
	}
}
