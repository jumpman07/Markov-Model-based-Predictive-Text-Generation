
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;
import java.io.*;
import java.lang.*;
import edu.duke.*;

public class MarkovRunnerWithInterface {
    
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        
        // Calls the methods for text generation
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        
        // Runs the text generator 3 times.
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            //printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        // Size of the text
        int size = 200;
        
        // Seed to create random object
        int seed = 531;
        
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        runModel(markov,st,size,seed);
        
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,seed);
        */

    }
    
    /*
    public void compareMethods(){
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        // Old Markov Model
        MarkovModel markov = new MarkovModel(2);
        runModel(markov,st,size,seed);
        System.out.println("Time taken by the Markov Model" + System.nanoTime());
        
        // Efficient Markov Model
        EfficientMarkovModel markovEff = new EfficientMarkovModel(2);
        runModel(markovEff,st,size,seed);
        System.out.println("Time taken by the Efficient Markov Model" + System.nanoTime());
    }
    */
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    /*
    public void testHashMap(){
        
        EfficientMarkovModel markov = new EfficientMarkovModel(2); // Order 2 efficient markov model
        String txt = "yes-this-is-a-thin-pretty-pink-thistle";
        markov.setRandom(42);
        markov.setTraining(txt);
        markov.printMapInfo();
    }
    */
}
