package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

// класс для управления всеми транзакциями (добавление, вывод, подсчёт баланса).
public class BudgetManager {
    private int balance = 0;
    private ArrayList<Transaction> list = new ArrayList<Transaction>();

    public static void main(String[] args){}

    // getters, setters
    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int _newBalance){
        this.balance = _newBalance;
    }

    // добавляет запись
    public void addTransaction(boolean _isCrediting, LocalDate _date, Category _category, int _sum){
        System.out.println(" >>> Обработка транзакции");
        Type type = _isCrediting ? Type.CREDITING : Type.DEBITING;
        Transaction transaction = createTransaction(type, _date, _category, _sum);
        list.add(transaction);
        this.setBalance(this.balance + _sum);
    }

    // возвращает текущий баланс
    public int currentBalance(){
        return this.getBalance();
    }

    // возвращает список транзакций за указанный период
    public ArrayList<Transaction> filterByDate(LocalDate _start, LocalDate _end){
        System.out.println("Верну список транзакций за указанный период");

        ArrayList<Transaction> result = new ArrayList<>();

        return result;
    }

    private Transaction createTransaction(Type _type, LocalDate _date, Category _category, int _sum){
        Transaction transaction = new Transaction();
        transaction.setType(_type);
        transaction.setDate(_date);
        transaction.setCategory(_category);
        transaction.setSum(_sum);

        return transaction;
    }
}
