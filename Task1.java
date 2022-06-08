import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        String[] array = new String[]{"Без", "труда", "не", "вытащишь", "рыбку", "из", "пруда"};
        String result = joiner(array);
        System.out.println(result);
    }

    public static String joiner(String[] words) {
        if (words.length > 1) {
            return words[0] + " " + joiner(Arrays.copyOfRange(words, 1, words.length));
        } else {
            return words[0];
        }
    }
}
