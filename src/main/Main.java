package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import binaryencoder.BinaryEncoder;
import binaryencoder.BinarySpec;
import fileio.FileIO;
import parser.Parser;
import translator.Translator;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\toivanov\\eclipse-workspace\\HackAssembler\\src\\prog.asm"));
		PrintWriter writer = new PrintWriter("C:\\Users\\toivanov\\eclipse-workspace\\HackAssembler\\src\\prog.hack");
		
		FileIO fileIO = new FileIO(reader, writer);
		BinaryEncoder binEncoder = new BinaryEncoder(new BinarySpec());
		
		Translator translator = new Translator(new Parser(), binEncoder, fileIO);
		
		translator.translate();
		
		reader.close();
		writer.close();
	}
}
