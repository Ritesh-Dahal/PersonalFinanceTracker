package differentmethods;


import FileSerializing.TransactionOperationOnFile;
import exceptionhandeling.InputException;
import fileworks.Logic;
import fileworks.Transaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClassContainingMethods
{

    Scanner sc = new Scanner(System.in);
     Logic   logic = new Logic();
    TransactionOperationOnFile operation = new TransactionOperationOnFile();


    public void idGenerator(){
        logic.id();
    }



    public void  addTransaction() {
        Integer transactionChoice = 0;

        try {
            do {



                System.out.println("============================================");
                System.out.println("Select The Transaction Type:");
                System.out.println();
                System.out.println("1.Income");
                System.out.println("2.Expense");
                System.out.println("3.Back");
                System.out.println();

                try {
                    System.out.println("Choose number according to your requirement");
                    transactionChoice = sc.nextInt();
                }catch (InputMismatchException e)
                {
                    throw new InputException("Invalid Input Only Numbers Are Allowed", "ClassContainingMethods", "addTransaction");
                }
                System.out.println("============================================");

                switch (transactionChoice) {
                    case 1: {
                        Integer incomeChoice = 0;
                        try {
                            do {
                                System.out.println("============================================");
                                System.out.println("Select The Income Type:");
                                System.out.println();
                                System.out.println("1.Salary");
                                System.out.println("2.Loan");
                                System.out.println("3.Back");
                                System.out.println();

                                try {
                                    System.out.println("Choose number according to your requirement");
                                    incomeChoice = sc.nextInt();
                                }catch(InputMismatchException e)
                                    {
                                        throw new InputException("Invalid Input Only Numbers Are Allowed", "ClassContainingMethods", "addTransaction");
                                    }
                                System.out.println("============================================");
                                switch (incomeChoice) {
                                    case 1:
                                        logic.income("Salary");
                                        break;
                                    case 2:
                                        logic.income("Loan");
                                        break;
                                    case 3:
                                        System.out.println("!! Exiting Successfully !!");
                                        break;
                                    default:
                                        System.out.println("!! Please enter a valid number !!");
                                        break;
                                }

                            } while (incomeChoice != 3);
                        }catch(InputException i){
                            System.out.println("Error: "+i.getMessage() +" || At Class: "+ i.getClassName() +" || At Method: "+ i.getMethodName());
                            sc.nextLine();
                        }
                        break;

                    }
                    case 2: {

                        Integer expenseChoice = 0;
                            try {
                                do {
                                    System.out.println("============================================");
                                    System.out.println("Select The Expenses Type:");
                                    System.out.println();
                                    System.out.println("1.Food");
                                    System.out.println("2.Travel");
                                    System.out.println("3.Accommodation(Rent)");
                                    System.out.println("4.Clothing");
                                    System.out.println("5.Miscellaneous");
                                    System.out.println("6.Back");
                                    System.out.println();

                                    try {
                                        System.out.println("Choose number according to your requirement");
                                        expenseChoice = sc.nextInt();
                                    }catch (InputMismatchException e)
                                    {
                                        throw new InputException("Invalid Input Only Numbers Are Allowed", "ClassContainingMethods", "addTransaction");
                                    }
                                    System.out.println("============================================");
                                    switch (expenseChoice) {
                                        case 1:
                                            logic.expense("Food");
                                            break;
                                        case 2:
                                            logic.expense("Travel");
                                            break;
                                        case 3:
                                            logic.expense("Accommodation(Rent)");
                                            break;
                                        case 4:
                                            logic.expense("Clothing");
                                            break;
                                        case 5:
                                            logic.expense("Miscellaneous");
                                            break;
                                        case 6:
                                            System.out.println("!! Exiting Successfully !!");
                                            break;
                                        default:
                                            System.out.println("!! Please enter a valid number !!");
                                            break;
                                    }

                                } while (expenseChoice != 6);
                            }catch(InputException i){
                                System.out.println("Error: "+i.getMessage() +" || At Class: "+ i.getClassName() +" || At Method: "+ i.getMethodName());
                                sc.nextLine();
                            }
                        break;
                    }
                    case 3:
                    {
                        System.out.println("!! Exiting Successfully !!");
                        break;
                    }
                    default:
                        System.out.println("!! Please enter a valid number !!");
                        break;
                }
            } while (transactionChoice != 3);
        }catch(InputException i){
            System.out.println("Error: "+i.getMessage() +" || At Class: "+ i.getClassName() +" || At Method: "+ i.getMethodName());
            sc.nextLine();
        }
    }


    public void viewTransaction() {

            Integer viewTransactionChoice = 0;
            try {
                do {
                    System.out.println("============================================");
                    System.out.println("Select The View Type:");
                    System.out.println();

                    System.out.println("1.View All Transaction");
                    System.out.println("2.View By Category");
                    System.out.println("3.View By Date Filter");
                    System.out.println("4.Back");
                    System.out.println();

                    try {
                        System.out.println("Choose number according to your requirement");
                        viewTransactionChoice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        throw new InputException("Invalid Input Only Numbers Are Allowed", "ClassContainingMethods", "viewTransaction");
                    }
                    System.out.println("============================================");

                    switch (viewTransactionChoice) {
                        case 1:
                       operation.readingAllTransaction();
                            break;
                        case 2:
                            Integer choice = 0;

                            do {
                                System.out.println("Select Category");
                                System.out.println("1.Income");
                                System.out.println("2.Expense");
                                System.out.println("3.Back");
                                System.out.println();
                                try {
                                    System.out.println("Choose number according to your requirement");
                                    choice = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    throw new InputException("Invalid Input Only Menu Numbers Are Allowed", "ClassContainingMethods", "viewTransaction");
                                }
                                if (choice == 1)
                                   operation.readingAllTransactionForCategory("Income");
                                else
                                    operation.readingAllTransactionForCategory("Expense");
                            } while (choice != 3);
                            break;
                        case 3:
                            logic.dateFilter();
                            break;
                        case 4:
                            System.out.println("!! Exiting Successfully !!");
                            break;
                        default:
                            System.out.println("!! Please enter a valid number !!");
                            break;
                    }


                } while (viewTransactionChoice != 4);

            } catch (InputException i) {
                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                sc.nextLine();
            }

    }



        public void deleteEditTransaction () {
            Integer userChoice = 0;
            try {
                do {
                    System.out.println("============================================");
                    System.out.println("Select The Action");
                    System.out.println();

                    System.out.println("1.Edit");
                    System.out.println("2.Delete");
                    System.out.println("3.Back");

                    try {
                        System.out.println("Choose number according to your requirement");
                        userChoice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        throw new InputException("Invalid Input Only Numbers Are Allowed", "ClassContainingMethods", "addTransaction");
                    }
                    System.out.println("============================================");
                    switch (userChoice) {
                        case 1:
                            logic.editTransaction();
                            break;
                        case 2:
                            logic.deleteTransaction();
                            break;
                        case 3:
                            System.out.println("!! Exiting Successfully !!");
                            break;
                        default:
                            System.out.println("!! Please enter a valid number !!");
                            break;

                    }
                } while (userChoice != 3);

            } catch (InputException i) {
                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                sc.nextLine();
            }
        }

        public void save(){

            logic.addingToFileUsingSerialization();
        }



    public void exportFile () {

        ArrayList<Transaction> newList = operation.readingAllTransactionForDeletion();

        try {
            File file = new File("C:\\Users\\user\\Desktop\\TransactionDetails.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (file.length() == 0) {
                bufferedWriter.write("ID: , Date: , Type: , Category: , Amount:, Description: ");
                bufferedWriter.newLine();
            }
            if (newList != null) {
                for (Transaction t : newList) {
                    if (t != null) {
                        String singleLine = t.getId() + "," + t.getDate() + "," + t.getType() + "," + t.getCategory() + "," + t.getAmount() + "," + t.getDescription();
                        bufferedWriter.write(singleLine);
                        bufferedWriter.newLine();
                    }
                }
                bufferedWriter.close();
            }
        } catch (IOException e) {
            throw new InputException("IOException occur", "Logic", "SavingToFile");
        }
    }
}