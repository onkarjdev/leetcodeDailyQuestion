class Solution {
    // Number of days in each month for a non-leap year
    private static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = dateToDayOfYear(arriveAlice);
        int endA = dateToDayOfYear(leaveAlice);
        int startB = dateToDayOfYear(arriveBob);
        int endB = dateToDayOfYear(leaveBob);

        // Calculate overlap interval
        int startOverlap = Math.max(startA, startB);
        int endOverlap = Math.min(endA, endB);

        // If endOverlap >= startOverlap, return the range size, else 0
        return Math.max(0, endOverlap - startOverlap + 1);
    }

    // Helper method to convert "MM-DD" to day of the year (1 to 365)
    private int dateToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = 0;
        // Add days of all previous months
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += DAYS_IN_MONTHS[i];
        }
        
        // Add current month's day
        dayOfYear += day;

        return dayOfYear;
    }
}