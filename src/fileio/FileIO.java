package fileio;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class FileIO {

	private RandomAccessFile reader;
	private PrintWriter writer;
	
	public FileIO(RandomAccessFile reader, PrintWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	public void resetReader() throws IOException {
		reader.seek(0);
	}
	
	public String readLine() throws IOException {
		return reader.readLine();
	}
	
	public void writeLine(String line) {
		writer.println(line);
	}
}
