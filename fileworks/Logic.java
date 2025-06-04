package fileworks;


import FileSerializing.TransactionOperationOnFile;
import exceptionhandeling.InputException;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Logic {


    private ArrayList<Transaction> mylist = new ArrayList<>();

    public List<Transaction> getMylist() {
        return mylist;
    }

    Scanner sc = new Scanner(System.in);

    private static Integer lastId = 0;


    TransactionOperationOnFile operation = new TransactionOperationOnFile();



    public void id(){


            lastId = operation.gettingId();


    }



    public void income(String type) {

        Double amount = 0d;
        try {
            System.out.println("Enter the income amount:");
            amount = (double) sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "income");
        } catch (InputException i) {
            System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());

        }
        if (amount <= 0) {
            System.out.println(" == Invalid Amount == ");
        } else {
            System.out.println("Enter the description:");
            String descriptionMessage = sc.nextLine();
            String category = type;
            lastId++;



            Transaction income = new Transaction(lastId, LocalDate.now(), "Income", descriptionMessage, amount, category);
            mylist.add(income);
            System.out.println("============================================");
            System.out.println("-- Income Added Successfully --");

        }
    }


    public void expense(String type) {

        Double amount = 0d;
        try {
            System.out.println("Enter the expense amount:");
            amount = (double) sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "expense");
        } catch (InputException i) {
            System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());


        }
        if (amount <= 0) {
            System.out.println(" == Invalid Amount == ");
        } else {
            System.out.println("Enter the description:");
            String descriptionMessage = sc.nextLine();
            String category = type;

            lastId++;



            Transaction income = new Transaction(lastId, LocalDate.now(), "Expense", descriptionMessage, amount, category);
            mylist.add(income);
            System.out.println("============================================");
            System.out.println("-- Expense Added Successfully --");


        }
    }


    public void viewAllTransaction() {
        for (Object object : mylist) {
            System.out.println(object);
        }

    }


