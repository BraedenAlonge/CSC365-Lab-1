import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Database {
    public static ArrayList<String[]> getGradeClassroomTeacher(String lastname) {
        File datafile = new File("students.txt");
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(datafile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                if (splitData[0].equalsIgnoreCase(lastname)) {
                    String[] current = new String[3];
                    current[0] = splitData[2];
                    current[1] = splitData[3];
                    String teacherName = splitData[6] + " " + splitData[7];
                    current[2] = teacherName;
                    result.add(current);
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
                    break;
                // Info command
                case "I":
                case "Info":
                    for (int i = 0; i < 7; i++) {
                        System.out.println("Students in grade" + i + ": " + totalNumberOfStudentsInGrade(i));
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
                        ArrayList<String[]> result = getGradeClassroomTeacher(splitStr[1]);
                        assert result != null;
                        for (String[] student : result) {
                            System.out.println("Student last name: " + splitStr[1]);
                            System.out.println("Student grade: " + student[0]);
                            System.out.println("Student classroom: " + student[1]);
                            System.out.println("Student teacher: " + student[2]);
                            System.out.println();
                        }
                    }
                    //If Bus Number is included
                    else if (splitStr.length == 3) {
                        int busNo = Integer.parseInt(splitStr[2]);
                        System.out.println("Bussy");    // Braeden what is this lmao
                        if (busNo == 0) {
                            System.out.println("Invalid Bus Number.");
                        }
                    } else { //Call Function with bus number included
                        System.out.println("Error - invalid number of arguments");
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
                    continue;
                default:
                    System.out.println("Error - Invalid command");
            }
        }
    }
}

