package fileworks;


import java.io.Serializable;
import java.time.LocalDate;

import static FileSerializing.TransactionOperationOnFile.center;

public class Transaction implements Serializable {



    private LocalDate date;
    private Double amount;
    private String category;
    private String description;
    private String type;
    private Integer id;

    public Transaction(Integer id,LocalDate date, String type, String description, Double amount, String category) {
        this.id=id;
        this.date = date;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
    public Transaction(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        final int ID_WIDTH = 4;
        final int DATE_WIDTH = 12;
        final int TYPE_WIDTH = 9;
        final int CATEGORY_WIDTH = 25;
        final int AMOUNT_WIDTH = 15;
        final int DESC_WIDTH = 24;
        return String.format("|%s|%s|%s|%s|%s|%s|",
                center(String.valueOf(getId()), ID_WIDTH),
                center(getDate().toString(), DATE_WIDTH),
                center(getType(), TYPE_WIDTH),
                center(getCategory(), CATEGORY_WIDTH),
                center(String.format("%.2f", getAmount()), AMOUNT_WIDTH),
                center(getDescription(), DESC_WIDTH));
    }

}