//    public void viewByCategory(String select) {
//
//
//        ArrayList<Transaction> categoryList = new ArrayList<>();
//
//        if (select.equalsIgnoreCase("Income")) {
//
//            for (Transaction obj : mylist)
//                if (obj.getType().equalsIgnoreCase("Income")) {
//                    categoryList.add(obj);
//                }
//            for (Object list : categoryList) {
//                System.out.println(list);
//            }
//        }
//        if (select.equalsIgnoreCase("Expense")) {
//            for (Transaction obj : mylist)
//                if (obj.getType().equalsIgnoreCase("Expense")) {
//                    categoryList.add(obj);
//                }
//            for (Object list : categoryList) {
//                System.out.println(list);
//            }
//        }
//
//    }


    public void deleteTransaction() {

        ArrayList<Transaction> newList = operation.readingAllTransactionForDeletion();

        if (newList.size() == 0) {
            System.out.println(" == List Is Empty So Add Transaction First == ");

        } else {
            for (Object object : newList) {
                System.out.println(object);
            }

            Integer userChoice = 0;

            try {
                System.out.println("Enter the id of the transaction which you wanna delete");
                userChoice = (Integer) sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "deleteTransaction");
            } catch (InputException i) {
                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                sc.nextLine();
            }
            boolean found = false;

            Iterator<Transaction> iterator = newList.iterator();

            while (iterator.hasNext()) {
                Transaction t = iterator.next();
                if (t.getId().equals(userChoice)) {
                    iterator.remove();
                    found = true;
                    System.out.println(" == Id Deleted Successfully == ");
                }
            }
            if (!found) {
                System.out.println(" == Id Not Found ==");
            }
        }

        operation.addingCollectionToFile(newList);

    }





    public void editTransaction() {

        ArrayList<Transaction> newList = operation.readingAllTransactionForDeletion();

        if (newList.size() == 0) {
            System.out.println(" == List Is Empty So Add Transaction First == ");

        } else {
            for (Object object : newList) {
                System.out.println(object);
            }
            Integer userChoice = 0;

            try {
                System.out.println("Enter the id of the transaction which you wanna edit:");
                userChoice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "editTransaction");
            } catch (InputException i) {
                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                sc.nextLine();
            }
            if (userChoice < 0) {
                System.out.println(" == Invalid Number == ");
            } else {
                Integer count = 0;
                try {
                    for (Transaction t : newList) {

                        if (t.getId().equals(userChoice)) {
                            int select = 0;

                            try {

                                do {
                                    System.out.println("1.Income");
                                    System.out.println("2.Expense");
                                    try {
                                        System.out.println("Select the Type:");
                                        select =sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "editTransaction");
                                    }
                                    if (select == 1) {
                                        t.setType("Income");
                                        int selectType = 0;
                                        do {
                                            System.out.println("1.Salary");
                                            System.out.println("2.Loan");
                                            try {
                                                System.out.println("Select the Category:");
                                                selectType = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "editTransaction");
                                            }
                                            if (selectType == 1) {
                                                t.setCategory("Salary");
                                            } else if (selectType == 2) {
                                                t.setCategory("Loan");
                                            } else {
                                                System.out.println("!! Invalid Selection !!");
                                            }
                                        } while (selectType != 1 && selectType != 2);


                                    } else if (select == 2) {
                                        t.setType("Expense");

                                        int expenseChoice = 0;

                                        do {
                                            System.out.println("============================================");
                                            System.out.println("Select The Expenses Type:");
                                            System.out.println();
                                            System.out.println("1.Food");
                                            System.out.println("2.Travel");
                                            System.out.println("3.Accommodation(Rent)");
                                            System.out.println("4.Clothing");
                                            System.out.println("5.Miscellaneous");
                                            System.out.println();
                                            try {
                                                System.out.println("Choose number according to your requirement");
                                                expenseChoice = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "editTransaction");
                                            }
                                            System.out.println("============================================");
                                            switch (expenseChoice) {
                                                case 1:
                                                    t.setCategory("Food");
                                                    break;
                                                case 2:
                                                    t.setCategory("Travel");
                                                    break;
                                                case 3:
                                                    t.setCategory("Accommodation(Rent)");
                                                    break;
                                                case 4:
                                                    t.setCategory("Clothing");
                                                    break;
                                                case 5:
                                                    t.setCategory("Miscellaneous");
                                                    break;
                                                default:
                                                    System.out.println("!! Please enter a valid number !!");
                                                    break;
                                            }

                                        } while (expenseChoice != 1 && expenseChoice != 2 && expenseChoice != 3 && expenseChoice != 4 && expenseChoice != 5);

                                    } else {
                                        System.out.println("!! Invalid Selection !!");
                                    }

                                } while (select != 1 && select != 2);
                            } catch (InputException i) {
                                System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                                sc.nextLine();
                            }
                            double amount = 0d;
                            try {
                                System.out.println("Enter the new amount:");
                                amount = (double) sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                throw new InputException("Invalid Input Only Numbers Are Allowed", "Logic", "editTransaction");
                            }
                            if (amount < 0) {
                                System.out.println(" == Invalid Amount == ");
                                break;

                            } else {
                                t.setAmount(amount);
                                System.out.println("Enter the description:");
                                t.setDescription(sc.nextLine());
                                operation.addingCollectionToFile(newList);

                                System.out.println("============================================");
                                System.out.println("--  Edited Successfully --");
                                return;
                            }
                        } else {
                            count++;
                        }

                    }

                } catch (InputException i) {
                    System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
                    sc.nextLine();
                }

                if (count > 0) {
                    System.out.println("== ID Not Found==");
                }

            }
        }



    }


    public void dateFilter() {

        ArrayList<Transaction> newList = operation.readingAllTransactionForDeletion();

        try {
            System.out.println("Enter the date form which you wanna filter(FORMAT: YYYY-MM-DD)");
            String startDate = sc.nextLine();
            System.out.println("Enter the date up to which you wanna filter(FORMAT: YYYY-MM-DD)");
            String endDate = sc.nextLine();
            LocalDate startFrom = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
            LocalDate endAt = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);



            for (Transaction t : newList) {
                LocalDate date = t.getDate();

                if ((date.isEqual(startFrom) || date.isAfter(startFrom)) && (date.isEqual(endAt) || date.isBefore(endAt))) {
                    System.out.println(t);
                }
            }

        } catch (DateTimeParseException e) {
            throw new InputException("Invalid Input Only Date", "Logic", "dateFilter");
        } catch (InputException i) {
            System.out.println("Error: " + i.getMessage() + " || At Class: " + i.getClassName() + " || At Method: " + i.getMethodName());
            sc.nextLine();
        }
    }





    public void addingToFileUsingSerialization()
    {
        TransactionOperationOnFile operation = new TransactionOperationOnFile();

        ArrayList<Transaction> newList = operation.readingAllTransactionForDeletion();


            for (Transaction firstTrans : newList) {
                boolean exist = false;
                for (Transaction secondTrans : mylist) {
                    if (firstTrans.getId().equals(secondTrans.getId()))
                    {
                        exist = true;
                    break;
                    }
                }
                if(!exist)
                    mylist.add(firstTrans);
            }


            Collections.sort(mylist, new AgeComparator());
        operation.addingCollectionToFile(mylist);
    }









// Used for writing on CSV


//    public void readingFromFile()
//    {

//        List<Transaction> newList = new ArrayList<>();
//        try{
//            FileReader fileReader = new FileReader("Transaction.csv");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//            boolean firstline = true;
//
//
//            //TO Skip the First Line
//
//            while ((line=bufferedReader.readLine())!=null){
//                if(firstline){
//                    firstline = false;
//                    continue;
//                }
//                String[] values = line.split(",");
//                Integer id = Integer.parseInt(values[0]);
//
//
//
//            }
//
//        }catch(IOException e) {
//            throw new InputException("IOException occur", "Logic", "readingFromFile");
//            }
//        }


//    public void savingToFile() {
//        try {
//            File file = new File("TransactionNew.csv");
//            file.createNewFile();
//            FileWriter fileWriter = new FileWriter(file, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//            if (file.length() == 0) {
//                bufferedWriter.write("ID: , Date: , Type: , Category: , Amount:, Description: ");
//                bufferedWriter.newLine();
//            }
//            if (mylist != null) {
//                for (Transaction t : mylist) {
//                    if (t != null) {
//                        String singleLine = t.getId() + "," + t.getDate() + "," + t.getType() + "," + t.getCategory() + "," + t.getAmount() + "," + t.getDescription();
//                        bufferedWriter.write(singleLine);
//                        bufferedWriter.newLine();
//                    }
//                }
//                bufferedWriter.close();
//            }
//        } catch (IOException e) {
//            throw new InputException("IOException occur", "Logic", "SavingToFile");
//        }
//    }




}