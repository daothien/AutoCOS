package tool.autocos.function;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteFile {
    static final String filePath = "files//acc.txt";
    public static void write(String email, String pass){
        try {
            File file = new File(filePath);
            FileWriter myWriter = new FileWriter(file,true);
            myWriter.write(email+"\n");
//            myWriter.write("\t");
//            myWriter.write(pass);
//            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static String readFile(){
        StringBuffer sb = new StringBuffer();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                sb.append(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return sb.toString();
    }
}
