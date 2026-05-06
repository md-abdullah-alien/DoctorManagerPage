package File;

import Entity.*;
import java.io.*;
import java.util.*;

public class FileIO {

    public static void loadFromFile(Doctor[] doctors) {
        try {
            Scanner sc = new Scanner(new File("./File/Doctors.txt"));
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(";");
                doctors[Integer.parseInt(data[0])] = new Doctor(data[1]);
            }
            sc.close();

            sc = new Scanner(new File("./File/Patients.txt"));
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(";");

                int doctorNo = Integer.parseInt(data[0]);
                int position = Integer.parseInt(data[1]);
                String name = data[2];
                int id = Integer.parseInt(data[3]);
                double bill = Double.parseDouble(data[4]);

                doctors[doctorNo].insertPatient(position, new Patient(name, id, bill));
            }
            sc.close();

        } catch (Exception e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveChangesInFile(Doctor[] doctors) {
        try {
            FileWriter doctorWriter = new FileWriter("./File/Doctors.txt");
            FileWriter patientWriter = new FileWriter("./File/Patients.txt");

            for (int i = 0; i < doctors.length; i++) {
                if (doctors[i] != null) {
                    doctorWriter.write(i + ";" + doctors[i].getDoctorName() + "\n");

                    Patient[] patients = doctors[i].getAllPatient();
                    for (int j = 0; j < patients.length; j++) {
                        if (patients[j] != null) {
                            patientWriter.write(i + ";" + j + ";" +
                                patients[j].getPatientName() + ";" +
                                patients[j].getPatientId() + ";" +
                                patients[j].getBill() + "\n");
                        }
                    }
                }
            }

            doctorWriter.close();
            patientWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}