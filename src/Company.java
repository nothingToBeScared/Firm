/*
4* Приложение Фирма
Написать класс фирма (firm.Firm), содержащий имя, адрес, заплатанный счет (сумма), сотрудников.
У каждого сотрудника (firm.Employee) есть имя, фамилия, ставка зарплаты (сумма), его личный
карточный счет (сумма), год поступления на работу, месяц поступления на работу, пол, отдел.
Класс фирма должен выполнять следующие функции:
— Вывести всех сотрудников фирмы на экран (метод void printAllEmployees())
— Вывести на экран всех сотрудников, отсортированных по зарплате (шейкерным методом)
— Выдать всем сотрудникам зарплату (перевести на карточный счет каждого сотрудника,
сумму равную зарплате сотрудника, с главного счета фирмы) (метод void giveSalaryForAll())
— Пересчитать всем сотрудникам дни отпуска (всем кто проработал больше 6 месяцев,
по 2 дня за каждый отработанный месяц) (метод void calcVocations())
 */

package src;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.FileWriter;

class Company {

    private String companyName;
    private String companyAddress;
    private int salaryAccount;
    private ArrayList<Employee> staff;

    Company(String cnm, String addr, int slrBal, ArrayList<Employee> stf) throws IOException {
        companyName = cnm;
        companyAddress = addr;
        salaryAccount = slrBal;
        staff = stf;

        String companyFile = cnm + ".txt";
        FileWriter fw = new FileWriter(companyFile);
        fw.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        fw.write(" создана компания со следующими данными\n");
        fw.write("   Имя компании: " + companyName + "\n");
        fw.write("   Адрес компании: " + companyAddress + "\n");
        fw.write("   Баланс зарплатного счета: " + salaryAccount + "\n");
        fw.write("   Список сотрудников: " + staff.toString() + "\n");
        fw.write("\n - - - - - - - - - - - - - -\n\n");
        fw.flush();
        fw.close();
    }
    Company(String cnm, ArrayList<Employee> stf) throws IOException {
        this(cnm, " ", 1_000_000, stf);
    }
    Company(String cnm, Employee[] arr) throws IOException {
        this(cnm, " ", 1_000_000, new ArrayList<Employee>());
        for (int i = 0; i < arr.length; i++) {
            staff.add(arr[i]);
        }
    }
    Company(String cnm, int slrBal) throws IOException {
        this(cnm, " ", slrBal, new ArrayList<Employee>());

        String companyFile = cnm + ".txt";
        FileWriter fw = new FileWriter(companyFile);
        fw.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        fw.write(" создана компания со следующими данными\n");
        fw.write("Имя компании: " + companyName + "\n");
        fw.write("Адрес компании: " + companyAddress + "\n");
        fw.write("Баланс зарплатного счета: " + salaryAccount + "\n");
        fw.write("Список сотрудников: " + staff.toString() + "\n");
        fw.write("\n - - - - - - - - - - - - - -\n\n");
        fw.flush();
        fw.close();
    }
    Company(String cnm) throws IOException {
        this(cnm, " ", 1_000_000, new ArrayList<Employee>());

        String companyFile = cnm + ".txt";
        FileWriter fw = new FileWriter(companyFile);
        fw.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        fw.write(" создана компания со следующими данными\n");
        fw.write("Имя компании: " + companyName + "\n");
        fw.write("Адрес компании: " + companyAddress + "\n");
        fw.write("Баланс зарплатного счета: " + salaryAccount + "\n");
        fw.write("Список сотрудников: " + staff.toString() + "\n");
        fw.write(" - - - - - - - - - - - - - -\n\n");
        fw.flush();
        fw.close();
    }
    Company() throws IOException {
        this("Not defined", " ", 1_000_000, new ArrayList<Employee>());
    }

