import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Database {
    public static ArrayList<String[]> StudentCmd(String lastname, boolean busOption) {
        File datafile = new File("students.txt");
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(datafile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[0].equalsIgnoreCase(lastname)) {
                    if (busOption) {
                        String current[] = {splitData[1], splitData[4]};
                        result.add(current);
                    } else {
                        String teacherName = splitData[6] + " " + splitData[7];
                        String[] current = {splitData[1], splitData[2], splitData[3], teacherName};
                        result.add(current);
                    }
                }
            }
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }
    public static double average(String grade) {
        File datafile = new File("students.txt");
        double avg = 0.0;
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(datafile);

            while (fileScanner.hasNext()) {

                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[2].equalsIgnoreCase(grade)) {
                    //Add gpa to avg, add 1 to count
                    avg += Double.parseDouble(splitData[5]);
                    count++;
                }
            }}
        catch(FileNotFoundException ex){
            System.out.println("Error opening file");
            }
        if (count == 0) {
            return 0.0;
        }
        return avg / count;
    }


    public static ArrayList<String[]> Grade(String grade, int option) {
        File datafile = new File("students.txt");
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(datafile);
            if (option == 1) {
                while (fileScanner.hasNext()) {

                    String data = fileScanner.nextLine();
                    String[] splitData = data.split(",");
                    if (splitData[2].equalsIgnoreCase(grade)) {

                        String current[] = {splitData[0], splitData[1]};
                        result.add(current);

                        }
                    }
                }
            else if (option == 2) {
                double gpa = -1;
                while (fileScanner.hasNext()) {

                    String data = fileScanner.nextLine();
                    String[] splitData = data.split(",");
                    double newGpa = Double.parseDouble(splitData[5]);
                    if (splitData[2].equalsIgnoreCase(grade) && newGpa > gpa) {

                        String current[] = {splitData[0], splitData[1], splitData[2], splitData[3],
                                            splitData[4], splitData[5], splitData[6], splitData[7]};
                        if (result.isEmpty()) {
                            result.add(current);  // Add the first element
                        } else {
                            result.set(0, current);  // Replace the element at index 0
                        }                        gpa = newGpa;
                    }
                }
            }
            else if (option == 3) {
                double gpa = 100.0;
                while (fileScanner.hasNext()) {

                    String data = fileScanner.nextLine();
                    String[] splitData = data.split(",");
                    double newGpa = Double.parseDouble(splitData[5]);
                    if (splitData[2].equalsIgnoreCase(grade) && newGpa < gpa) {

                        String current[] = {splitData[0], splitData[1], splitData[2], splitData[3],
                                splitData[4], splitData[5], splitData[6], splitData[7]};
                        if (result.isEmpty()) {
                            result.add(current);  // Add the first element
                        } else {
                            result.set(0, current);  // Replace the element at index 0
                        }                        gpa = newGpa;
                    }
                }
            }

            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }

    public static int totalNumberOfStudentsInGrade(int grade) {
        File datafile = new File("students.txt");
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(datafile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (Integer.parseInt(splitData[2]) == grade) {
                    count++;
                }
            }
            return count;
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred opening file");
        }
        return -1;
    }



    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputString = "";
        while (true) {
            System.out.println("Please select: [S,T,B,G,A,I, or Q]. ");
            inputString = inputScanner.nextLine();
            String[] splitStr = inputString.split("\\s");
            String cmd = splitStr[0];
            switch (cmd) {
                // Quit command
                case "Q":
                case "Quit":
                    System.out.println("Exiting Program...");
                    return;                // Info command
                case "I":
                case "Info":
                    for (int i = 0; i < 7; i++) {
                        System.out.println("Students in grade " + i + ": " + totalNumberOfStudentsInGrade(i));
                    }
                    continue;
                    // Student command
                case "S":
                case "Student":
                    if (splitStr.length == 1 || splitStr[1] == null) {
                        System.out.println("Invalid Parameters: Student last name required.");
                        continue;
                    }
                    if (splitStr.length == 2) {
                        ArrayList<String[]> result = StudentCmd(splitStr[1], false);
                        assert result != null;
                        for (String[] student : result) {
                            System.out.println("Student last name: " + splitStr[1].toUpperCase());
                            System.out.println("Student first name: " + student[0]);
                            System.out.println("Student grade: " + student[1]);
                            System.out.println("Student classroom: " + student[2]);
                            System.out.println("Student teacher: " + student[3]);
                            System.out.println();
                        }
                    }
                    //If Bus Number is included
                    else if (splitStr.length == 3 && (splitStr[2].equals("B") || splitStr[2].equals("Bus"))) {
                            ArrayList<String[]> result = StudentCmd(splitStr[1], true);
                            assert result != null;
                            if (result.isEmpty()) {
                                System.out.println("No entries found.");
                                continue;
                            }
                            for (String[] student : result) {
                                System.out.println("Student last name: " + splitStr[1].toUpperCase());
                                System.out.println("Student first name: " + student[0]);
                                System.out.println("Student bus route: " + student[1]);
                            }
                    } else { //Call Function with bus number included
                        System.out.println("Error - invalid arguments");
                    }
                    continue;
                case "T":
                case "Teacher":
                    if (splitStr[1] == null) {
                        System.out.println("Invalid Parameters: Student last name required.");
                        continue;
                    }
                    //Bus Option
                    else if (splitStr[0].equals("B") || splitStr[0].equals("Bus")) {
                        if (splitStr[1] == null) {
                            System.out.println("Invalid Parameters: Bus number required.");
                            continue;
                        }
                    }
                    //Grade Option
                    else if (splitStr[0].equals("G") || splitStr[0].equals("Grade")) {
                        if (splitStr[1] == null) {
                            System.out.println("Invalid Parameters: Grade required.");
                            continue;
                        }
                    }
                    //Average Option
                    else if (splitStr[0].equals("A") || splitStr[0].equals("Average")) {
                        if (splitStr[1] == null) {
                            System.out.println("Invalid Parameters: Grade required.");
                            continue;
                        }
                    }
                case "G":
                case "Grade":
                    if (splitStr.length < 2) {
                        System.out.println("Invalid Parameters: Grade required.");
                        continue;
                    }
                    //Option 1: No 2nd param;   Option 2: High;   Option 3: Low
                    int option = 1;

                    if (splitStr.length > 2) {
                        if (splitStr[2].equals("H") || splitStr[2].equals("High")) {
                            option = 2;
                        }
                        else if (splitStr[2].equals("L") || splitStr[2].equals("Low")) {
                            option = 3;
                        }

                    }
                    ArrayList<String[]> result = Grade(splitStr[1], option);
                    for (String[] grade : result) {
                        for (int i = 0; i < grade.length; i++) {
                            System.out.print(grade[i]);
                            if (i < grade.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("");

                    }


                        continue;
                case "A":
                case "Average":
                    if (splitStr.length != 2) {
                        System.out.println("Invalid Parameters: Only grade required.");
                    } else {
                        double avg = average(splitStr[1]);
                        String noEntry = "";
                        BigDecimal avgRound = new BigDecimal(avg);
                        avgRound = avgRound.setScale(2, RoundingMode.HALF_UP);  // Rounds to 2 decimal places
                        if (avg == 0.00) {
                            noEntry = " (No entries for this grade)";
                        }

                        System.out.println("Grade: " + splitStr[1]);
                        System.out.println("Average GPA: " + avgRound + noEntry);

                    }
                    continue;

                default:
                    System.out.println("Error - Invalid command");
            }
        }
    }
}

