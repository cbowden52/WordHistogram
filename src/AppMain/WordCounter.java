package AppMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

	private static Map<String, Integer> wordHash = new HashMap<String, Integer>();
	private static int maxWordLength, totalWords = 0;
	private static String[] punctuation = {",", ".", "!", "?", "(", ")"};
	private static File inputFile;
	private static final Logger log = new Logger();
	
	//When logging to output.txt before output.txt is created, must refresh to see the file appear
	public static void main(String[] args) {
		readInput();
		getMaxWordLength();
		printOutput();
		log.closeStream();
	}
	
	/* Reads the file and invokes the 
	 * updateWordHash method for each word read
	 */
	public static void readInput() {
		inputFile = new File("input.txt");
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String word;
		while(scanner.hasNext()) {
			word = scanner.next().toLowerCase();
			for(String x : punctuation) {
				word = word.replace(x, "");
			}
			updateWordHash(word);
		}
		
		scanner.close();
	}
	
	/*Adds the word to the hash map if doesnt already exist
	 * otherwise adds 1 to that words' key value
	 */
	public static void updateWordHash(String word) {
		if(wordHash.containsKey(word)) {
			wordHash.put(word, wordHash.get(word) + 1);
		} else {
			wordHash.put(word, 1);
		}
		totalWords++;
	}
	
	//formats the text and calls the log
	public static void printOutput() {
		for(String word : wordHash.keySet()) {
			for(int i = 0; i < (maxWordLength - word.length()); i++) {
				log.logToFile(" ");
			}
			log.logToFile(word + "	| ");
			System.out.print(word + "	| ");
			float perTotal = ((float) wordHash.get(word) / totalWords) * 100;
			for(int i = 0; i < perTotal; i++) {
				System.out.print("=");
				log.logToFile("=");
			}
			System.out.println(" (" + wordHash.get(word) + ")");
			log.logToFile(" (" + wordHash.get(word) + ")\n");
		}
	}
	
	//used for better formatting to .txt file
	public static void getMaxWordLength() {
		for(String word : wordHash.keySet()) {
			if(word.length() > maxWordLength) {
				maxWordLength = word.length();
			}
		}
	}
}
