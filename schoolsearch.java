import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class schoolsearch {



    public static boolean checkFormat(String line) {
        String[] splitLine = line.split(",");
        for (int i = 0; i < splitLine.length; i++) {
            if (i >= 2 && i <= 5) {
                try {
                    Double.parseDouble(splitLine[i]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            if (i > 7) {
                return false;
            }
        }
        return true;
    }

    // Student takes in LIST FILE, TEACHER FILE
    public static ArrayList<String[]> Student(String lastname, boolean busOption, File listFile, File teacherFile) {

        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(listFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[0].equalsIgnoreCase(lastname)) {
                    if (busOption) {
                        String current[] = {splitData[1], splitData[4]};
                        result.add(current);
                    } else {
                        String teacherLastName = "NULL";
                        String teacherFirstName = "NULL";
                        try {
                            Scanner teacherScanner = new Scanner(teacherFile);
                            while (teacherScanner.hasNext()) {
                                String teacherData = teacherScanner.nextLine();
                                String[] splitTeacherData = teacherData.split(",");
                                if (splitTeacherData[2].equals(splitData[3])) {
                                    teacherLastName = splitTeacherData[0];
                                    teacherFirstName = splitTeacherData[1];
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("ERROR - teachers file not found");
                            System.exit(0);
                        }
                        String[] current = {splitData[1], splitData[2], splitData[3], teacherLastName, teacherFirstName};
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
    //Bus takes in LIST FILE
    public static ArrayList<String[]> Bus(String busNum, File datafile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(datafile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[4].equalsIgnoreCase(busNum)) {
                        String[] current = {splitData[0], splitData[1], splitData[2], splitData[3]};
                        result.add(current);
                }
            }
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }

    //ClassroomTeacher takes in TEACHER FILE
    public static ArrayList<String[]> ClassroomTeacher(String classNum, File teacherFile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(teacherFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[2].equalsIgnoreCase(classNum)) {
                    String[] current = {splitData[0], splitData[1]};
                    result.add(current);
                }
            }
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }


    //Classroom takes in LIST FILE
    public static ArrayList<String[]> Classroom(String classNum, File listFile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(listFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                    if (splitData[3].equalsIgnoreCase(classNum)) {
                        String[] current = {splitData[0], splitData[1]};
                        result.add(current);
                    }
            }
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }
    // Teacher takes in LIST FILE, TEACHER FILE
    public static ArrayList<String[]> Teacher(String teacher, File listFile, File teacherFile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner teacherScanner = new Scanner(teacherFile);
            while (teacherScanner.hasNext()) {
                String data = teacherScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[0].equalsIgnoreCase(teacher)) {
                    String classroom = splitData[2];
                    Scanner listScanner = new Scanner(listFile);
                    while (listScanner.hasNext()) {
                        String studentData = listScanner.nextLine();
                        String[] splitStudentData = studentData.split(",");
                        if (splitStudentData[3].equals(classroom)) {
                            String[] current = {splitStudentData[0], splitStudentData[1]};
                            result.add(current);
                        }
                    }
                }
            }
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file");
        }
        return null;
    }
    //Average takes in LIST FILE
    public static double Average(String grade, File listFile) {
        double avg = 0.0;
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(listFile);

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

    // Enrollments takes in LIST FILE
    public static int Enrollments(File listFile, String classroom) {
        int result = 0;
        try {
            Scanner fileScanner = new Scanner(listFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[3].equals(classroom)) {
                    ++result;
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Grade takes in LIST FILE, TEACHER FILE
    public static ArrayList<String[]> Grade(String grade, int option, File listFile, File teacherFile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(listFile);
            if (option == 1) {
                while (fileScanner.hasNext()) {

                    String data = fileScanner.nextLine();
                    String[] splitData = data.split(",");
                    if (splitData[2].equalsIgnoreCase(grade)) {

                        String[] current = {splitData[0], splitData[1]};
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
                        String teacherLastName = "NULL";
                        String teacherFirstName = "NULL";
                        try {
                            Scanner teacherScanner = new Scanner(teacherFile);
                            while (teacherScanner.hasNext()) {
                                String teacherData = teacherScanner.nextLine();
                                String[] splitTeacherData = teacherData.split(",");
                                if (splitTeacherData[2].equals(splitData[3])) {
                                    teacherLastName = splitTeacherData[0];
                                    teacherFirstName = splitTeacherData[1];
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("ERROR - teachers file not found");
                            System.exit(0);
                        }
                        String[] current = {splitData[0], splitData[1], splitData[2], splitData[3],
                                            splitData[4], splitData[5], teacherLastName, teacherFirstName};
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
                        String teacherLastName = "NULL";
                        String teacherFirstName = "NULL";
                        try {
                            Scanner teacherScanner = new Scanner(teacherFile);
                            while (teacherScanner.hasNext()) {
                                String teacherData = teacherScanner.nextLine();
                                String[] splitTeacherData = teacherData.split(",");
                                if (splitTeacherData[2].equals(splitData[3])) {
                                    teacherLastName = splitTeacherData[0];
                                    teacherFirstName = splitTeacherData[1];
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("ERROR - teachers file not found");
                            System.exit(0);
                        }
                        String current[] = {splitData[0], splitData[1], splitData[2], splitData[3],
                                splitData[4], splitData[5], teacherLastName, teacherFirstName};
                        if (result.isEmpty()) {
                            result.add(current);  // Add the first element
                        } else {
                            result.set(0, current);  // Replace the element at index 0
                        }                        gpa = newGpa;
                    }
                }
            } else if (option == 4) {
                Scanner teacherScanner = new Scanner(teacherFile);
                while (teacherScanner.hasNext()) {
                    Scanner studentScanner = new Scanner(listFile);
                    boolean teachesGrade = false;
                    String teacherData = teacherScanner.nextLine();
                    String[] splitTeacherData = teacherData.split(",");
                    while (studentScanner.hasNext()) {
                        String studentData = studentScanner.nextLine();
                        String[] splitStudentData = studentData.split(",");
                        if (splitStudentData[2].equals(grade) &&
                                splitStudentData[3].equals(splitTeacherData[2])) {
                            teachesGrade = true;
                        }
                    }
                    if (teachesGrade) {
                        String[] current = {splitTeacherData[0], splitTeacherData[1]};
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
    //Info takes in STUDENT FILE
    public static int Info(int grade, File datafile) {
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
            System.exit(0);
        }
        return -1;
    }

    // CompareGpaTo takes in LIST FILE, TEACHER FILE
    public static ArrayList<String[]> compareGpaTo(int option, File listFile, File teacherFile) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(listFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String[] current = new String[3];
                switch(option) {
                    // Grade
                    case 1: {
                        current = new String[] {splitData[5], splitData[2]};
                        break;
                    }
                    // Bus Route
                    case 2: {
                        current = new String[] {splitData[5], splitData[4]};
                        break;
                    }
                    // Teacher
                    case 3: {
                        Scanner teacherScanner = new Scanner(teacherFile);
                        while (teacherScanner.hasNext()) {
                            String teacherData = teacherScanner.nextLine();
                            String[] splitTeacherData = teacherData.split(",");
                            if (splitTeacherData[2].equals(splitData[3])) {
                                current = new String[] {splitData[5], splitTeacherData[0], splitTeacherData[1]};
                            }
                        }
                        break;
                    }
                }
                result.add(current);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
       // File datafile = new File("students.txt");
        File listFile = new File("list.txt");
        File teacherFile = new File("teachers.txt");
        try {
            Scanner fileChecker1 = new Scanner(listFile);
            while (fileChecker1.hasNext()) {
                String data = fileChecker1.nextLine();
                if (!checkFormat(data)) {
                    System.out.println("Error - invalid file format");
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred opening file: Student File");
            System.exit(1);
        }
        try {
            Scanner fileChecker2 = new Scanner(teacherFile);
            while (fileChecker2.hasNext()) {
                String data = fileChecker2.nextLine();
                if (!checkFormat(data)) {
                    System.out.println("Error - invalid file format");
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred opening file: Teacher File");
            System.exit(1);
        }
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
                    return;
                // Info command
                case "I":
                case "Info":
                    for (int i = 0; i < 7; i++) {
                        System.out.println("Students in grade " + i + ": " + Info(i, listFile));
                    }
                    continue;
                    // Student command
                case "S:":
                case "Student:":
                    if (splitStr.length == 1 || splitStr[1] == null) {
                        System.out.println("Invalid Parameters: Student last name required.");
                        continue;
                    }
                    if (splitStr.length == 2) {
                        ArrayList<String[]> result = Student(splitStr[1], false, listFile, teacherFile);
                        assert result != null;
                        if (result.isEmpty()) {
                            System.out.println("No results found.");
                            continue;
                        }
                        for (String[] student : result) {
                            System.out.println(splitStr[1].toUpperCase()
                            + "," + student[0] + "," + student[1] + "," + student[2] + ","
                            + student[3] + "," + student[4]);
                        }
                    }
                    //If Bus Number is included
                    else if (splitStr.length == 3 && (splitStr[2].equals("B") || splitStr[2].equals("Bus"))) {
                            ArrayList<String[]> result = Student(splitStr[1], true, listFile, teacherFile);
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
                case "T:":
                case "Teacher:":
                    if (splitStr.length != 2) {
                        System.out.println("Invalid Arguments: Only teacher name required.");
                        continue;
                    }
                    ArrayList<String[]> teachRes = Teacher(splitStr[1], listFile, teacherFile);
                    for (String[] teach : teachRes) {
                        for (int i = 0; i < teach.length; i++) {
                            System.out.print(teach[i]);
                            if (i < teach.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("");

                    }

                    continue;
                case "G:":
                case "Grade:":
                    if (splitStr.length < 2) {
                        System.out.println("Invalid Parameters: Grade required.");
                        continue;
                    }
                    //Option 1: No 2nd param;   Option 2: High;   Option 3: Low; Option 4: Teacher
                    int option = 1;

                    if (splitStr.length > 2) {
                        if (splitStr[2].equals("H") || splitStr[2].equals("High")) {
                            option = 2;
                        }
                        else if (splitStr[2].equals("L") || splitStr[2].equals("Low")) {
                            option = 3;
                        }
                        else if (splitStr[2].equals("T") || splitStr[2].equals("Teacher")) {
                            option = 4;
                        }

                    }
                    ArrayList<String[]> result = Grade(splitStr[1], option, listFile, teacherFile);
                    for (String[] grade : result) {
                        for (int i = 0; i < grade.length; i++) {
                            System.out.print(grade[i]);
                            if (i < grade.length - 1) {
                                System.out.print(",");
                            }
                        }
                        System.out.println("");

                    }


                        continue;
                case "A:":
                case "Average:":
                    if (splitStr.length != 2) {
                        System.out.println("Invalid Parameters: Only grade required.");
                    } else {
                        double avg = Average(splitStr[1], listFile);
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
                case "Analytics:":
                case "Al:":
                    if (splitStr.length != 2) {
                        System.out.println("Invalid parameters: option required");
                    } else {
                        try {
                            int analyticsOption = Integer.parseInt(splitStr[1]);
                            ArrayList<String[]> analyticsResult = compareGpaTo(analyticsOption, listFile, teacherFile);
                            for (String[] analytics : analyticsResult) {
                                if (analytics.length == 2) {
                                    System.out.println(analytics[0] + "," + analytics[1]);
                                } else {
                                    System.out.println(analytics[0] + "," + analytics[1] + "," + analytics[2]);

                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid option");
                        }
                    }
                    continue;
                case "Bus:":
                case "B:":
                    if (splitStr.length != 2) {
                        System.out.println("Invalid Arguments: Only bus number required.");
                        continue;
                    }
                    ArrayList<String[]> res = Bus(splitStr[1], listFile);
                    for (String[] bus : res) {
                        for (int i = 0; i < bus.length; i++) {
                            System.out.print(bus[i]);
                            if (i < bus.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("");

                    }

                    continue;
                case "Enrollments":
                case "E":
                    for (int classroom = 101; classroom <= 112; ++classroom) {
                        System.out.print("Classroom: " + classroom + "; ");
                        System.out.println("Enrollments: " + Enrollments(listFile, Integer.toString(classroom)));
                    }
                case "Classroom:":
                case "C:":
                    if (splitStr.length < 2) {
                        System.out.println("Invalid Arguments.");
                        continue;
                    }
                    if (splitStr.length == 2) {
                        ArrayList<String[]> cr = Classroom(splitStr[1], listFile);
                        for (String[] cls : cr) {
                            for (int i = 0; i < cls.length; i++) {
                                System.out.print(cls[i]);
                                if (i < cls.length - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.println("");
                        }
                        continue;
                    }
                    if (splitStr.length == 3) {
                        if (splitStr[2].equals("T")) {
                            ArrayList<String[]> cr = ClassroomTeacher(splitStr[1], teacherFile);
                            for (String[] cls : cr) {
                                for (int i = 0; i < cls.length; i++) {
                                    System.out.print(cls[i]);
                                    if (i < cls.length - 1) {
                                        System.out.print(", ");
                                    }
                                }
                                System.out.println("");
                            }
                            continue;
                        }
                    }
                default:
                    System.out.println("Error - Invalid command");
            }
        }
    }
}

