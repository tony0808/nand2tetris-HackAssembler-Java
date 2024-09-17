package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import symboltable.SymbolTable;
import binaryencoder.BinaryEncoder;
import binaryencoder.BinarySpec;
import fileio.FileIO;
import parser.Parser;
import translator.Translator;

public class Main {
	
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\nand2tetris-JavaImpl\\src\\prog.asm");
		
		RandomAccessFile reader = new RandomAccessFile(file, "r");
		PrintWriter writer = new PrintWriter("C:\\Users\\Admin\\eclipse-workspace\\nand2tetris-JavaImpl\\src\\prog.hack");
		
		Translator translator = new Translator(
										new Parser(), 
										new BinaryEncoder(new BinarySpec()), 
										new SymbolTable(),
										new FileIO(reader, writer)
									);
		
		translator.translate();
		
		reader.close();
		writer.close();
	}
}
