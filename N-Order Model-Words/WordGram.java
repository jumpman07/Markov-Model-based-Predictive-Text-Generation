/*
 * WordGram is similar to the String class where words are grouped together 
 * to form a String-like object.
 * Contains methods like .wordAt(), .length(), .equals(), .toString(), .hashCode()
 * which function similar to corresponding methods in the String class.
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import edu.duke.*;

public class WordGram {
    private String[] myWords;
    private int myHash;
    
    // Constructor
    public WordGram(String[] source, int start, int size) {
        
        // Creates a String array of words from start of given size
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size); 
    }

    public String wordAt(int index) {
        
        // Returns the word at the given index. Throws IndexOutOfBoundsException for invalid index
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        
        return myWords.length; // Returns length of the array
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < myWords.length; i++)
            ret += myWords[i] + " ";

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        // For two wordgrams to be equal their length should be the same
        if (this.length()!= other.length())
            return false;
        
        for (int i=0; i < myWords.length; i++){
            if (!myWords[i].equals(other.wordAt(i)))
                return false;
        } 
        return true;
    }

    public WordGram shiftAdd(String word) { 
        //WordGram out = new WordGram(myWords, 0, myWords.length);
        
        String[] words = new String[myWords.length];
        for (int i = 0; i < words.length-1; i++){
            words[i] = this.wordAt(i+1);
        }
        words[words.length-1] = word;
        
        return new WordGram(words,0,words.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
    }
    
    public int hashCode(){
        
        //Convert the WordGram to a string
        String st = this.toString();
        return st.hashCode();
    }
}