package Entity;

public class Doctor {
    private String doctorName;
    private Patient[] patients = new Patient[15];

    public Doctor() {}

    public Doctor(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void insertPatient(int index, Patient p) {
        patients[index] = p;
    }

    public Patient getPatient(int index) {
        return patients[index];
    }

    public void removePatient(int index) {
        patients[index] = null;
    }

    public void transferPatient(int from, int to, Doctor d) {
        d.insertPatient(to, patients[from]);
        patients[from] = null;
        System.out.println("Transfer Complete...");
    }

    public void showDoctor() {
        System.out.println("------ " + doctorName + " ------");
        for (int i = 0; i < patients.length; i++) {
            if (patients[i] != null) {
                patients[i].showPatient();
            }
        }
		System.out.println("=========================\n");
    }

    public String getDoctor() {
        String data = "**** " + doctorName + " ****\n";
        for (int i = 0; i < patients.length; i++) {
            if (patients[i] != null) {
                data += patients[i].getPatient();
            }
        }
		data+= "*******************************\n";
        return data;
    }

    public Patient[] getAllPatient() {
        return patients;
    }
}