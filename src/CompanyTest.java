package src;

import java.io.IOException;
import java.time.LocalDate;

class CompanyTest {
    public static void main(String[] args) throws IOException {

        Company company = new Company("Рога и копыта");
        Employee[] staff = new Employee[7];

        staff[0] = new Employee("Сидоров", "Василий", "Петрович", Sex.valueOf("Муж"), LocalDate.of(1990, 1, 2),
                8_000, 2013, 8);
        staff[1] = new SalesPerson("Казакова", "Татьяна", "Олеговна", Sex.valueOf("Жен"), LocalDate.of(1995, 7, 14),
                10_000, 0, 2012, 3, 0, 0);
        staff[2] = new Manager("Иванов", "Фёдор", "Викторович", Sex.valueOf("Муж"), LocalDate.of(1985, 11, 22),
                15_000, 0, 2005, 4, "Отдел маркетинга", 0, 10);
        staff[3] = new Employee("Шевченко", "Тарас", "Григорьевич", Sex.valueOf("Муж"), LocalDate.of(1986, 2, 28),
                10_000, 2014, 7);
        staff[4] = new Employee("Скакунова", "Жанна", "Алексеевна", Sex.valueOf("Жен"), LocalDate.of(1996, 9, 8),
                10_000);
        staff[5] = new SalesPerson("Евдокимов", "Дмитрий", "Васильевич", 11_000, 0);
        staff[6] = new Employee("Димитрова", "Светлана", "Борисовна", Sex.valueOf("Жен"), LocalDate.of(1995, 1, 18));

        staff[6].setSalary(12_000);
        staff[4].changeSalary(10);
        staff[5].setBirthday(1991, 2, 28);


        company.addEmployees(staff);
        company.printAllEmployees();

        company.salesStaffToSell(1000);
        System.out.println();

        company.printEmployeesSortedBySalary();
        company.increaseSalaryAfter5Years(10);

        System.out.println("Баланс расчетного счета перед выплатой зарплаты: " + company.getSalaryAccountBalance() + "\n");
        company.giveSalaryForAll();
        System.out.println("Баланс расчетного счета после выплаты зарплаты: " + company.getSalaryAccountBalance() + "\n");

        company.calculateVacation();
        company.giveHolidayBonus(LocalDate.of(2016, 3, 8), 800);
        company.giveHolidayBonus(LocalDate.of(2016, 2, 23), 500);
        System.out.println("Баланс расчетного счета после выплаты праздничного бонуса: " + company.getSalaryAccountBalance() + "\n");
        company.printAllEmployees();

        company.reduceStaff();
        company.printEmployeesSortedByName();

        company.hireNewPerson("Барсуков", "Евгений", "Викторович", Sex.valueOf("Муж"), LocalDate.of(1995, 1, 17),
                10_000, "Отдел маркетинга");
        company.printEmployeesSortedByName();
    }
}
