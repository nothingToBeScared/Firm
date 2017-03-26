/*
У каждого сотрудника (firm.Employee) есть имя, фамилия, ставка зарплаты (сумма), его личный
карточный счет (сумма), год поступления на работу, месяц поступления на работу, пол, отдел.
--

Обычный сотрудник:
— Фамилия
— Имя
— Отчество
— Пол
— Дата рождения
— Ставка зарплаты
— Зарплатный счет
— Стаж
— Дни отпуска
— Дата последнего отпуска
— Отдел
*/

package firm;

import java.time.LocalDate;

class Employee {

    private String surname;
    private String name;
    private String fathersName;
    private Sex sex;
    private LocalDate birthday;
    private int salary;
    private int cardBalance;
    private int experienceMonths;
    private int vacationDays;
    private LocalDate lastVacationDate;
    private String division;
    private int hireYear;
    private int hireMonth;

    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int cb, int hryear, int hrmonth, String dvsn, int vac) {
        surname = srnm;
        name = nm;
        fathersName = fnm;
        sex = mf;
        birthday = bd;
        salary = slr;
        cardBalance = cb;
        hireYear = hryear;
        hireMonth = hrmonth;
        division = dvsn;
        vacationDays = vac;
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int cb, int hryear, int hrmonth, String dvsn) {
        this(srnm, nm, fnm, mf, bd, slr, cb, hryear, hrmonth, dvsn, 0);
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int hryear, int hrmonth, String dvsn) {
        this(srnm, nm, fnm, mf, bd, slr, 0, hryear, hrmonth, dvsn, 0);
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int cb, int hryear, int hrmonth) {
        this(srnm, nm, fnm, mf, bd, slr, cb, hryear, hrmonth, "Not set");
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int hryear, int hrmonth) {
        this(srnm, nm, fnm, mf, bd, slr, 0, hryear, hrmonth);
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, String dvsn) {
        this(srnm, nm, fnm, mf, bd, slr, LocalDate.now().getYear(), LocalDate.now().getMonthValue(), dvsn);
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr) {
        this(srnm, nm, fnm, mf, bd, slr, "Not set");
    }
    Employee(String srnm, String nm, String fnm, Sex mf, int slr) {
        this(srnm, nm, fnm, mf, LocalDate.of(0000, 01, 02), slr);
    }
    Employee(String srnm, String nm, String fnm, int slr) {
        this(srnm, nm, fnm, Sex.НеУказан, slr);
    }
    Employee(String srnm, String nm, String fnm, Sex mf, LocalDate bd) {
        this(srnm, nm, fnm, mf, bd, 0);
    }
    Employee() {
        this("Not set", "Not set", "Not set", 0 );
    }
    Employee (Employee obj) {
        this( obj.surname, obj.name, obj.fathersName, obj.sex, obj.birthday, obj.salary, obj.cardBalance,
                obj.hireYear, obj.hireMonth, obj.division, obj.vacationDays);
    }

    String getEmployeeFullName() {
        return surname + " " + name + " " + fathersName;
    }
    int getSalary() {
        return salary;
    }
    void setSalary(int slr) {
        if (slr <= 0) {
            System.out.println("Incorrect salary to set (zero or negative)!");
        } else {
            salary = slr;
        }
    }
    void changeSalary(int changePercent) {
        int salaryNew = salary + salary * changePercent / 100;
        if (salaryNew <= 0) {
            System.out.println("Incorrect change of salary (zero or negative)!");
        } else {
            salary = salaryNew;
        }

    }
    Sex getSex() {
        return sex;
    }

    LocalDate getBirthday() {
        return birthday;
    }
    void setBirthday(int year, int month, int day) {
        birthday = LocalDate.of(year, month, day);
    }
    void refillCardBalance(int payment) {
        if (payment <= 0) {
            System.out.println("Amount is not correct!");
        } else {
            cardBalance += payment;
        }
    }
    int getCardBalance() {
        return cardBalance;
    }
    int getHireMonth() {
        return hireMonth;
    }
    int getHireYear() {
        return hireYear;
    }

    int getVacationDays() {
        return vacationDays;
    }
    void setVacationDays(int days) {
        vacationDays = days;
    }
    void setDivision(String dv) {
        division = dv;
    }
    String getPosition() {
        switch (this.getClass().getName()) {
            case "firm.Manager": return "Начальник отдела";
            case "firm.SalesPerson": return "Менеджер по продаже";
            default: return "Рядовой Сотрудник";
        }
    }
    String getDivision() {
        return division;
    }
    String getSurname() {
        return surname;
    }
    String getName() {
        return name;
    }
    String getFathersName() {
        return fathersName;
    }
}