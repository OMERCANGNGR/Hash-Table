import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {
    private int tableSize;
    int size;
    double loadFactor;
    HashEntry[] table;
    static boolean resetFlag;
    int collusioncount;
    public HashTable(){
        this.tableSize=997;
        this.loadFactor=0.0;
        this.size=0;
        this.resetFlag=false;
        this.collusioncount=0;

    }
    public void Reading(){
        try {
            collusioncount=0; // Zeroed because all data set read from begining
            resetFlag=false;  //In case of reset set the flag false in case it needed again
            File myObj = new File("C:\\Users\\ÖMER\\Desktop\\story.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String word[]= data.split(" ");
                for (int i=0;i<word.length;i++){
                    //  System.out.print(word[i]+" ");
                    if (word[i].equals("I")||word[i].equals("ı")||word[i].equals("ı—")||word[i].equals("i")) {
                        word[i].equals("i");
                    }
                    if(word[i].equals("")||word[i].equals(" ")){
                        //do nothing
                    }

                    else {
                        Value value = new Value(word[i].toLowerCase()); //Initialize a Value class object that gives -
                        indexingFunction(value);  //integer value of given string and
                    }                             //indexingFunction sends the words to indexing function
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int nextPrimeNumber(int size){
        int newSize=size*2;
        boolean flag=false;
        for (int i=2;i<=newSize/2;i++){
            if (newSize%i==0){
                flag=true;
            }
            if(i==newSize/2&&flag==false){
                break;
            }
            else if (i==newSize/2||flag==true){
                i=2;
                newSize++;
                flag=false;
            }
        }
        return newSize;
    }
    public void createTable(){    //Initialize the table
        table=new HashEntry[tableSize];
        if(resetFlag==false){  //ResetFlag return if loadFactor > 0.5 or 0.7
            for (int i=0;i<table.length;i++){
                table[i]=null;   //Set all indexes as null
            }
            Reading();  //Ready to filled
        }
        else {   //Proceed if loadFactor>0.5 or 0.7
            size=0;   //Table will be reload size  and table zeroed.
            for (int i=0;i<table.length;i++){
                table[i]=null;
            }
            Reading();
        }
    }
    public boolean bucketIsEmpty(int index){
        boolean isEmpty=false;      //  >Returns if the given index empty or not
        if(table[index]==null){     // |
            isEmpty=true;           // |
        }                          //  |
        return isEmpty;            //  |
    }
    public Value put(int index, Value value){ //Put function place the given value to given key index
        Value RemovedValue=null;
        RemovedValue=table[index].getValue();
        long key=value.getKey();
        table[index]=new HashEntry(key,value);
        return RemovedValue;
    }
    public void indexingFunction(Value value){ //Compression and indexing function also contains loadFactor control
        int index=0;
        long key=value.getKey(); //Integer values of the given strings created into value class its available directly here
        int valuedib=0;
        String word=value.getData();
        String wordAtIndex="";

            index=(int)((key)%table.length); //The index that data should be into
            if(bucketIsEmpty(index)){ //If the bucket null then place the value its own index
            table[index]=new HashEntry(key,value); //New entry
            size++; //Increase the number that shows how much data we have in our table

            }
            else if(!bucketIsEmpty(index)){   //If the index not null
                collusioncount++;             //That shows there is a collusion
                wordAtIndex=table[index].getValue().getData();
                if(wordAtIndex.equals(word)){   //Check if the given data placed before if true increase count of the data
                    table[index].getValue().increaseCount();
                }
                else if(!wordAtIndex.equals(word)){ //If the given data did not place before
                    Value valueToPlace=value;
                    Value tempV=null;
                    int j=0;
                    int normalIndex=0;
                    int bucketdib=0;
                    int bucketNormalIndex=0;
                    while(valueToPlace!=null){   //Keep progress until variables placed properly
                        normalIndex=(int)((key)%table.length);
                        index=(int)((key+j)%table.length);
                        //wordAtIndex=table[index].getValue().getData();
                        if(bucketIsEmpty(index)){  //If there is a empty bucket place the data into there
                            table[index]=new HashEntry(key,value);
                            size++;
                            valueToPlace=null;
                        }

                        else if (!bucketIsEmpty(index)){ //if bucket is not empty
                            wordAtIndex=table[index].getValue().getData();
                            bucketNormalIndex=(int)((table[index].getValue().getKey())%table.length);
                            bucketdib=index-bucketNormalIndex;  //Hold distance of inital bucket the index we check
                            valuedib=index-normalIndex;  //Hold distance of inital bucket the given data
                            if(wordAtIndex.equals(word)){ //While increase indexes we should check if data placed before
                                table[index].getValue().increaseCount();
                                break;
                            }
                            else{
                                if(valuedib<bucketdib){ //If the dib of the given data less than the data that already-
                                    tempV=table[index].getValue(); //placed into the index values change places-
                                    valueToPlace.setDib(valuedib); //Progress continuou until valueToPlace placed -
                                    put(index,valueToPlace);       // Into a null bucket
                                    valueToPlace=tempV;            //
                                }
                            }
                        }
                        j++; //Increase the value into modulus
                    }
                }
            }
        loadFactor=(double)size/(double)tableSize;
        if(loadFactor>0.7){ //If  %50 or %70 of the table is full than reset the table because there will be -
            int newsize=nextPrimeNumber(tableSize); // too many collisions
            this.tableSize=newsize;
            resetFlag=true; //CreateTable function checks if resetFlag true than createTable function resets some-
            createTable();  // important values.
        }
        //loadFacotr

    }
 public Value Valueget(String dataToSearch){ //It returnes the value with its all variables.
        Value toFind=new Value(dataToSearch);
        Value returned=null;
        long ind=toFind.getKey();
        for (int i=0;i<tableSize;i++){
            int index=(int)((ind+i)%tableSize); //Data key founded and compressed by tableSize and checks Linearly -
            if(table[index].getValue().getData().equals(dataToSearch)) //Until the data founded.
            {
                returned=table[index].getValue();
                break;
            }

        }
        return returned;
 }

    public int getCollusioncount() {
        return collusioncount;
    }

    public void setCollusioncount(int collusioncount) { //Holds how much collusion happened
        this.collusioncount = collusioncount;
    }
}
