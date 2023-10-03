import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
            System.out.println("Введите строку для проверки");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            String pattern = "^[1-9]\\d{5}[,|.]\\d{6}$";
            System.out.println(Pattern.matches(pattern, text)?"Строка является шестизначным числом":"Строка не является шестизначным числом");
    }
}