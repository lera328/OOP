import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        String vowels = "ауеёиоуыэюя";
        String consonant = "бвгджзйклмнрстфхцчшщ";
        File file = new File("src\\text.txt");
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int line;
            int kV = 0;
            int kC = 0;
            int k = 1;
            String ch;
            while ((line = br.read()) != -1) {
                ch = String.valueOf((char) line).toLowerCase();
                if (ch.contains(".") || ch.contains("!") || ch.contains("?")) {
                    text += "Предложение №" + k + " количество гласных - " + kV + " количество соглаcных - " + kC;
                    if (kV > kC) text += ". Гласных больше\n";
                    if (kC > kV) text += ". Соласных больше\n";
                    else text += ". Число гласных равно числу согласны\n";

                    k++;
                } else {
                    if (vowels.contains(ch)) kV += 1;
                    if (consonant.contains(ch)) kC += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }


}
