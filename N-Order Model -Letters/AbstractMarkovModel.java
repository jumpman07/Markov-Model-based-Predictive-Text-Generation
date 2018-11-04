
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * Super class to implement Markov Model of any order.
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;
import java.io.*;
import java.lang.*;
import edu.duke.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
        
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        // Returns a list of letters that follow the given key
        
        ArrayList<String> follows = new ArrayList<String>();
        int keyLength = key.length();
        
        for (int i = 0; i < myText.length()-keyLength; i++){
            
            // If the key is found in myText, add the letter following it to the list
            if (key.equals(myText.substring(i,i+keyLength)))
                follows.add(myText.substring(i+keyLength,i+keyLength+1));
        }
        
        return follows;
    }
    
    public void toString(int n){
        System.out.println("Markov model of order " + n); 
    }
}
