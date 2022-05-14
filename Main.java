import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    // ”словимс¤, что currentPosition[0] - это координата змейки по вертикали,
    // а currentPosition[1] - это координата змейки по горизонтали
    static int[] currentPosition = new int[2];
    static int[] berryPosition = new int[]{0,1};
    static String[][] field;
    static boolean isBerryNeeded = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in
        ));

        field = buildField(10, 10);
        printField(field);
        String direction;
        System.out.println("введите 'start' дл¤ начала игры");
        while (!(direction = reader.readLine()).equalsIgnoreCase("exit")) {
            int cubeValue = rollCube();
            System.out.println("введите направление");
            direction = reader.readLine();
            while (cubeValue>0){
                move(1, direction);
                checkBerry();
                cubeValue--;
            }
            if (isBerryNeeded){
                createBerry();
            }
            printField(field);
            System.out.println("введите 'roll' дл¤ продолжени¤");
        }
    }

    private static void createBerry() {
        Random random = new Random();
        berryPosition[0] = random.nextInt(11);
        berryPosition[1] = random.nextInt(11);
        isBerryNeeded = false;
        field[berryPosition[0]][berryPosition[1]] = " x ";
    }

    private static void checkBerry() {
        if (berryPosition[0] == currentPosition[0] && berryPosition[1] == currentPosition[1]){
          isBerryNeeded = true;
        }
    }

    static void move(int cubeValue, String direction) {
        if (direction.equalsIgnoreCase("right")) {
            int oldVertical = currentPosition[0];
            int oldHorizontal = currentPosition[1];
            field[oldVertical][oldHorizontal] = " . ";
            currentPosition[1] = (currentPosition[1] + cubeValue)%10;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("down")) {
            int oldVertical = currentPosition[0];
            int oldHorizontal = currentPosition[1];
            field[oldVertical][oldHorizontal] = " . ";
            currentPosition[0] = (currentPosition[0] + cubeValue)%10;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("up")) {
            int oldVertical = currentPosition[0];
            int oldHorizontal = currentPosition[1];
            field[oldVertical][oldHorizontal] = " . ";
            currentPosition[0] = (currentPosition[0] +10 - cubeValue)%10;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("left")) {
            int oldVertical = currentPosition[0];
            int oldHorizontal = currentPosition[1];
            field[oldVertical][oldHorizontal] = " . ";
            currentPosition[1] = (currentPosition[1] +10 - cubeValue)%10;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }

    }

    static int rollCube() {
        Random random = new Random();
        int cubeValue = random.nextInt(7);
        System.out.println(cubeValue);
        return cubeValue;
    }

    //начальное состояние поля
    static String[][] buildField(int width, int height) {
        String[][] field = new String[height][width];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " . ";
            }
        }
        field[currentPosition[0]][currentPosition[1]] = " o ";
        field[berryPosition[0]][berryPosition[1]] = " x ";
        return field;
    }

    // печать поля после каждого шага
    static void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
