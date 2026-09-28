/**
 * Task A4 - Daily Statistics computed from an Array of service times.
 * All calculations use manual traversal - no built-in max(), min(), sum(), etc.
 */
public class DailyStatistics {
    private int[] serviceTime;
    private int count;

    public DailyStatistics(int capacity) {
        serviceTime = new int[capacity];
        count = 0;
    }

    public DailyStatistics() {
    }

    public void serviceTime(int minutes) {
        if (count == serviceTime.length) {
            System.out.println("Array full. Cannot add more service times.");
            return;
        }
        serviceTime[count] = minutes;
        count++;
    }

    public int getTotalStudentsServed() {
        return count;
    }

    public int getTotalServiceTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += serviceTime[i];
        }
        return total;
    }

    public double getAverageServiceTime() {
        if (count == 0) return 0;
        return (double) getTotalServiceTime() / count;
    }

    public int getHighestServiceTime() {
        if (count == 0) return -1;
        int highest = serviceTime[0];
        for (int i = 1; i < count; i++) {
            if (serviceTime[i] > highest) {
                highest = serviceTime[i];
            }
        }
        return highest;
    }

    public int getLowestServiceTime() {
        if (count == 0) return -1;
        int lowest = serviceTime[0];
        for (int i = 1; i < count; i++) {
            if (serviceTime[i] < lowest) {
                lowest = serviceTime[i];
            }
        }
        return lowest;
    }

    public int getServicesLongerThan(int minutes) {
        int longerCount = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTime[i] > minutes) {
                longerCount++;
            }
        }
        return longerCount;
    }

    public void displayStatistics() {
        System.out.println("---- Daily Statistics ----");
        System.out.println("Total students served: " + getTotalStudentsServed());
        System.out.println("Total service time: " + getTotalServiceTime() + " min");
        System.out.printf("Average service time: %.2f min%n", getAverageServiceTime());
        System.out.println("Highest service time: " + getHighestServiceTime() + " min");
        System.out.println("Lowest service time: " + getLowestServiceTime() + " min");
        System.out.println("Services longer than 10 min: " + getServicesLongerThan(10));
        System.out.println("---------------------------");
    }
}
