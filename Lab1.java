
/**
 *
 * @author David Schwehr <dpschwehr[at]gmail[.com]> github: dssquared
 * @author Tim Wegher
 * @brief java program used to parse information from a memory dump file
 *        looking for matches from credit card reader stream.
 *        Lab1 for Computer Security Spring 2016 MSU-Bozeman
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String fileName = "memorydump.dmp";// File path to memorydump file
        String readString = "";// One line of memory dump to append to string builder
        String regex = "(%[A-Z]|;)([0-9]{1,19})(?:\\^([^\\^]{2,26})\\^|=)([0-9]{4}|\\^)([0-9]{3}|\\^)([^\\?]+)\\?";// Regex to look for matching credit card info string
        StringBuilder builder = new StringBuilder();// One giant string created from memory dump file

        try{
            Scanner reader = new Scanner(new BufferedReader(new FileReader(fileName)));
            // Build giant string from memory dump file
            while(reader.hasNext()){
                readString = reader.nextLine();
                //System.out.println(readString);// DEBUG
                builder.append(readString);
            }
            //System.out.print(builder);// DEBUG

            reader.close();
        } catch (Exception e){
            if(e instanceof FileNotFoundException ){
                System.out.println("File not Found");
            }else if(e instanceof IOException){
                e.printStackTrace();
            }
        }

        dataCollector(regex, builder);
    }// End main()

    /**
     * dataCollector takes the raw data and using regex finds credit cards storing them in the cards ArrayList. Then it runs through the cards ArrayList print each.
     * @param regEx the regular expression needed to search for credit card info
     * @param data  the string to be searched by the Matcher and Pattern classes
     *              of the java regex library
     * @return boolean returns true if there are matches found. System prints results.
     */
    private static boolean dataCollector(String localRegex, StringBuilder data){
        ArrayList<Card> cards = new ArrayList<Card>();
        Pattern pattern = Pattern.compile(localRegex);
        Matcher matcher = pattern.matcher(data);
        String matchingString;
        boolean found = false;
        while(matcher.find()){
            found = true;
            //System.out.println(matcher.group();); // DEBUG

            // Make the card object
            Card card = new Card(matcher.group(1).length() == 2 ? 1 : 2, matcher.group(), matcher.group(1).substring(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6));
            cards.add(card);
        }
        // Print all found cards
        if(found){
            System.out.printf("There is %d credit card records in the memory data%n", cards.size());
            for (int i = 0; i < cards.size(); i++) {
                System.out.printf("<Information of card %d>%n", i+1);// Offset i by 1
                cards.get(i).print();
            }
        }
        return found;
    }// end dataCollector()

}// End class
