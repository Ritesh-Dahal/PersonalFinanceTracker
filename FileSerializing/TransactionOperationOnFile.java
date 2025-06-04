package FileSerializing;

import exceptionhandeling.InputException;
import fileworks.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionOperationOnFile implements Serializable {

    public void addingCollectionToFile(List<Transaction> list)
    {
//            System.out.println(list.size());


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

