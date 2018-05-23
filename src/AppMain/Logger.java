package AppMain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	
	private String outputFile;
	private static PrintWriter out;
	private static FileOutputStream fo;
	
	//setup logger
	public Logger() {
		outputFile = "output.txt";
		try {
			out = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logToFile(String text) {
		out.print(text);
	}

	public void closeStream() {
		out.close();
	}
}
