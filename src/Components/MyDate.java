package Components;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

public class MyDate {
    private int dd = 0, mm = 0, yyyy = 0;

    public MyDate() {}
    public MyDate(int dd, int mm, int yyyy) {
        this.dd = dd;
        this.mm = mm;
        this.yyyy = yyyy;
    }
    public MyDate(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.dd = calendar.get(Calendar.DAY_OF_MONTH);
        this.mm = calendar.get(Calendar.MONTH) + 1;
        this.yyyy = calendar.get(Calendar.YEAR);
    }
    public MyDate(java.sql.Date date) {
        if(date != null) {
            this.dd = date.toLocalDate().getDayOfMonth();
            this.mm = date.toLocalDate().getMonthValue();
            this.yyyy = date.toLocalDate().getYear();
        }
    }
    public MyDate(String date) {
        String[] words = date.split("/");
        this.dd = Integer.parseInt(words[0]);
        this.mm = Integer.parseInt(words[1]);
        this.yyyy = Integer.parseInt(words[2]);
    }

    public int getDd() {return dd;}
    public int getMm() {return mm;}
    public int getYyyy() {return yyyy;}
    public static MyDate getCurrentDate() {
        LocalDate date = LocalDate.now();
        return new MyDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
    public static MyDate getMinInMonth() {
        LocalDate currentDate = LocalDate.now();
        YearMonth ym = YearMonth.of(currentDate.getYear(), currentDate.getMonthValue());
        LocalDate date = ym.atDay(1);
        return new MyDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
    public static MyDate getMaxInMonth() {
        LocalDate currentDate = LocalDate.now();
        YearMonth ym = YearMonth.of(currentDate.getYear(), currentDate.getMonthValue());
        LocalDate date = ym.atEndOfMonth();
        return new MyDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public void setDd(int dd) {this.dd = dd;}
    public void setMm(int mm) {this.mm = mm;}
    public void setYyyy(int yyyy) {this.yyyy = yyyy;}

    public int compareTo(MyDate date) {
        if(this.yyyy < date.getYyyy()) return -1;
        else if(this.yyyy > date.getYyyy()) return 1;
        else { // Năm bằng nhau
            if(this.mm < date.getMm()) return -1;
            else if(this.mm > date.getMm()) return 1;
            else { // Tháng bằng nhau
                if(this.dd < date.getDd()) return -1;
                else if(this.dd > date.getDd()) return 1;
                return 0;
            }
        }
    }

    public boolean bettween(MyDate l, MyDate r) {
        return compareTo(l)>=0 && compareTo(r)<=0;
    }

    @Override
    public String toString() {return String.format("%02d/%02d/%04d", dd, mm, yyyy);}
    public java.sql.Date getSqlDate() {return java.sql.Date.valueOf(LocalDate.of(yyyy, mm, dd));}
    // Util Date
    public java.util.Date getUtilDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(yyyy, mm-1 , dd);
        return calendar.getTime();
    }
    public static java.util.Date getMinDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1000, 0, 1);
        return calendar.getTime();
    }
    public static java.util.Date getMaxDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(9999, 11, 31);
        return calendar.getTime();
    }
}
