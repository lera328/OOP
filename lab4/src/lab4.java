import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class lab4 {
    private JFrame frame;
    private JLabel label;
    private String content = "Moving Text App";
    private int x = 10, y = 10;
    private int dx = 1, dy = 1;
    String[] fontsArray = {"Arial", "Verdana", "Times New Roman", "Calibri", "Tahoma",
            "Courier New", "Impact", "Georgia", "Trebuchet MS"};

    private Random random = new Random();
    private JComboBox<String> fontComboBox;

    public lab4() {
        frame = new JFrame("Moving Text App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Используем null layout для управления координатами
        label = new JLabel(content);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(label);
        fontComboBox = new JComboBox<>();
        for (String font : fontsArray) {
            fontComboBox.addItem(font);
        }
        fontComboBox.setBounds(250, 20, 120, 30);
        frame.add(fontComboBox);
        label.setBounds(x, y, 200, 20); // Устанавливаем начальные координаты и размеры JLabel
        frame.setVisible(true);
        startAnimation();
    }

    private void changeFont() {
        String selectedFontName = (String) fontComboBox.getSelectedItem();
        Font selectedFont = new Font(selectedFontName, Font.PLAIN, 14);
        label.setFont(selectedFont);
    }

    private void startAnimation() {
        Timer timer = new Timer(10, e -> {
            x += dx;
            y += dy;
            if (x < 0 || x > frame.getWidth() - 140) {//Цифры, если честно, на глаз, хотелось отнимать длину и ширину лейбла
                dx *= -1;                           //но получалась какая-то ерунда
                changeCaseAndFont();
            }
            if (y < 0 || y > frame.getHeight() - 60) {
                dy *= -1;
                changeCaseAndFont();
            }
            label.setLocation(x, y);
        });
        timer.start();
    }

    private void changeCaseAndFont() {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (random.nextBoolean()) {
                    chars[i] = Character.toUpperCase(chars[i]);
                } else {
                    chars[i] = Character.toLowerCase(chars[i]);
                }
            }
        }
        content = String.valueOf(chars);
        label.setText(content);
        changeFont();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(lab4::new);
    }
}