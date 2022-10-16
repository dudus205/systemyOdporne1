import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SOBFileReader {

    public static void main(String[] args) {
        File file = new File(args[0]);

        if (validateFile(file)) {
            readFile(file);
        }
    }

    private static boolean validateFile(File file) {
        if (!file.exists()) {
            System.err.println("File does not exists!");
            return false;
        } else if (!file.canRead()) {
            System.err.println("Permissions to file are not allowed!");
            return false;
        }
        return true;
    }

    private static void readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            readLines(br);
            System.out.println("File has been read successfully!");
        } catch (IOException e) {
            System.err.println("File could not be read! Cause: ");
            e.printStackTrace();
        }
    }

    private static void readLines(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();

        while (line != null) {
            line = bufferedReader.readLine();
        }
    }
}