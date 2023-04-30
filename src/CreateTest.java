import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateTest {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        FileWriter writer = new FileWriter("src/1.txt",false);
        int countRectangle = in.nextInt();
        writer.write(countRectangle+"\n");
        for (int i = 0; i < countRectangle ; i++) {
            writer.write(10 * i + " " + 10 * i + " " + 10 * (2 * countRectangle - i) + " " + 10 * (2 * countRectangle - i) + "\n");

        }
        writer.write(countRectangle+"\n");
        for (int i = 0; i < countRectangle; i++) {
            writer.write(i*10+" "+i*10+"\n");
        }
        writer.close();
    }
}
