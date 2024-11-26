package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

// класс для управления всеми транзакциями (добавление, вывод, подсчёт баланса).
public class BudgetManager {
    private int balance = 0;
    private ArrayList<Transaction> list = new ArrayList<Transaction>();

    public static void main(String[] args){}

    // getters, setters
    public void setBalance(int _newBalance){
        this.balance = _newBalance;
    }
    public int getBalance(){
        return this.balance;
    }

    // добавляет запись транзакции
    public void addTransaction(boolean _isCrediting, LocalDate _date, Category _category, int _sum){
        Type type = _isCrediting ? Type.CREDITING : Type.DEBITING;
        Transaction transaction = createTransaction(type, _date, _category, _sum);
        this.list.add(transaction);
        this.setBalance(this.balance + _sum);
        System.out.println(" >>> Операция прошла, баланс обновлен");
    }

    // создание транзакции
    private Transaction createTransaction(Type _type, LocalDate _date, Category _category, int _sum){
        System.out.println(" >>> Создание транзакции");
        Transaction transaction = new Transaction();
        transaction.setType(_type);
        transaction.setDate(_date);
        transaction.setCategory(_category);
        transaction.setSum(_sum);

        return transaction;
    }

    public ArrayList<Transaction> listTransactions(){
        this.list.sort(Comparator.comparing(Transaction::getDate));

        return this.list;
    }

    // возвращает отсортиованный по дате список транзакций за указанный период
    public ArrayList<Transaction> filterByDate(LocalDate _start, LocalDate _end){
//        addTransaction(true, LocalDate.of(2024, 10,10), Category.SALARY, 300000);
//        addTransaction(true, LocalDate.of(2024, 11,17), Category.ENTERTAINMENT, 50000);
//        addTransaction(false, LocalDate.of(2024, 9,24), Category.ENTERTAINMENT, -150000);
//        addTransaction(true, LocalDate.of(2024, 8,23), Category.SALARY, 300000);
//        addTransaction(true, LocalDate.of(2024, 5,24), Category.ENTERTAINMENT, 5000);
//        addTransaction(false, LocalDate.of(2024, 9,10), Category.ENTERTAINMENT, -150000);
        System.out.println(" >>> Сбор транзакций за период с " + _start + " по " + _end);
        ArrayList<Transaction> result = new ArrayList<Transaction>();

        for(int i = 0; i < this.list.size(); i++){
            LocalDate date = this.list.get(i).getDate();
            if (
                    ( date.isAfter(_start) || date.isEqual(_start) ) &&
                    ( _end.isAfter(date)   || date.isEqual(_end))  ) { // если date больше либо равно _start и меньше либо равно _end
                result.add(this.list.get(i));
            }
        }

        result.sort(Comparator.comparing(Transaction::getDate));

        return result;
    }


}
