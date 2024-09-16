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
				case CommandType.A_COMMAND: execute_A_Command(); break;
				case CommandType.C_COMMAND: execute_C_Command(); break;
				case CommandType.L_COMMAND: execute_L_Command(); break;
				case CommandType.COMMENT:   break;
				case CommandType.UKNOWKN:   handleUknownCommand();
			}
			
			advance();
		}
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
		System.out.println("uknown command"); 
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
			firstAdvanceFlag = false;
		}
		
		return command != null;
	}
	
	private void setCommand() {
		parser.setCommand(command);
	}
}
