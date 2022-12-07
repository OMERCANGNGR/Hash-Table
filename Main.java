import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //long nano_startTime = System.nanoTime();
        HashTable myTable=new HashTable();
        myTable.createTable();
        //long nano_endTime = System.nanoTime();
        Value deneme=null;
        //long nano_startTimeSearch = System.nanoTime();
        System.out.println("_______________________");
        try {
            File myObj = new File("C:\\Users\\ÖMER\\Desktop\\search.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String word[]= data.split(" ");
                for (int i=0;i<word.length;i++){
                    if(word[i].equals(" ")||word[i].equals("")){
                        //do nothing it s the end of the field.
                    }
                    else{
                        Value value = new Value(word[i].toLowerCase());
                        deneme=myTable.Valueget(word[i]);
                        System.out.println("Word: "+ deneme.getData());
                        System.out.println("Key: " + deneme.getKey());
                        System.out.println("Count: "+deneme.getCount());
                        System.out.println("_______________________");
                        }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //long nano_endTimeSearch = System.nanoTime();
        //System.out.println("Searchtime: "+(nano_endTimeSearch-nano_startTimeSearch));
        //System.out.println("Indexintime: "+(nano_endTime-nano_startTime));
        //System.out.println("CollusionCount: "+myTable.getCollusioncount());
        //You can change the key function that gives integer value of the given string into Value class constructor
        //Just take the other function into comment line !!!!!
    }
}
