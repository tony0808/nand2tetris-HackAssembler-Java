package fileio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	public FileIO(BufferedReader reader, PrintWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	public String readLine() throws IOException {
		return reader.readLine();
	}
	
	public void writeLine(String line) {
		writer.println(line);
	}
}
