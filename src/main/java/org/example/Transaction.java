package org.example;

import java.time.LocalDate;

// класс для хранения данных о транзакции (тип, сумма, категория, дата).
public class Transaction {
    private Type type;
    private LocalDate date;
    private Category category;
    private int sum;

    public static void main(String[] args) {}

    // getters,setters
    public void setType(Type _type){
        this.type = _type;
    }

    public Type getType(){
        return this.type;
    }

    public void setDate(LocalDate _date){
        this.date = _date;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public void setCategory(Category _category){
        this.category = _category;
    }

    public Category getCategory(){
        return this.category;
    }

    public void setSum(int _sum){
        this.sum = _sum;
    }

    public int getSum(){
        return this.sum;
    }
}
