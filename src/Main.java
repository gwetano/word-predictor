import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        //System.out.println(model);

        Scanner input = new Scanner(System.in);
        System.out.println("Scrivi qua:");
        String word = input.nextLine();

        //prendo solo l'ultima parola inserita
        StringTokenizer token = new StringTokenizer(word);
        while (token.hasMoreElements()) {
            word = token.nextToken();
        }
        System.out.println(model.generate(word));
    }
}