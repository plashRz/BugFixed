
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Created a file to store Expenses
        File exp = new File("src/expenses.txt");
        if(exp.exists()){
            System.out.println("Expence file loaded!");
        } else {
            exp.createNewFile();
            System.out.println(exp.getName()+" --"+exp.getAbsolutePath());
        }
        //To read from that expense file and put it in Arraylist for further operations.
        Scanner reader = new Scanner(exp);
        ArrayList<Integer> expenses = new ArrayList<Integer>();

        while (reader.hasNextInt()){
            expenses.add(reader.nextInt());
        }
        reader.close();


        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");

        optionsSelection(exp,expenses);

    }
    private static void optionsSelection(File exp, ArrayList<Integer> expenses) throws IOException {

        String[] arr = {"1. I wish to review my expenditure",
                "2. I wish to add my expenditure",
                "3. I wish to delete my expenditure",
                "4. I wish to sort the expenditures",
                "5. I wish to search for a particular expenditure",
                "6. Close the application"
        };

        for(int i=0; i<6;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }

        System.out.println("\nEnter your choice:\t");
        Scanner sc = new Scanner(System.in);
        int  options =  sc.nextInt();
        for(int j=1;j<=6;j++){
            if(options==j){
                switch (options){
                    case 1:

                        System.out.println("Your saved expenses are listed below: \n");
                        System.out.println(expenses+"\n");
                        optionsSelection(exp, expenses);
                        break;
                    case 2:
                        System.out.println("Enter the value to add your Expense: \n");
                        int value = sc.nextInt();
                        //added entry to the file
                        FileWriter fw = new FileWriter(exp,true);
                        fw.write("\n"+value);
                        fw.close();
                        //add to the arraylist
                        expenses.add(value);
                        System.out.println("Your value is updated\n");
                        //displayed result to user
                        System.out.println("\n"+expenses);
                        optionsSelection(exp, expenses);

                        break;
                    case 3:
                        System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
                        int con_choice = sc.nextInt();
                        if(con_choice==options){
                            expenses.clear();
                            System.out.println(expenses+"\n");
                            fw = new FileWriter(exp);
                            fw.write("");
                            fw.close();
                            System.out.println("All your expenses are erased!\n");
                        } else {
                            System.out.println("Oops... try again!\n");
                        }
                        optionsSelection(exp, expenses);
                        break;
                    case 4:
                        expenses = sortExpenses(expenses);
                        System.out.println(expenses+"\n");
                        optionsSelection(exp, expenses);
                        break;
                    case 5:
                        searchExpenses(expenses);
                        optionsSelection(exp, expenses);
                        break;
                    case 6:
                        closeApp();
                        break;
                    default:
                        System.out.println("You have made an invalid choice!\n");
                        break;
                }
            }
        }

    }
    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
    }
    private static void searchExpenses(ArrayList<Integer> arrayList) {

        System.out.println("Enter the expense you need to search:\t");
        int item = new Scanner(System.in).nextInt();

        if(arrayList.contains(item)){
            System.out.println("\nThe requested transaction of $" +item +
                    " is done at: "+arrayList.indexOf(item)+"th place! \n\n");

        } else {
            System.out.println("\ntransaction not found!\n\n");
        }

    }
    private static ArrayList<Integer> sortExpenses(ArrayList<Integer> arrayList) {

        //created a new comparator which compares the elements
        //if +ve value - it will return o1 ie element first hence the sorting is done in ascending order
        //for descending values we simply need to switch object ordering (o2-o1)
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        return arrayList;

    }
}