    void writeToFile(String operation, String descr) throws IOException {
        String companyFile = companyName + ".txt";
        FileWriter fw = new FileWriter(companyFile, true);
        fw.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        fw.write(" проведена операция \"" + operation + "\" со слежующими деталями:\n");
        fw.write(descr + "\nСостояние после операции: \n");
        fw.write("   Имя компании: " + companyName + "\n");
        fw.write("   Адрес компании: " + companyAddress + "\n");
        fw.write("   Баланс зарплатного счета: " + salaryAccount + "\n");
        fw.write("   Список сотрудников:\n");
        Manager man;
        SalesPerson sp;
        for (Employee e : staff) {
            fw.write(e.getEmployeeFullName() + ", " + e.getPosition() + ", ДР: " + e.getBirthday() + ", Пол: " + e.getSex() +
                    ", принят на работу: " + e.getHireMonth() + "'" + e.getHireYear()  + ", дней отпуска: " + e.getVacationDays());
            if (e.getClass().getName().equals("firm.Manager")) {
                man = (Manager) e;
                fw.write(", ЗП (+бонус): " + (e.getSalary() + man.getSalaryExtraForPeople()) + ", баланс карточного счета: " + e.getCardBalance());
            } else if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                fw.write(", ЗП (+бонус): " + (e.getSalary() + sp.getSalaryExtraSales()) + ", баланс карточного счета: " + e.getCardBalance());
            } else {
                fw.write(", ЗП: " + e.getSalary() + ", баланс карточного счета: " + e.getCardBalance());
            }
            fw.write("\n");
        }
        fw.write("\n - - - - - - - - - - - - - -\n\n");
        fw.flush();
        fw.close();
    }

    void setCompanyName(String newname) throws IOException {
        companyName = newname;
        writeToFile("Обновлено имя компании", "");
    }
    String getCompanyName() {
        return companyName;
    }
    void setCompanyAddress(String add) throws IOException {
        companyAddress = add;
        writeToFile("Обновлен адрес сотрудников", "");
    }
    String getCompanyAddress() {
        return companyAddress;
    }
    void updateSalaryAccount(int change) throws IOException {
        if (salaryAccount + change < 0) {
            System.out.println("Cannot update account balance to negative!");
        } else {
            salaryAccount += change;
        }
        writeToFile("Обновлен баланс зарплатного счета компании", "");
    }
    int getSalaryAccountBalance() {
        return salaryAccount;
    }

    void addOneEmployee(Employee e) throws IOException {
        staff.add(e);
        String descr = "В штат добавлен сотрудник " + e.getEmployeeFullName();
        writeToFile("Добавлен сотрудник", descr);
    }
    void addEmployees(Employee[] e) throws IOException {
        String descr = "";
        for (int i = 0; i < e.length; i++) {
            staff.add(e[i]);
            descr = descr + "В штат добавлен сотрудник " + e[i].getEmployeeFullName() + "\n";
        }
        writeToFile("Добавлен список сотрудников", descr);
    }
    void addEmployees(ArrayList<Employee> e) throws IOException {
        String descr = "";
        for (int i = 0; i < e.size(); i++) {
            staff.add(e.get(i));
            descr = descr + "В штат добавлен сотрудник " + e.get(i).getEmployeeFullName() + "\n";
        }
        writeToFile("Добавлен список сотрудников", descr);
    }

    void printAllEmployees() {
        System.out.println("Список сотрудников (неотсортированный): ");
        Manager man;
        SalesPerson sp;
        for (Employee e : staff) {
            System.out.print(e.getEmployeeFullName() + ", " + e.getPosition() + ", ДР: " + e.getBirthday() + ", Пол: " + e.getSex() +
                    ", принят на работу: " + e.getHireMonth() + "'" + e.getHireYear()  + ", дней отпуска: " + e.getVacationDays());
            if (e.getClass().getName().equals("firm.Manager")) {
                man = (Manager) e;
                System.out.println(", ЗП (+бонус): " + (e.getSalary() + man.getSalaryExtraForPeople()) + ", баланс карточного счета: " + e.getCardBalance());
            } else if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                System.out.println(", ЗП (+бонус): " + (e.getSalary() + sp.getSalaryExtraSales()) + ", баланс карточного счета: " + e.getCardBalance());
            } else {
                System.out.println(", ЗП: " + e.getSalary() + ", баланс карточного счета: " + e.getCardBalance());
            }
        }
        System.out.println();
    }
    void printEmployeesSortedByName() {
        ArrayList<Employee> staffClone = new ArrayList<> (staff);
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getClass().getName().equals("firm.Manager")) {
                staffClone.set(i, new Manager((Manager) staff.get(i)));
            } else if (staff.get(i).getClass().getName().equals("firm.SalesPerson")) {
                staffClone.set(i, new SalesPerson((SalesPerson) staff.get(i)));
            } else {
                staffClone.set(i, new Employee(staff.get(i)));
            }
        }
        int start = 0;
        int finish = staffClone.size() - 1;

        Manager man;
        SalesPerson sp;
        for (Employee e : staffClone) {
            if (e.getClass().getName().equals("firm.Manager")) {
                man = (Manager) e;
                man.setSalary(man.getSalary() + man.getSalaryExtraForPeople());
            } else if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                sp.setSalary(e.getSalary() + sp.getSalaryExtraSales());
            }
        }

        Employee temp;
        while (start < finish) {
            for (int i = start; i < finish; i++) {
                if (staffClone.get(i).getEmployeeFullName().charAt(0) > staffClone.get(i+1).getEmployeeFullName().charAt(0)) {
                    temp = staffClone.get(i);
                    staffClone.set(i, staffClone.get(i+1));
                    staffClone.set(i+1, temp);
                }
            }
            finish--;

            for (int i = finish; i > start; i--) {
                if (staffClone.get(i).getEmployeeFullName().charAt(0) < staffClone.get(i-1).getEmployeeFullName().charAt(0)) {
                    temp = staffClone.get(i);
                    staffClone.set(i, staffClone.get(i-1));
                    staffClone.set(i-1, temp);
                }
            }
            start++;
        }
        System.out.println("Список сотрудников, отсортированных по имени:");
        for (Employee e : staffClone) {
            System.out.println(e.getEmployeeFullName() + ", " + e.getPosition() + ", ДР: " + e.getBirthday() + ", Пол: " + e.getSex() +
                    ", принят на работу: " + e.getHireMonth() + "'" + e.getHireYear()  + ", дней отпуска: " + e.getVacationDays() +
                    ", ЗП(+бонус): " + e.getSalary() + ", баланс карточного счета: " + e.getCardBalance());
        }
        System.out.println();
    }
    void printEmployeesSortedBySalary() {
        ArrayList<Employee> staffClone = new ArrayList<> (staff);
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getClass().getName().equals("firm.Manager")) {
                staffClone.set(i, new Manager((Manager) staff.get(i)));
            } else if (staff.get(i).getClass().getName().equals("firm.SalesPerson")) {
                staffClone.set(i, new SalesPerson((SalesPerson) staff.get(i)));
            } else {
                staffClone.set(i, new Employee(staff.get(i)));
            }
        }
        int start = 0;
        int finish = staffClone.size() - 1;
        Manager man;
        SalesPerson sp;
        for (Employee e : staffClone) {
            if (e.getClass().getName().equals("firm.Manager")) {
                man = (Manager) e;
                man.setSalary(man.getSalary() + man.getSalaryExtraForPeople());
            } else if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                sp.setSalary(e.getSalary() + sp.getSalaryExtraSales());
            }
        }

        Employee temp;
        while (start < finish) {
            for (int i = start; i < finish; i++) {
                if (staffClone.get(i).getSalary() > staffClone.get(i+1).getSalary()) {
                    temp = staffClone.get(i);
                    staffClone.set(i, staffClone.get(i+1));
                    staffClone.set(i+1, temp);
                }
            }
            finish--;

            for (int i = finish; i > start; i--) {
                if (staffClone.get(i).getSalary() < staffClone.get(i-1).getSalary()) {
                    temp = staffClone.get(i);
                    staffClone.set(i, staffClone.get(i-1));
                    staffClone.set(i-1, temp);
                }
            }
            start++;
        }
        System.out.println("Список сотрудников, отсортированных по зарплате:");
        for (Employee e : staffClone) {
            System.out.println(e.getEmployeeFullName() + ", " + e.getPosition() + ", ДР: " + e.getBirthday() + ", Пол: " + e.getSex() +
                    ", принят на работу: " + e.getHireMonth() + "'" + e.getHireYear()  + ", дней отпуска: " + e.getVacationDays() +
                    ", ЗП(+бонус): " + e.getSalary() + ", баланс карточного счета: " + e.getCardBalance());
        }
        System.out.println();
    }
    void giveSalaryForAll() throws IOException {
        Manager man;
        SalesPerson sp;
        int salaryPaid = 0;
        String descr = "";
        for (Employee e : staff) {
            if (e.getClass().getName().equals("firm.Manager")) {
                man = (Manager) e;
                if (e.getSalary() + man.getSalaryExtraForPeople() < salaryAccount) {
                    e.refillCardBalance(e.getSalary() + man.getSalaryExtraForPeople());
                    salaryAccount -= (e.getSalary() + man.getSalaryExtraForPeople());
                    descr = descr + "Зарплата " + (e.getSalary() + man.getSalaryExtraForPeople()) + " выдана сотруднику: " + e.getEmployeeFullName() + "\n";
                    salaryPaid += (e.getSalary() + man.getSalaryExtraForPeople());
                } else {
                    e.refillCardBalance(salaryAccount);
                    salaryAccount -=salaryAccount;
                    descr = descr + "Зарплата выдана не полностью сотруднику: " + e.getEmployeeFullName() + ", выдано " + salaryAccount + "\n";
                    salaryPaid += salaryAccount;
                }
            } else if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                if (e.getSalary() + sp.getSalaryExtraSales() < salaryAccount) {
                    e.refillCardBalance(e.getSalary() + sp.getSalaryExtraSales());
                    salaryAccount -= (e.getSalary() + sp.getSalaryExtraSales());
                    descr = descr + "Зарплата " + (e.getSalary() + sp.getSalaryExtraSales()) + " выдана сотруднику: " + e.getEmployeeFullName() + "\n";
                    salaryPaid += (e.getSalary() + sp.getSalaryExtraSales());
                } else {
                    e.refillCardBalance(salaryAccount);
                    salaryAccount -=salaryAccount;
                    descr = descr + "Зарплата выдана не полностью сотруднику: " + e.getEmployeeFullName() + ", выдано " + salaryAccount + "\n";
                    salaryPaid += salaryAccount;
                }
            } else {
                if (e.getSalary() < salaryAccount) {
                    e.refillCardBalance(e.getSalary());
                    salaryAccount -= e.getSalary();
                    descr = descr + "Зарплата " + e.getSalary() + " выдана сотруднику: " + e.getEmployeeFullName() + "\n";
                    salaryPaid += e.getSalary();
                } else {
                    e.refillCardBalance(salaryAccount);
                    salaryAccount -=salaryAccount;
                    descr = descr + "Зарплата выдана не полностью сотруднику: " + e.getEmployeeFullName() + ", выдано " + salaryAccount + "\n";
                    salaryPaid += salaryAccount;
                }
            }
        }
        descr = descr + "Суммарно выплачено " + salaryPaid + "зарплаты.\n";
        System.out.println(descr);
        writeToFile("Выдана зарплата сотрудникам", descr);
    }

    void calculateVacation() throws IOException {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int months;
        String descr = "";
        for (Employee e : staff) {
            if (e.getHireYear() != 0 && e.getHireMonth() != 0) {
                months = (currentYear - e.getHireYear()) * 12 + currentMonth - e.getHireMonth();
                if (months >= 6) {
                    e.setVacationDays(months * 2);
                    descr = descr + "Сотрудник " + e.getEmployeeFullName() + " получил " + (months * 2) + " дней отпуска.\n";
                } else {
                    e.setVacationDays(0);
                }
            }
        }
        writeToFile("Сотрудникам рассчитаны дни отпуска", descr);
    }
    void salesStaffToSell(int amount) throws IOException  {
        String description = "";
        for (Employee e : this.staff) {
            SalesPerson sp;
            if (e.getClass().getName().equals("firm.SalesPerson")) {
                sp = (SalesPerson) e;
                sp.setSalesVolume(amount);
                sp.setSalaryExtraSales();
                description = description + "Сотрудник " + e.getEmployeeFullName() + " осуществил продажу на " + amount + " грн, бонус сотрудника " + sp.getSalaryExtraSales() + "\n";
            }
        }
        System.out.println(description);
        writeToFile("Сотрудники из отдела продаж осуществили продажу", description);
    }
    void increaseSalaryAfter5Years(int incPer) throws IOException  {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int months;
        String descr = "";
        for (Employee e : staff) {
            months = (currentYear - e.getHireYear()) * 12 + currentMonth - e.getHireMonth();
            if (months > 5 * 12) {
                e.changeSalary(incPer);
                descr = descr + "Зарплата сотруднику " + e.getEmployeeFullName() + " повышена на " + incPer + " процентов.\n";
            }
        }
        System.out.println(descr);
        writeToFile("Сотрудникам повышена зарплата", descr);
    }
    void giveHolidayBonus(LocalDate ld, int amount) throws IOException {
        String descr = "";
        if (ld.getMonthValue() == 3 && ld.getDayOfMonth() == 8) {
            descr = descr + "Праздничный бонус в честь 8 марта выдан сотрудникам:\n";
            for (Employee e : staff) {
                if (e.getSex() == Sex.Жен) {
                    if (amount < salaryAccount) {
                        e.refillCardBalance(amount);
                        salaryAccount -= amount;
                        descr = descr + "Сотруднику " + e.getEmployeeFullName() + " выдано " + amount + "\n";
                    } else {
                        e.refillCardBalance(salaryAccount);
                        salaryAccount -= salaryAccount;
                        descr = descr + "Сотруднику " + e.getEmployeeFullName() + " бонус выдан не полностью: " + amount + "\n";
                    }
                }
            }
        } else if (ld.getMonthValue() == 2 && ld.getDayOfMonth() == 23) {
            descr = descr + "Праздничный бонус в честь 23 февраля выдан сотрудникам:\n";
            for (Employee e : staff) {
                if (e.getSex() == Sex.Муж) {
                    if (amount < salaryAccount) {
                        e.refillCardBalance(amount);
                        salaryAccount -= amount;
                        descr = descr + "Сотруднику " + e.getEmployeeFullName() + " выдано " + amount + "\n";
                    } else {
                        e.refillCardBalance(salaryAccount);
                        salaryAccount -= salaryAccount;
                        descr = descr + "Сотруднику " + e.getEmployeeFullName() + " бонус выдан не полностью: " + amount + "\n";
                    }
                }
            }
        }
        System.out.println(descr);
        writeToFile("Сотрудникам выдан праздничный бонус", descr);
    }
    void reduceStaff() throws IOException {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int saving = 0;
        String descr = "";
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < staff.size(); i++) {

            if ((currentYear - staff.get(i).getHireYear()) * 12 + currentMonth - staff.get(i).getHireMonth() < 6) {
                saving += staff.get(i).getSalary();
                indexes.add(i);

            } else if (staff.get(i).getBirthday().getDayOfMonth()%2 != 0) {
                saving += staff.get(i).getSalary();
                indexes.add(i);
            }
        }

        for (int i = indexes.size() - 1; i >= 0; i--) {
            descr = descr + "Сотрудник " + staff.get((int)indexes.get(i)).getEmployeeFullName() + " уволен из компании\n";
            staff.remove((int)indexes.get(i));
            // System.out.println(staff.get(indexes.get(i)).getEmployeeFullName());
            // System.out.println("Удален сотрудник с индексом " + indexes.get(i));
        }

        int remaining = staff.size();
        descr = descr + "Зарплата уволенных в размере " + saving + " поровну распределена между оставшимися " + remaining + " сотрудниками:\n";
        for (Employee e : staff) {
            e.setSalary(e.getSalary() + saving / staff.size());
            descr = descr + "Сотрудник " + e.getEmployeeFullName() + " получил " + saving / staff.size() + " прибавки (новая ЗП:" + e.getSalary() + ")\n";
        }
        System.out.println(descr);
        writeToFile("Уволены сотрудники со стажем меньше 6 месяцев и нечетным днем рождения", descr);
    }

    void hireNewPerson(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, String dvsn) throws IOException {
        Employee person = new Employee(srnm, nm, fnm, mf, bd, slr, dvsn);
        // share salary from other Employees
        String descr = "";
        descr = descr + "Новый сотрудник " + person.getEmployeeFullName() + " принят на работу с зарплатой " + slr + "\n";
        int reduce = slr / staff.size();
        descr = descr + "У каждого из " + staff.size() + "-x действующих сотрудников зарплата снижена на " + reduce + ":\n";
        for (Employee e : staff) {
            e.setSalary(e.getSalary() - reduce);
            descr = descr + "Сотруднику " + e.getEmployeeFullName() + " снижена зарплата на " + reduce + " для оплаты труда нового сотрудника\n";
        }

        staff.add(person);

        System.out.println(descr);
        writeToFile( "Принят новый сотрудник, зарплата сформирована путем снижения действующим сотрудникам", descr);
    }
}