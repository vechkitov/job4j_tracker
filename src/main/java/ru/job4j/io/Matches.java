package ru.job4j.io;

import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            int limit = Math.min(3, count);
            System.out.print(player + " введите число от 1 до " + limit + ": ");
            int matches = Integer.parseInt(input.nextLine());
            if (matches < 1 || matches > limit) {
                System.out.println("Ошибка: число " + matches
                        + " находится за пределами допустимого диапазона");
                continue;
            }
            count -= matches;
            System.out.println("Оставшееся количество спичек: " + count);
            turn = !turn;
        }
        String rsl = turn ? "Выиграл второй игрок" : "Выиграл первый игрок";
        System.out.println(rsl);
    }
}
