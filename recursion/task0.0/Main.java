import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<Integer>();
        BufferedReader br = null;
        long sum = 0;
        try{
            br = new BufferedReader(new FileReader("input.txt"));
            String line;
            while((line = br.readLine()) != null)
            {
                numbers.add(Integer.valueOf(line));
            }
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(file);
            for (int el : numbers)
            {
                sum+=el;
            }
            pw.println(sum);
            pw.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
        finally {
            try{
                br.close();
            }
            catch (IOException e){
                System.out.println("Error: " + e);
            }
        }
    }
}