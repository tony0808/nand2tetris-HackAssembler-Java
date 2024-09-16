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
		
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\eclipse-workspace\\nand2tetris-JavaImpl\\src\\test.asm"));
		PrintWriter writer = new PrintWriter("C:\\Users\\Admin\\eclipse-workspace\\nand2tetris-JavaImpl\\src\\test.hack");
		
		Translator translator = new Translator(
										new Parser(), 
										new BinaryEncoder(new BinarySpec()), 
										new FileIO(reader, writer)
									);
		
		translator.translate();
		
		reader.close();
		writer.close();
	}
}
