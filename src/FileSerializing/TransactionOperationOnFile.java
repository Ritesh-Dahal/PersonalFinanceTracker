package FileSerializing;

import exceptionhandeling.InputException;
import fileworks.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionOperationOnFile implements Serializable {
    final int ID_WIDTH = 4;
    final int DATE_WIDTH = 12;
    final int TYPE_WIDTH = 9;
    final int CATEGORY_WIDTH = 25;
    final int AMOUNT_WIDTH = 15;
    final int DESC_WIDTH = 24;


    public static String center(String text, int width) {

        if (text == null) text = "";
        int padding = width - text.length();
        if (padding <= 0) return text;
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }


    Map<String,List<Transaction>> hstry  = new HashMap<>();

    public void addingCollectionToFile(List<Transaction> list)
    {
//            System.out.println(list.size());
        hstry.put("Income",null);
        hstry.put("Expense",null);

        try{
            File file = new File("Transaction.ser");

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (IOException e )
        {
            throw new InputException("File not found ", "TransactionOperationOnFile", "addingCollectionToFile");
        }

    }


    public  void  readingAllTransaction()
    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("Transaction.ser")))
        {
                while (true) {
                    try {
                        ArrayList<Transaction> transaction = (ArrayList<Transaction>) objectInputStream.readObject();
                        System.out.println("=".repeat(ID_WIDTH + DATE_WIDTH + TYPE_WIDTH + CATEGORY_WIDTH + AMOUNT_WIDTH + DESC_WIDTH + 19));
                        System.out.printf("|%s|%s|%s|%s|%s|%s|%n",
                                center("ID", ID_WIDTH),
                                center("Date", DATE_WIDTH),
                                center("Type", TYPE_WIDTH),
                                center("Category", CATEGORY_WIDTH),
                                center("Amount", AMOUNT_WIDTH),
                                center("Description", DESC_WIDTH));
                        System.out.println("=".repeat(ID_WIDTH + DATE_WIDTH + TYPE_WIDTH + CATEGORY_WIDTH + AMOUNT_WIDTH + DESC_WIDTH + 19));

                        for (Object obj : transaction) {
                            System.out.println(obj);
                        }

                     }catch (EOFException e)
                        {
                            throw new InputException("EOFException while reading file ", "TransactionOperationOnFile", "readingAllTransaction");

                         }
                    break;
                }

        }catch (IOException e )
        {
            throw new InputException("IO Exception while reading file ", "TransactionOperationOnFile", "readingAllTransaction");
        }catch (ClassNotFoundException e )
        {
            throw new InputException("FileNotFound while reading file", "TransactionOperationOnFile", "readingAllTransaction");
        }

    }

    public  void  readingAllTransactionForCategory(String select)
    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("Transaction.ser")))
        {
            while (true) {
                try {
                    ArrayList<Transaction> transaction = (ArrayList<Transaction>) objectInputStream.readObject();
                    ArrayList<Transaction> categoryList = new ArrayList<>();
                    if (select.equalsIgnoreCase("Income")) {

                        for (Transaction obj : transaction)
                            if (obj.getType().equalsIgnoreCase("Income")) {
                                categoryList.add(obj);
                            }
                        for (Object list : categoryList) {
                            System.out.println(list);
                        }
                    }
                    if (select.equalsIgnoreCase("Expense")) {
                        for (Transaction obj : transaction)
                            if (obj.getType().equalsIgnoreCase("Expense")) {
                                categoryList.add(obj);
                            }
                        for (Object list : categoryList) {
                            System.out.println(list);
                        }
                    }

                }catch (EOFException e)
                {
                    throw new InputException("EOFException while reading file ", "TransactionOperationOnFile", "readingAllTransaction");

                }
                break;
            }

        }catch (IOException e )
        {
            throw new InputException("IO Exception while reading file ", "TransactionOperationOnFile", "readingAllTransaction");
        }catch (ClassNotFoundException e )
        {
            throw new InputException("FileNotFound while reading file", "TransactionOperationOnFile", "readingAllTransaction");
        }

    }


    public  ArrayList<Transaction>  readingAllTransactionForDeletion()
    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("Transaction.ser")))
        {
            while (true) {
                try {
                    ArrayList<Transaction> transaction = (ArrayList<Transaction>) objectInputStream.readObject();
                    return transaction;

                }catch (EOFException e)
                {
                    throw new InputException("EOFException while reading file ", "TransactionOperationOnFile", "readingAllTransaction");
                }
            }

        }catch (IOException e )
        {
            return new ArrayList<>();
        }catch (ClassNotFoundException e )
        {
            throw new InputException("FileNotFound while reading file", "TransactionOperationOnFile", "readingAllTransaction");
        }

    }



        public Integer gettingId()
        {
            try (ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("Transaction.ser")))

            {
                while (true) {
                    try {
                        ArrayList<Transaction> transaction = (ArrayList<Transaction>) objectInputStream.readObject();
                        if(transaction.size()==0)
                        {
                            return 0;
                        }
                        else {
                            Transaction lastTransaction = transaction.get(transaction.size() - 1);
                            Integer lastId = lastTransaction.getId();
                            return lastId;
                        }
                    }catch (EOFException e)
                    {
                        throw new InputException("EOFException while reading file ", "TransactionOperationOnFile", "gettingId");
                    }
                }

            }catch (IOException e )
            {
                return 0;

            }catch (ClassNotFoundException e )
            {
                throw new InputException("FileNotFound while reading file", "TransactionOperationOnFile", "gettingId");
            }


        }




        public void generatingReport(){


            try (ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream("Transaction.ser")))
            {
                while (true) {
                    try {
                        ArrayList<Transaction> transaction = (ArrayList<Transaction>) objectInputStream.readObject();


                            Double totalIncome =0d;
                            Double totalExpense =0d;


                            for (Transaction obj : transaction){
                                if (obj.getType().equalsIgnoreCase("Income")) {
                                   totalIncome+= obj.getAmount();
                                }
                               else if (obj.getType().equalsIgnoreCase("Expense")) {

                                   totalExpense+= obj.getAmount();
                                }


                        }

                        System.out.println("Total Income: "+totalIncome);
                        System.out.println("Total Expense: "+ totalExpense);

                        if (totalIncome>totalExpense)
                        {
                            System.out.println("Your income is more than the expense");
                        }
                        else {
                            System.out.println("Your expense is more than the income");
                        }



                    }catch (EOFException e)
                    {
                        throw new InputException("EOFException while reading file ", "TransactionOperationOnFile", "readingAllTransaction");

                    }
                    break;
                }

            }catch (IOException e )
            {
                throw new InputException("IO Exception while reading file ", "TransactionOperationOnFile", "readingAllTransaction");
            }catch (ClassNotFoundException e )
            {
                throw new InputException("FileNotFound while reading file", "TransactionOperationOnFile", "readingAllTransaction");
            }




        }








}

