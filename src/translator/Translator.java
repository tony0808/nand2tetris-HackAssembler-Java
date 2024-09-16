package translator;

import java.io.IOException;

import parser.Parser;

import binaryencoder.BinaryEncoder;
import common.CommandType;
import fileio.FileIO;

public class Translator {
	
	private BinaryEncoder binEncoder;
	private Parser parser;
	private FileIO fileIO;
	
	private boolean firstAdvanceFlag;
	private String command;
	
	public Translator(Parser parser, BinaryEncoder binEncoder, FileIO fileIO) {
		this.fileIO = fileIO;
		this.parser = parser;
		this.binEncoder = binEncoder;
	}
	
	public void translate() throws IOException {
		
		while(hasMoreCommands()) {
			
			setCommand();
			
			switch(getCommandType()) {
				case A_COMMAND:  execute_A_Command(); break;
				case C_COMMAND:  execute_C_Command(); break;
				case COMMENT:    break;
				case WHITESPACE: break;
				case UKNOWKN:    handleUknownCommand();
				default: 		 handleDefaultCase();
			}
			
			advance();
		}
	}
	
	private void handleDefaultCase() {
		System.exit(1);
	}
	
	private void execute_A_Command() {
		String symbol, binary;
		int decimal;
		
		symbol = parser.getSymbol();
		decimal = Integer.valueOf(symbol);
		binary = binEncoder.encode_A_command(decimal);
		
		fileIO.writeLine(binary);
	}
	
	private void execute_C_Command() {
		String dest, comp, jump;
		String binary;
		
		dest = parser.getDest();
		comp = parser.getComp();
		jump = parser.getJump();
		
		binary = binEncoder.encode_C_Commad(dest, comp, jump);
		
		fileIO.writeLine(binary);
	}
	
	private void execute_L_Command() {
		
	}
	
	private void handleUknownCommand() {
		System.out.println("uknown command: " + parser.command); 
		System.exit(1);
	}
	
	private void advance() throws IOException {
		command = fileIO.readLine();
	}
	
	private CommandType getCommandType() {
		return parser.getCommandType();
	}
	
	private boolean hasMoreCommands() throws IOException {
		
		if(!firstAdvanceFlag) {
			advance();
			firstAdvanceFlag = true;
		}
		
		return command != null;
	}
	
	private void setCommand() {
		parser.setCommand(command);
	}
}
