package excercise;

import java.util.*;

/*Task************
        1 Jan 1900 was a Monday.

        Thirty days has September,
        April, June and November.
        All the rest have thirty-one,
        Saving February alone,
        Which has twenty-eight, rain or shine.
        And on leap years, twenty-nine.

        A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

        How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
******************/

public class SundaysCount {
    private static final Map<String, Integer> MONTHS = new LinkedHashMap<String, Integer>() {{
        put("Jan", 31);
        put("Feb", 28);
        put("Mar", 31);
        put("Apr", 30);
        put("May", 31);
        put("Jun", 30);
        put("Jul", 31);
        put("Aug", 31);
        put("Sep", 30);
        put("Oct", 31);
        put("Nov", 30);
        put("Dec", 31);
    }};

    private static final List<String> WEEKDAYS = new LinkedList<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    private int currentYear = 1900;
    private int currentDay = 1;
    private String currentWeekday = "Mon";
    private String currentMonth = "Jan";

    private int answer;

    public static void main(String[] args) {
        SundaysCount task = new SundaysCount();
        task.solve();
    }

    private void solve() {
        answer = 0;
        while(true) {
            if (currentYear == 2000 && currentMonth.equals("Dec") && currentDay == 31) {
                break;
            }
            makeNextDate();
            calculate();
        }
        System.out.println(answer + " Sundays fell on the first of the month during the twentieth century");
    }

    private void calculate() {
        if (currentYear >= 1901 && currentYear <=2000) {
            if(currentDay == 1) {
                if("Sun".equals(currentWeekday)) {
                    answer++;
                }
            }
        }
    }

    private void makeNextDate() {
        currentWeekday = getNextWeekday(currentWeekday);
        if(++currentDay > getMonthDays(currentMonth, currentYear)) {
            currentDay = 1;
        }
        if(currentDay == 1) {
            currentMonth = getNextMonth(currentMonth);
            if("Jan".equals(currentMonth)) {
                currentYear++;
            }
        }
    }

    private String getNextMonth(String currentMonth) {
        String firstMonth = "";
        boolean getNext = false;
        for(Map.Entry<String, Integer> entry : MONTHS.entrySet()) {
            if(getNext) {
                return entry.getKey();
            }
            if("".equals(firstMonth)) {
                firstMonth = entry.getKey();
            }
            if(currentMonth.equals(entry.getKey())) {
                getNext = true;
            }
        }
        return firstMonth;
    }

    private int getMonthDays(String currentMonth, int currentYear) {
        int days = MONTHS.get(currentMonth);
        if("Feb".equals(currentMonth)) {
            if(currentYear % 4 == 0) {
                if(currentYear % 100 == 0) {
                    if(currentYear % 400 != 0) {
                        return 28;
                    }
                }
                return 29;
            }
        }
        return days;
    }

    private String getNextWeekday(String currentWeekday) {
        String firstDay = "";
        boolean getNext = false;
        for(String weekday : WEEKDAYS) {
            if(getNext) {
                return weekday;
            }
            if("".equals(firstDay)) {
                firstDay = weekday;
            }
            if(weekday.equals(currentWeekday)) {
                getNext = true;
            }
        }
        return firstDay;
    }

}
