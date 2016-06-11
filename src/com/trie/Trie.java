package com.trie;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * reproduced and added to the original src
 * from http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 *
 */
class TrieNode {
    TrieNode[] arr;
    boolean isEnd;
    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
    }
 
}
 
public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.arr[index]==null){
                TrieNode temp = new TrieNode();
                p.arr[index]=temp;
                p = temp;
            }else{
                p=p.arr[index];
            }
        }
        p.isEnd=true;
    }
 
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.isEnd)
                return true;
        }
 
        return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }
 
    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }
    
    /**
     * @author Khundongbam Popinjoy
     * @param s
     * @return list of possible words with the given prefix
     */
    public ArrayList<String> wordList(String s){
    	TrieNode p = searchNode(s);
    	if(p == null) return null;
    	ArrayList<String> list = new ArrayList<>();
    	LinkedList<MyMap> Q = new LinkedList<>();
    	Q.push(new MyMap(p,s));
    	while(Q.size() > 0){
    		MyMap myMap = Q.pop();
    		String str = myMap.str;
    		TrieNode node = myMap.node;
    		if(node.isEnd) list.add(str);
    		for(int i= 0; i<26; i++){
    			if(node.arr[i] != null){
    				Q.addLast(new MyMap(node.arr[i], str+(char)('a'+i)));
    			}
    		}
    	}
    	return list;
    }
    
    public class MyMap{
    	private String str;
    	private TrieNode node;
    	
    	MyMap(TrieNode n, String s){
    		str = s;
    		node = n;
    	}
    }
}