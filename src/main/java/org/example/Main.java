package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("""
.....................................................................
                Проект 'Учёт расходов и доходов'
.....................................................................""");

        Scanner console = new Scanner(System.in);

        BudgetManager manager = new BudgetManager();
        boolean continueCicle = true;
        
        while (continueCicle) {
            System.out.println("");
            System.out.println("""
                   1. Добавить запись
                   2. Посмотреть текущий баланс
                   3. Список транзакций за период
                   0. Выход """.trim());
            System.out.println("Введите команду: ");
            int command = console.nextInt();

            switch (command) {
                case 1:
                    if(credOrDeb(console)){
                        LocalDate date = enterDate(console);
                        Category category = choiceCategory(console, true);
                        int sum = enterSum(console);
                        manager.addTransaction(date, category, sum); // в методе обработка с валидацией
                    } else {
                        Category category = choiceCategory(console, false);
                        int sum = enterSum(console);
                        manager.addTransaction(LocalDate.now(), category, -sum); // в методе обработка с валидацией
                    }
                    System.out.println(" >>> Добавлена транзакция");

                    break;
                case 2:
                    System.out.println(" >>> Текущий баланс: " + manager.currentBalance());

                    break;
                case 3:
                    // попросить ввести одну дату
                    // и вторую
                    LocalDate start = LocalDate.now(); // of(year, month, day)
                    LocalDate end = LocalDate.now(); // of(year, month, day)
                    ArrayList<Transaction> res = manager.filterByDate(start, end);
                    System.out.println(res);

                    break;
                case 0:
                    System.out.println(" >>> Выход и завершение программы");
                    continueCicle = false;

                    break;
                default:
                    System.out.println(" !");
                    System.out.println(" >>> Неверные данные");
                    System.out.println(" !");

                    break;
            }
        }

        console.close();
    }

    // зачисление или списание ()
    public static boolean credOrDeb(Scanner _console){
        System.out.println("""
            Выберите тип операции:
            1. зачисление
            2. списание""");
        int type = _console.nextInt();
        boolean crediting;

        switch (type){
            case 1:
                crediting = true;
                break;
            case 2:
                crediting = false;
                break;
            default:
                crediting = true;
        }

        return crediting;
    }
    // введение суммы
    public static int enterSum(Scanner _console){
        System.out.println("Введите сумму:");
        int sum = _console.nextInt();

        return sum;
    }
    // введение даты
    public static LocalDate enterDate(Scanner _console){
        System.out.println("""
            Введите поочередно год, месяц и день в виде числа""");
        System.out.println("""
            год:""");
        int year = _console.nextInt();
        System.out.println("""
            месяц:""");
        int month = _console.nextInt();
        System.out.println("""
            день:""");
        int day = _console.nextInt();

        LocalDate result = LocalDate.of(year, month, day);

        return result;
    }

    // выбор категории транзакции
    public static Category choiceCategory(Scanner _console, boolean isCrediting){
        if(isCrediting){
            System.out.println("""
                Выберите категорию:
                1. SALARY
                2. OTHER""");
        } else {
            System.out.println("""
                Выберите категорию:
                1. FOOD
                2. TRANSPORT
                3. ENTERTAINMENT""");
        }


        Category result;
        int category = _console.nextInt();

        switch(category){
            case 1:
                result = Category.FOOD;
                break;
            case 2:
                result = Category.TRANSPORT;
                break;
            case 3:
                result = Category.SALARY;
                break;
            case 4:
                result = Category.ENTERTAINMENT;
                break;
            case 5:
                result = Category.OTHER;
                break;
            default:
                System.out.println("dickhead");
                result = Category.OTHER;
                break;
        }

        return result;
    }
}