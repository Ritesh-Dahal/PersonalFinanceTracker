package fileworks;


import java.io.Serializable;
import java.time.LocalDate;

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
        public String toString()
        {
            return "ID: "+ getId() +" || Date: "+ getDate() + " || Type: "+getType()+" || Category: "+getCategory()+" || Amount: "+ getAmount()+ " || Description: "+ getDescription();
        }
}


