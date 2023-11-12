import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пример многочлена 5a+7ab-c^2+ab^3c");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первый многочлен:");
        String firstInput = scanner.nextLine();
        System.out.println("Введите второй многочлен:");
        String secondInput = scanner.nextLine();

       System.out.println(getResultWithSuits(result(firstInput,secondInput), firstInput, secondInput));

}

    public static List<String> result(String a1, String a2){
        List<String> splitA1=splitString(a1);
        List<String> splitA2=splitString(a2);
        List<String> result=new ArrayList<>();
        String composition="";
        for(int i=0;i<splitA1.size();i++){
            String member=splitA1.get(i);
            double digit=getDigit(member);
            String letter=letterSplit(getLetter(member));
            String member_="";
            double digit_=0;
            String letter_="";
            for(int j=0;j<splitA2.size();j++){
                member_=splitA2.get(j);
                digit_=getDigit(member_);
                letter_=letterSplit(getLetter(member_));
                if(digit*digit_%1==0) composition=String.valueOf((int)(digit*digit_));
                else composition=String.valueOf((digit*digit_));
                result.add(composition+LetterGluing(letter+letter_));
            }
        }
        return result;
    }

    public static String getResultWithSuits(List<String> res,String s1, String s2){
        int k=0;
        String result="";
        if (s1.charAt(0)!='-')s1="+"+s1;
        if (s2.charAt(0)!='-')s2="+"+s2;
        for (int i = 0; i < s1.length(); i++) {
            char curChar = s1.charAt(i);
            if (curChar == '+' || curChar == '-')
            {
                for (int j=0;j<s2.length();j++){
                    char curChar2=s2.charAt(j);
                    if (curChar2=='+' || curChar2=='-'){
                        if(curChar+curChar2==88) result+="-"+res.get(k);
                        else result+="+"+res.get(k);
                        k++;

                    }
                }
            }
        }
        return result;

    }
    public static List<String> splitString(String input) {
        List<Integer> signs=new ArrayList<>();
        List<String> result = new ArrayList<>();
        input = input.replaceAll("\\s+", "");
        String substring = "";
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '+' || currentChar == '-') {
                signs.add(Integer.valueOf(currentChar+"1"));
                result.add(substring);
                substring=""; // очистить строку
            } else {
                substring+=currentChar;
            }
        }
        result.add(substring); // добавить последнюю подстроку
        return result;
     }

        private static double getDigit(String input) {
        String res="";
        input = input.replace(",",".");
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c) || c=='.') {
                res+=c;
            }
            else break;
        }
        if(res=="")res="1";
        return Double.parseDouble(res);
    }

    private static String getLetter(String input){
        double digit = getDigit(input);
        String dig="";
        if(digit%1==0) dig=String.valueOf((int)digit);
        else dig=String.valueOf(digit);
        input=input.replace(dig,"");
        return input;
    }
    public static String letterSplit(String a){
        String sub="";
        String res="";
        String letter="";
        for (char p:a.toCharArray()) {
            if (Character.isLetter(p)) {res+=p; letter=String.valueOf(p); }
            else if(Character.isDigit(p)) {
                for (int i=0;i<Integer.parseInt(String.valueOf(p))-1;i++) res+=letter;

            }
        }
        return res;
    }

    public static String LetterGluing(String a){
        char[] chars = a.toCharArray();
        Arrays.sort(chars);
        a = new String(chars);

        StringBuilder res = new StringBuilder();
        int k = 1;
        char prevItem = a.charAt(0);

        for (int i = 1; i < a.length(); i++) {
            char currentItem = a.charAt(i);

            if (currentItem == prevItem) {
                k++;
            } else {
                if (k == 1) {
                    res.append(prevItem);
                } else {
                    res.append(prevItem).append("^").append(k);
                    k = 1;
                }
                prevItem = currentItem;
            }
        }
        if (k == 1) {
            res.append(prevItem);
        } else {
            res.append(prevItem).append("^").append(k);
        }
        return res.toString();

    }
}