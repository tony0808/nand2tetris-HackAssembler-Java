package translator;

import java.io.IOException;

import symboltable.SymbolEntry;
import symboltable.SymbolTable;
import binaryencoder.BinaryEncoder;
import fileio.FileIO;
import parser.Parser;
import common.AssemblySpecs;
import common.CommandType;
import common.SymbolType;
import common.Utility;


public class Translator {
	
	private BinaryEncoder binEncoder;
	private SymbolTable symTab;
	private Parser parser;
	private FileIO fileIO;
	
	private long current_variable_ram_address;
	private long current_command_address;
	
	public Translator(Parser parser, BinaryEncoder binEncoder, SymbolTable symTab, FileIO fileIO) {
		this.fileIO = fileIO;
		this.parser = parser;
		this.symTab = symTab;
		this.binEncoder = binEncoder;
		this.current_variable_ram_address = AssemblySpecs.STARTING_VARIABLE_RAM_ADDRESS;
		this.current_command_address = 0;
	}
	
	public void translate() throws IOException {
		first_pass();
		resetFileReader();
		second_pass();
	}
	
	private void first_pass() throws IOException {
		
		advance();
		
		while(hasMoreCommands()) {
			
			switch(getCommandType()) {
				case A_COMMAND:  advanceCommandAddress(); break;
				case C_COMMAND:  advanceCommandAddress(); break;
				case L_COMMAND:  execute_L_Command();     break;
				case COMMENT:    break;
				case WHITESPACE: break;
				case UKNOWKN:    handleUknownCommand();
				default: 		 handleDefaultCase();
			}
			
			advance();
		}
	}
	
	private void resetFileReader() throws IOException {
		fileIO.resetReader();
	}
	
	private void second_pass() throws IOException {
		
		advance();
		
		while(hasMoreCommands()) {
			
			switch(getCommandType()) {
				case A_COMMAND:  execute_A_Command(); break;
				case C_COMMAND:  execute_C_Command(); break;
				case L_COMMAND:  break;
				case COMMENT:    break;
				case WHITESPACE: break;
				case UKNOWKN:    handleUknownCommand();
				default: 		 handleDefaultCase();
			}
			
			advance();
		}
	}
	
	private void execute_L_Command() {
		symTab.addSymbol(parser.getSymbol(), SymbolType.LABEL, current_command_address);
	}
	
	private void execute_A_Command() {
		String binary;
		String symbol;
		
		symbol = parser.getSymbol();
		
		if(Utility.isSymbolDecimal(symbol)) {
			binary = getEncodedDecimal();
		}
		else {
			binary = getEncodedVariable();
		}
		
		fileIO.writeLine(binary);
	}
	
	private String getEncodedDecimal() {
		String symbol;
		int decimal;
	
		symbol = parser.getSymbol();
		decimal = Integer.valueOf(symbol);
		
		return binEncoder.encodeDecimal(decimal);
	}
	
	private String getEncodedVariable() {
		String symbol;
		long address;
		
		symbol = parser.getSymbol();
		
		if(symTab.contains(symbol)) {
			address = getAddressIfSymbolExists(symbol);
		}
		else {
			address = getAddressIfSymbolMissing(symbol);
		}
		
		return binEncoder.encodeDecimal(address);
	}
	
	private long getAddressIfSymbolExists(String symbol) {
		SymbolEntry entry = symTab.getEntry(symbol);
		return entry.getAddress();
	}
	
	private long getAddressIfSymbolMissing(String symbol) {
		symTab.addSymbol(symbol, SymbolType.VARIABLE, current_variable_ram_address++);
		return current_variable_ram_address - 1;
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
	
	private void handleUknownCommand() {
		System.out.println("uknown command"); 
		System.exit(1);
	}
	
	private void handleDefaultCase() {
		System.exit(1);
	}
	
	private void advance() throws IOException {
		String nextCommnad = fileIO.readLine();
		parser.setCommand(nextCommnad);
	}
	
	private CommandType getCommandType() {
		return parser.getCommandType();
	}
	
	private boolean hasMoreCommands() throws IOException {
		return parser.getCommand() != null;
	}
	
	private void advanceCommandAddress() {
		current_command_address++;
	}
}
