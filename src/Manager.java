/*
Менеджер (отдел, подчиненные, зп + по 50 за подчененного)
*/

package src;

import java.time.LocalDate;

class Manager extends Employee {
    private int subordinates;
    private int salaryExtraForPeople;
    private int extraForOneSub = 500;

    Manager(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int cb, int hryear, int hrmonth, String dvsn, int vac, int subord) {
        super(srnm, nm, fnm, mf, bd, slr, cb, hryear, hrmonth, dvsn, vac);
        subordinates = subord;
        salaryExtraForPeople = subord * extraForOneSub;
    }
    Manager (Manager obj) {
        this(obj.getSurname(), obj.getName(), obj.getFathersName(), obj.getSex(), obj.getBirthday(), obj.getSalary(), obj.getCardBalance(),
                obj.getHireYear(), obj.getHireMonth(), obj.getDivision(), obj.getVacationDays(), obj.subordinates);

    }
    Manager(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, String dvsn, int subord) {
        this(srnm, nm, fnm, mf, bd, slr, 0, LocalDate.now().getYear(), LocalDate.now().getMonthValue(), dvsn, 0, subord);
    }

    int getSalaryExtraForPeople() {
        return salaryExtraForPeople;
    }
}
