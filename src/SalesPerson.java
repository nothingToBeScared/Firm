/*
Продавец (продажи, зп + 30% от продаж)
*/

package src;

import java.time.LocalDate;

class SalesPerson extends Employee {
    private int salesVolume;
    private int salaryExtraSales;
    private int extraPercent = 30;

    SalesPerson(String srnm, String nm, String fnm, Sex mf, LocalDate bd, int slr, int cb, int hryear, int hrmonth, int vac, int slsVolume) {
        super(srnm, nm, fnm, mf, bd, slr, cb, hryear, hrmonth, "Отдел продаж", vac);
        salesVolume = slsVolume;
        salaryExtraSales = (int) slsVolume * extraPercent / 100;
    }
    SalesPerson(SalesPerson obj) {
        this(obj.getSurname(), obj.getName(), obj.getFathersName(), obj.getSex(), obj.getBirthday(), obj.getSalary(), obj.getCardBalance(),
                obj.getHireYear(), obj.getHireMonth(), obj.getVacationDays(), obj.salesVolume);
    }
    SalesPerson(String srnm, String nm, String fnm, int slr, int slsVolume) {
        this(srnm, nm, fnm, Sex.valueOf("НеУказан"), LocalDate.of(0000, 01, 02), slr, 0,
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 0, slsVolume);
    }
    public void setSalesVolume(int vol) {
        salesVolume = vol;
    }
    int getSalaryExtraSales() {
        return salesVolume * extraPercent / 100;
    }
    void setSalaryExtraSales() {
        salaryExtraSales = salesVolume * extraPercent / 100;
    }
}
