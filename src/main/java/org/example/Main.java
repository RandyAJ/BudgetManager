package org.example;

//import java.util.ArrayList;
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
            System.out.println();
            System.out.println("""
                   1. Добавить запись
                   2. Посмотреть текущий баланс
                   3. Список транзакций за период
                   0. Выход""".trim());
            System.out.println("Введите команду: ");
            int command = console.nextInt();

            switch (command) {
                case 1:
                    if(credOrDeb(console)){
                        LocalDate date = enterDate(console);
                        Category category = choiceCategory(console, true);
                        int sum = enterSum(console);
                        manager.addTransaction(true, date, category, sum); // в методе обработка с валидацией
                    } else {
                        LocalDate date = enterDate(console);
                        Category category = choiceCategory(console, false);
                        int sum = enterSum(console);
                        manager.addTransaction(false, date, category, -sum); // в методе обработка с валидацией
                    }
                    break;
                case 2:
                    System.out.println(" >>> Текущий баланс: " + manager.getBalance());
                    break;
                case 3:
                    System.out.println("Введите дату начала");
                    LocalDate start = enterDate(console);//LocalDate.of(2024, 1, 1);
                    System.out.println("и дату окончания");
                    LocalDate end = enterDate(console);//LocalDate.of(2024, 12, 31);

                    ArrayList<Transaction> transactions = manager.filterByDate(start, end);

                    System.out.println(" >>> Список транзакций:");
                    transactions.forEach( transaction -> System.out.println(transaction));
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

    // зачисление или списание
    public static boolean credOrDeb(Scanner _console){
        System.out.println("""
            Выберите тип операции:
            1. Зачисление
            2. Списание""");

        return (_console.nextInt() == 1);
    }

    // введение суммы
    public static int enterSum(Scanner _console){
        System.out.println("Введите сумму:");

        return _console.nextInt();
    }

    // введение даты
    public static LocalDate enterDate(Scanner _console){
        System.out.println("""
            Чтобы ввести дату введите поочередно: год, месяц и день в виде числа""");
        System.out.println("""
            год:""");
        int year = _console.nextInt();
        System.out.println("""
            месяц:""");
        int month = _console.nextInt();
        System.out.println("""
            день:""");
        int day = _console.nextInt();

        return LocalDate.of(year, month, day);
    }

    // выбор категории транзакции
    public static Category choiceCategory(Scanner _console, boolean _isCrediting){
        Category result = Category.OTHER;

        if(_isCrediting){
            System.out.println("""
                Выберите категорию:
                1. Зарплата
                2. Другое""");

            result = (_console.nextInt() == 1) ? Category.SALARY : Category.OTHER;
        } else {
            System.out.println("""
                Выберите категорию:
                1. Еда
                2. Транспорт
                3. Развлечения
                4. Другое""");

            switch(_console.nextInt()){
                case 1:
                    result = Category.FOOD;
                    break;
                case 2:
                    result = Category.TRANSPORT;
                    break;
                case 3:
                    result = Category.ENTERTAINMENT;
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}