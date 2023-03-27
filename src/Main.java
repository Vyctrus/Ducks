import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void readExcelData(){
        int duckNumber=0;
        int[][] ducks;
        int rowSize=0;

        try{
            File myObj = new File("name_file.csv");
            Scanner myReader = new Scanner(myObj);
            if(myReader.hasNextLine()){
                duckNumber= myReader.nextInt();
                rowSize= myReader.nextInt();
                myReader.nextLine();
            }
            ducks=new int[duckNumber][2];//ktora kacza, jaka wartosc
            int tempI=0;
            while(myReader.hasNextLine()){
                ducks[tempI][0]=myReader.nextInt();
                ducks[tempI][1]=myReader.nextInt();
                tempI++;//change to for loop
                myReader.nextLine();
            }
            myReader.close();//end function after this loop
            //printDuckValues(ducks);
            doTheCalculations(ducks,duckNumber, rowSize);
        }catch(FileNotFoundException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static boolean extractValueAtPosition(int intRepresentation,int position){
        return ((intRepresentation) & (1 << (position))) !=0;
    }

    public static void doTheCalculations(int ducks[][], int duckNumber, int rowSize){
        int bestSumHeights=0;
        int bestMask=0;
        int mask=0;
        int bigNumber=(int)Math.pow(2,duckNumber);
        while(mask<bigNumber){
            int tempSumHeights=0;
            int tempSumCost=0;
            boolean validMask=true;

            for(int i=0;i<duckNumber;i++){
                if(extractValueAtPosition(mask,i)){
                    if(tempSumCost+ducks[i][1]>rowSize){
                        validMask=false;
                        break;
                    }
                    tempSumHeights+=ducks[i][0];
                    tempSumCost+=ducks[i][1];
                }
            }

            if(bestSumHeights<tempSumHeights && validMask){
                bestSumHeights=tempSumHeights;
                bestMask=mask;
            }

            mask++;
        }
        //System.out.println("Best sum is : "+bestSumHeights);
        //System.out.println("Mask is : "+bestMask);
        System.out.println(bestSumHeights);
        //only for tests
        //calculatePseudoOptimal(ducks,duckNumber,rowSize);//45
    }

    public static void calculatePseudoOptimal(int ducks[][], int duckNumber, int rowSize){
        ArrayList<Duck> tempTab= new ArrayList<Duck>(duckNumber);
        for(int i=0;i<duckNumber;i++){
            tempTab.add(new Duck(ducks[i][0],ducks[i][1],i));
        }
        Collections.sort(tempTab);
        int tempSum=0;
        int tempLength=0;
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        for(int j=0;j<duckNumber;j++){
            if(tempLength+tempTab.get(j).weight<rowSize){
                tempSum+=tempTab.get(j).height;
                tempLength+=tempTab.get(j).weight;
                indexes.add(tempTab.get(j).startingIndex);
            }
        }
        System.out.println("Pseudo optimal sum is : "+tempSum);
        System.out.println(indexes);
    }

    public static void printDuckValues(int ducks[][]){
        for(int singleDuck[]: ducks){
            System.out.println(singleDuck[0]+" "+singleDuck[1]);
        }
    }

    public static void main(String[] args) {
        readExcelData();//brut force ;/
    }
}