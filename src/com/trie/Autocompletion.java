package com.trie;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
//	    String temp;
	    while(src.hasNext()){
//	    	temp = src.next();
//	    	System.out.println(temp);
	    	englishWords.insert(src.next());
	    }
	    src.close();
	    Scanner scanner = new Scanner(System.in);
	    Scanner scanner2 = new Scanner(System.in);
	    int choice = 100;
	    while(choice != 0){
	    	System.out.println("Please select your choice as given below."
	    			+ "\ninsert -- 1"
	    			+ "\nsearch -- 2"
	    			+ "\nstarts with -- 3"
	    			+ "\nclose program -- 0");
	    	choice = scanner.nextInt();
	    	switch(choice){
	    	case 0:
	    		System.out.println("closing program");
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
	    			System.out.println("can be completed to a valid word");
	    		else
	    			System.out.println("cannot be completed to a valid word");
	    		break;
	    	default:
	    		System.out.println("Invalid option");
	    		break;
	    	}
	    	
	    }
	    scanner.close();
	    scanner2.close();
	    long end = System.currentTimeMillis();
	    System.out.println("Loading completed in "+(end-start)+ " millisecs");
	    System.out.println("Memory req for the dictionary: "+(runtime.freeMemory() - free_mem_initial));
	    

	}

}
