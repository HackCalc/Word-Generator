package wordGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class WordGenerator {

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();

		char reqChar = 'n';
		HashSet<Character> allowedChars = new HashSet<Character>();
		allowedChars.add('r');
		allowedChars.add('o');
		allowedChars.add('g');
		allowedChars.add('l');
		allowedChars.add('a');
		allowedChars.add('t');
		allowedChars = addAccents(allowedChars);
		
		String dicPath = "C:\\wordlist.txt";
		generateWords(allowedChars, reqChar, dicPath);
		
		System.out.println(System.nanoTime() - startTime);

	}

	/**
	 * Given a Dictionary the method search for words that have the required chars and, in addition, they are only formed by allowed chars. 
	 * @param optChars
	 * @param reqChar
	 * @param path
	 * @throws IOException
	 */
	private static void generateWords(HashSet<Character> allowedChars, char reqChar, String path) throws IOException {
		BufferedReader bufReader = new BufferedReader(
				new FileReader(path));

		String line = bufReader.readLine();
		HashSet<String> usedWords = new HashSet<String>();
		while (line != null) {
			if (!usedWords.contains(line) && isValidWord(line, allowedChars, reqChar)) {
				System.out.println(line);
				usedWords.add(line);//keep the word in case of the dictionary has repetitions
			}

			line = bufReader.readLine();
		}

		bufReader.close();
	}

	/**
	 * return true only if the word length is more then 3 chars, have the required char and it's formed only by allowed chars  
	 * @param line
	 * @param allowedChars
	 * @param reqChar
	 * @return
	 */
	public static boolean isValidWord(String line, HashSet<Character> allowedChars, char reqChar) {
		int paraulaLength = line.length();

		if (paraulaLength >= 3) {
			int indexObligatori = line.indexOf(reqChar);

			if (indexObligatori >= 0) {

				for (int i = 0; i < paraulaLength; i++) {
					
					if (i != indexObligatori && !allowedChars.contains(line.charAt(i))) {//Skip the required char
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		return true;//return true only if passes all filters
	}

	/**
	 * Adds accents to allowed vowels 
	 * @param allowedChars
	 * @return
	 */
	public static HashSet<Character> addAccents(HashSet<Character> allowedChars) {
		HashSet<Character> result = new HashSet<Character>(allowedChars);

		if (allowedChars.contains('a')) {
			result.add('à');
		}
		if (allowedChars.contains('e')) {
			result.add('è');
			result.add('é');
		}
		if (allowedChars.contains('i')) {
			result.add('í');
		}
		if (allowedChars.contains('o')) {
			result.add('ò');
			result.add('ó');
		}
		if (allowedChars.contains('u')) {
			result.add('ú');
		}
		return result;
	}

}
