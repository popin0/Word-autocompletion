package com.trie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Khundongbam Popinjoy
 *
 */
public class Autocompletion {
	
	static final String store = "resources/words.txt";

	public static void main(String[] args) {
		System.out.println("In your service\n        work in progress....");
		long start = System.currentTimeMillis();
		Runtime runtime = Runtime.getRuntime();
		long free_mem_initial = runtime.freeMemory();
		Trie englishWords = new Trie();
		FileReader fin = null;
		try {
			fin = new FileReader(store);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Scanner src = new Scanner(fin);
	    src.useDelimiter("\n");
	    while(src.hasNext()){
	    	englishWords.insert(src.next());
	    }
	    src.close();
	    long end = System.currentTimeMillis();
	    System.out.println("Loading words in english completed in "+(end-start)+ " millisecs");
	    System.out.println("Memory req for the dictionary: "+(runtime.freeMemory() - free_mem_initial)/(1024*1024)+" MB");
	    Scanner scanner = new Scanner(System.in);
	    Scanner scanner2 = new Scanner(System.in);
	    int choice = 100;
	    while(choice != 0){
	    	System.out.println("Please select your choice as given below."
	    			+ "\n1: insert"
	    			+ "\n2: search"
	    			+ "\n3: is a prefix/word"
	    			+ "\n4: list all words for given input prefix"
	    			+ "\n0: close program");
	    	choice = scanner.nextInt();
	    	switch(choice){
	    	case 0:
	    		System.out.println("program closed");
	    		break;
	    	case 1:
	    		System.out.print("word to be inserted: ");
	    		englishWords.insert(scanner2.nextLine());
	    		System.out.println("inserted");
	    		break;
	    	case 2:
	    		System.out.print("word to search: ");
	    		if(englishWords.search(scanner2.nextLine()))
	    			System.out.println("valid word");
	    		else
	    			System.out.println("invalid word");
	    		break;
	    	case 3:
	    		System.out.print("prefix to lookup: ");
	    		if(englishWords.startsWith(scanner2.nextLine()))
	    			System.out.println("is a valid prefix/word");
	    		else
	    			System.out.println("is not a valid prefix/word");
	    		break;
	    	case 4:
	    		System.out.println("Enter prefix: ");
	    		ArrayList<String> result = englishWords.wordList(scanner2.nextLine());
	    		if(result != null){
	    			for(String str: result)
	    				System.out.println(str);
	    		}else{
	    			System.out.println("Input is not a prefix of an english word\n   at least not in my collection in words.txt");	    			
	    		}
	    		break;
	    	default:
	    		System.out.println("Invalid option");
	    		break;
	    	}
	    	
	    }
	    scanner.close();
	    scanner2.close();
	}

}
