package Entity;

public class Patient {
    private String patientName;
    private int patientId;
    private double bill;

    public Patient() {
        System.out.println("E-Patient Created.");
    }

    public Patient(String patientName, int patientId, double bill) {
        System.out.println("P-Patient Created.");
        setPatientName(patientName);
        setPatientId(patientId);
        setBill(bill);
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getBill() {
        return bill;
    }

    public void showPatient() {
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Bill: " + bill + " BDT");
    }

    public String getPatient() {
        return "Patient Name: " + patientName + "\n" +
               "Patient ID: " + patientId + "\n" +
               "Bill: " + bill + " BDT\n";
    }
}