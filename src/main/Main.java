package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import common.CommandType;

import parser.Parser;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\toivanov\\eclipse-workspace\\HackAssembler\\src\\prog.asm"));
		Parser parser = new Parser(br);
		
		while(parser.hasMoreCommands()) {
			parser.advance();
		
			switch(parser.getCommandType()) {
				case CommandType.A_COMMAND: System.out.println(parser.getSymbol()); break;
				case CommandType.L_COMMAND: System.out.println(parser.getSymbol()); break;
				case CommandType.C_COMMAND: System.out.println(parser.getDest() + "-" + parser.getComp() + "-" + parser.getJump());
				case CommandType.COMMENT:   break;
				case CommandType.UKNOWKN:   System.out.println("uknown command"); System.exit(1);
			}
		}
	}
}
