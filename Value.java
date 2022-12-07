public class Value<K extends String> {
    K data;
    int count;
    int dib;
    long key;
    public Value(K data){
        this.data=data;
        this.count=1;
        //this.key=polynomialFunction(data); //Given polynomial accumulation Function
        this.key=myOwnHashFunction(data); //my own polynomial accumulation Funtion
    }  //Change the function into comment line if you look the other polynomialAccumulationFunciton perform

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDib() {
        return dib;
    }

    public void setDib(int dib) {
        this.dib = dib;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
    public long polynomialFunction(K data){

        long convertedValue=0;
        long first=0;
        long place=0;
        char alphabet[]=new char[26];
        alphabet=new char[26];
        int index=0;
        for (int i=97;i<123;i++){  //Initializing of alphabeth with ascıı values
            alphabet[index]=(char)i;
            index++;
        }
        int length= data.length();
        for (int i =0;i<length;i++){
            for (int j=0;j<26;j++){
                if(alphabet[j]==data.charAt(i)){ //Gives order of the letter
                    //temp=(int)word.charAt(i);
                    place=j+1;
                    break;
                }
            }
            first= (int) (place*Math.pow(37,length-i-1));
            convertedValue=convertedValue+first; //h1(x) str->int
        }
        return convertedValue;
    }
    public void increaseCount(){
        this.count++;
    } //Word counter
    public long myOwnHashFunction(K data){
        long convertedValue=0;
        long first=0;
        long place=0;
        char alphabet[]=new char[26];
        alphabet=new char[26];
        int index=0;
        for (int i=97;i<123;i++){ //Initializing alphabeth with ascii values
            alphabet[index]=(char)i;
            index++;
        }
        for (int j =0;j<data.length();j++){
            char character = data.charAt(j);   //Progress goes on char by char
            int ascii = (int) character;       //Takes the ascii of char
            int modulus=ascii%2;               //Modulus hold if ascii divided by 2
            int secondmodulus=ascii%3;         //Secondmodulus hold if ascii divided by 3
            int ones,tens,hundreds=0;
            int comlepentBasement=0;           //complement holds the sum of basements of the ascii value ex(116 1+1+6)
            int temp=0;
            if(ascii>=100){
                ones=(ascii%100)%10;
                tens=(ascii%100-ascii%10)/10;
                hundreds=ascii/100;
                comlepentBasement=ones+tens+hundreds;   //Sum bases
            }
            else{
                ones=ascii%10;
                tens=ascii/10;
                comlepentBasement=ones+tens;
            }
            if (modulus==0){             //If divided by two
                if (secondmodulus==0){    //If divided by three
                    temp=ascii+comlepentBasement*3;
                }
                else{                         //If If divided by two and divided by three
                    temp=ascii+comlepentBasement*5;
                }
            }
            else {
                if (secondmodulus==0){     //If not divided by two and divided by three
                    temp=ascii+comlepentBasement*7;
                }
                else{                      //If If not divided by two and  not divided by three
                    temp=ascii+comlepentBasement*11;
                }
            }
            first= (int) (temp*Math.pow(37,data.length()-j-1));
            convertedValue=convertedValue+first; //h1(x) str->int
        }
        return convertedValue;
    }
}
