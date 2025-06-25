package fileworks;

import java.util.Comparator;

public class AgeComparator implements Comparator<Transaction> {

    public int compare(Transaction t1 , Transaction t2) {
        Integer firstId = t1.getId();
        Integer secondId = t2.getId();

        if(firstId>secondId)
            return 1;
        else if (firstId<secondId)
            return -1;
        else
            return 0;
    }

}
