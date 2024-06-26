package kz.project;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static char[][] map;
    static final int SIZE = 3;
    static final char DOT_PLAYER = 'X';
    static final char DOT_AI = '0';
    static final char DOT_EMPTY = '*';

    static final Scanner sc = new Scanner(System.in);

    static final Random random = new Random();

    public static void main(String[] args) {
        prepareMap();
        showMap();
        while (true) {
            playerTurn();
            showMap();

            if(isWinner()) {
                System.out.println("Победил игрок");
                break;
            }

            if(isDraw()) {
                System.out.println("Ничья");
                break;
            }

            botTurn();
            showMap();
            if(isWinner()) {
                System.out.println("Победил БОТ");
                break;
            }

            if(isDraw()) {
                System.out.println("Ничья");
                break;
            }

        }


    }

    public static boolean isWinner() {
        for (int i = 0; i < SIZE; i++) {
            if ((map[i][0] != DOT_EMPTY && map[i][1] != DOT_EMPTY && map[i][2] != DOT_EMPTY) ||
                    (map[0][i] != DOT_EMPTY && map[1][i] != DOT_EMPTY && map[2][i] != DOT_EMPTY)) {
                return true;
            }
        }
        return (map[0][0] != DOT_EMPTY && map[1][1] != DOT_EMPTY && map[2][2] != DOT_EMPTY) ||
                (map[0][2] != DOT_EMPTY && map[1][1] != DOT_EMPTY && map[2][0] != DOT_EMPTY);
    }


    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void botTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_AI;
        System.out.printf("Ход ИИ: [%d, %d]\n", (x + 1), (y + 1));
    }

    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_PLAYER;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void showMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i+" ");
        }
        System.out.println(" ");

        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void prepareMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}
