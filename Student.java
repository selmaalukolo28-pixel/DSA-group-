/**
 * Represents one student record used across the service-centre simulation.
 * Used by ServiceQueue (Task A1) and StudentRecordList (Task A2).
 */
public class Student {
    private final String studentNo;
    private final String name;
    private final String serviceType;
    private final int estimatedServiceTime; // in minutes

    public Student(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNo() { return studentNo; }
    public String getName() { return name; }
    public String getServiceType() { return serviceType; }
    public int getEstimatedServiceTime() { return estimatedServiceTime; }

    @Override
    public String toString() {
        return "[" + studentNo + " | " + name + " | " + serviceType + " | " + estimatedServiceTime + " min]";
    }
}
