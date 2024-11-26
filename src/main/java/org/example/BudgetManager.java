package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

// класс для управления всеми транзакциями (добавление, вывод, подсчёт баланса).
public class BudgetManager {
    private int balance = 0;
    ArrayList<Transaction> list;

    public static void main(String[] args){}

    // getters, setters
    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int _newBalance){
        this.balance = _newBalance;
    }

    // добавляет запись
    public boolean addTransaction(LocalDate _date, Category _category, int _sum){
        System.out.println("Добавляю транзакцию");
        int curr = this.getBalance();
        this.setBalance(curr + _sum);

        return true;
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
}
