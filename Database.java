import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    // TODO : Refactor db functions from schoolsearch to here; DRY (Don't repeat yourself!)

    private File teachersFile;
    private File listFile;

    public Database() {
        this.teachersFile = new File("teachers.txt");
        this.listFile = new File("list.txt");
    }

    public enum Command {
        Student,
        StudentBus,
        Bus,
        Teacher,
        Average,
        Grade,
        Info
    }

    public String readFromDatabase(Command c, ArrayList<Object> args) {
        try {
            Scanner teacherScanner = new Scanner(teachersFile);
            Scanner listScanner = new Scanner(listFile);
            switch(c) {
                case Student: {
                    String lastname = (String) args.get(0);
                    return StudentCmd(listScanner, lastname);
                }
                case StudentBus: {
                    String lastname = (String) args.get(0);
                    return StudentBusCmd(listScanner, lastname);
                }
                case Bus: {
                    String busNum = (String) args.get(0);
                    return BusCmd(listScanner, busNum);
                }
                case Teacher: {
                    String teacher = (String) args.get(0);
                    return TeacherCmd(teacherScanner, teacher);
                }
                case Average: {
                    ;
                }
                case Grade: {
                    ; }
                case Info: {
                    ; }
            }
    } catch (FileNotFoundException e) {
        System.out.println("Error opening file");
        System.exit(0);
        }
        return null;
    }

    public String StudentCmd(Scanner listScanner, String lastname) {
        return null;
    }

    public String StudentBusCmd(Scanner listScanner, String lastname) {
        return null;
    }

    public String BusCmd(Scanner listScanner, String busNum) {
        return null;
    }

    public String TeacherCmd(Scanner teacherScanner, String teacher) {
        return null;
    }

    public String Average(Scanner listScanner, String grade) {
        return null;
    }

    public String Grade(Scanner listScanner, String grade, int option) {
        return null;
    }
}
