package mainclass;


import FileSerializing.TransactionOperationOnFile;
import differentmethods.ClassContainingMethods;
import exceptionhandeling.InputException;
import fileworks.Logic;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass
{
    private Integer userInputNumber;

    public Integer getUserInputNumber() {
        return userInputNumber;
    }



    public static void main(String[] args) {

        MainClass mainClass = new MainClass();
        Scanner sc = new Scanner(System.in);
        ClassContainingMethods classContainingMethods = new ClassContainingMethods();
        TransactionOperationOnFile operation = new TransactionOperationOnFile();

            classContainingMethods.idGenerator();


            try {

                do {
                    System.out.println("============================================");
                    System.out.println("Welcome to Personal Finance Tracker");
                    System.out.println();

                    System.out.println("1.Add Transaction");
                    System.out.println("2.View Transaction");
                    System.out.println("3.Delete/Edit Transaction");
                    System.out.println("4.Generate Reports");
                    System.out.println("5.Save and Load Data");
                    System.out.println("6.Export Report");
                    System.out.println("7.Exit");
                    System.out.println();

                    try {
                        System.out.println("Choose number according to you're requirement:");
                        mainClass.userInputNumber = sc.nextInt();


                    } catch (InputMismatchException e) {
                        throw new InputException("Invalid Input Only Numbers Are Allowed", "MainClass", "main");
                    }

                    System.out.println("============================================");


                    switch (mainClass.getUserInputNumber()) {
                        case 1:

                            classContainingMethods.addTransaction();
                            break;
                        case 2:
                            classContainingMethods.viewTransaction();
                            break;
                        case 3:
                            classContainingMethods.deleteEditTransaction();
                            break;
                        case 4:
                          operation.generatingReport();
                            break;
                        case 5:
                            classContainingMethods.save();
                            System.out.println(" == Saved Successfully == ");
                            break;
                        case 6:
                            classContainingMethods.exportFile();
                            System.out.println(" == Exported Successfully on Desktop with File Name TransactionDetails == ");
                            break;
                        case 7:
                            System.out.println("!! Exiting Successfully !!");
                            break;
                        default:
                            System.out.println("!! Please enter a valid number !!");
                            break;
                    }

                } while (mainClass.userInputNumber != 7);

            } catch (InputException i) {
                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());

            }


    }

}
