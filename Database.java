import java.io.File;
import java.io.FileNotFoundException;
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

    public String readFromDatabase(Command c) {
        try {
            Scanner teacherScanner = new Scanner(teachersFile);
            Scanner listScanner = new Scanner(listFile);

    } catch (FileNotFoundException e) {
        System.out.println("Error opening file");
        System.exit(0);
        }
        return null;
    }
}
