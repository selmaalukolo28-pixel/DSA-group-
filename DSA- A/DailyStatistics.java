public class DailyStatistics {
    private int[] serviceTimes;
    private int count;

    public DailyStatistics(int capacity) {
        serviceTimes = new int[capacity];
        count = 0;
    }

    public void addServiceTime(int minutes) {
        if (count == serviceTimes.length) {
            System.out.println("Array full. Cannot add more service times.");
            return;
        }
        serviceTimes[count] = minutes;
        count++;
    }

    public int getTotalStudentsServed() {
        return count;
    }

    public int getTotalServiceTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += serviceTimes[i];
        }
        return total;
    }

    public double getAverageServiceTime() {
        if (count == 0) return 0;
        return (double) getTotalServiceTime() / count;
    }

    public int getHighestServiceTime() {
        if (count == 0) return -1;
        int highest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) {
                highest = serviceTimes[i];
            }
        }
        return highest;
    }

    public int getLowestServiceTime() {
        if (count == 0) return -1;
        int lowest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) {
                lowest = serviceTimes[i];
            }
        }
        return lowest;
    }

    public int getServicesLongerThan(int minutes) {
        int longerCount = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > minutes) {
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
