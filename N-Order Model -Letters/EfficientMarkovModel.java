
/**
 * Write a description of EfficientMarkovModel here.
 * Generates predictive text using N words, where N is the order of the Model.
 * Improves the efficiency by creating a HashMap of length N keys and list of letters following the key.
 * Building this HashMap before-hand prevents revisiting each key (group of N letters) repeatedly to find the letter following them
 * @Shreyas Mushrif 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private HashMap<String,ArrayList<String>> map;
    int order; // Refers to the number of letters used as a key to predict the next letter
    
    // Constructor
    public EfficientMarkovModel(int n){
        map = new HashMap<String,ArrayList<String>>();
        order = n;         // Order of the model
    }
    
    private void buildMap(){
        
        for (int i = 0; i < myText.length() - order; i++){
            String key = myText.substring(i,i+order);  // its length will be same as that or order.
            String nextLetter = myText.substring(i+order, i+order+1);
            
            if (map.containsKey(key)) // If the key is present, add the following letter to the arraylist 
                map.get(key).add(nextLetter);
            else{                     // Create a new list for that key and add the nextLetter to it
                ArrayList<String> list = new ArrayList<String>();
                list.add(nextLetter);
                map.put(key,list);
            }
        }
    }
    
    public ArrayList<String> getFollows(String key){
        
        // Get the arraylist corresponding to the key from map
        return map.get(key);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        
        // Create a map of keys and the lists of characters following them
        buildMap();
        
        //Print the map info
        printMapInfo();
    }
    
    public String getRandomText(int numChars){
        
        // if input text is empty, return an empty string
        if (myText == null)
            return "";
        
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length()-order); // Choose a random number from a sequence of myText.length()-1 random numbers
        String key = myText.substring(idx,idx + order);      // Choose the next character to the character at idx as the key
        sb.append(key);
        
        for (int i = 0; i < numChars - key.length(); i++){
            ArrayList<String> follows = getFollows(key);
            
            // Break if the current key has no letter(s) following it.
            if (follows.size() == 0)
                break;
            
            // Get the next random character 
            String nextRand = follows.get(myRandom.nextInt(follows.size()));
            
            // Append it to the output
            sb.append(nextRand);
            
            // Update the key
            key = key.substring(1) + nextRand;
        } 
        
        return sb.toString();
    }
    
    private int largestVal(){
        
        int maxSize = 0;
        for (String key : map.keySet()){
            if (map.get(key).size() > maxSize)
                maxSize = map.get(key).size();
        }
        
        return maxSize;
    }
    
    private void printKeysWithVal(int val){
        
        for (String key : map.keySet()){
            if (map.get(key).size() == val)
                System.out.print(key + " ");
        }
    }
    
    private void printMapInfo(){
        
        System.out.println("Size of the map = " + map.size());
        
        int maxSize = largestVal(); // Gets the maximum size of arraylist
        System.out.println("Size of largest value in the map = " + maxSize);
        
        /*
        // Print keys with the maximum value
        System.out.println(" Keys that have the maximum value are: ");
        printKeysWithVal(maxSize);
        */
    }
    
    public String toString(){
        return "Efficient Markov Model order " + order;
    }

}
