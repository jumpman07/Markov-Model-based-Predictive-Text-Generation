
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import java.lang.*;
import edu.duke.*;

public class EfficientMarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map; // Stores wordGram object and the corresponding list of 
                                                     // words that follow it.
    
    public EfficientMarkovWord(int n){
        myOrder = n;
        map = new HashMap<WordGram,ArrayList<String>> ();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        System.out.println("Printing the hashmap info: ");
        printHashMapInfo();	
    }
    
    public String getRandomText(int numWords){
        
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length - myOrder); // Random word to start with
        WordGram kGram = new WordGram(myText,idx,myOrder);
        
        sb.append(kGram.toString()).append(" ");
        
        for (int i = 0; i < numWords-1; i++){
            
            ArrayList<String> follows = getFollows(kGram); // Get a list of words that follow kGram
            
            // If follows is empty, exit
            if (follows.size() == 0)
                break;
            
            // Choose next word from the words in "follows"
            idx = myRandom.nextInt(follows.size());
            String next = follows.get(idx);
            sb.append(next).append(" ");
            
            // Update kGram by inserting next in the last index(of kGram) and removing the first element
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private void buildMap(){
        
        for (int i = 0; i < myText.length - myOrder; i++){
            
            WordGram kGram = new WordGram(myText,i,myOrder); // Creates a wordgram of size myOrder from the ith position in myText
            String next = myText[i + kGram.length()]; // Get the following word.
            
            if (map.containsKey(kGram)) // If the kGram already exists
                map.get(kGram).add(next); // Add the next word to the arraylist of words
            else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                map.put(kGram,list);
            }
        }
        
        // Add an empty list for the last words in myText
        WordGram kGram = new WordGram(myText, myText.length - myOrder,myOrder);
        if (!map.containsKey(kGram)){
            ArrayList<String> list = new ArrayList<String>();
            map.put(kGram,list);
        }  
    }
    
    private void printHashMapInfo(){
        
        System.out.println("Number of keys in the hashmap " + map.size());
        
        // Get the largest value in the hashmap and the key with largest value
        int maxSize = 0;
        String largestKey = "";
        for (WordGram kGram: map.keySet()){
            if (map.get(kGram).size() > maxSize)
                maxSize = map.get(kGram).size();
                largestKey = kGram.toString();
        }
        System.out.println("Size of the largest key " + maxSize);
        System.out.println("Key with the largest value " + largestKey);
        
    }
    
    private ArrayList<String> getFollows(WordGram kGram){
        return map.get(kGram);
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        
        // Returns the index in the array words from which the words in target start matching, else returns -1.
        for (int i = start; i < words.length - target.length(); i++){
            
            if (words[i].equals(target.wordAt(0))){
                boolean found = true;
                for (int j = 1; j < target.length();j++){
                    if (!words[i+j].equals(target.wordAt(j))){
                        found = false;
                        break;
                    }
                }
                
                if (found)
                    return i;
            }
            
        }
        
        return -1;
    }
}
