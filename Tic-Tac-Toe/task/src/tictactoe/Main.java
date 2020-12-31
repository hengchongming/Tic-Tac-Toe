package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // initialize grid with user input
        char[] game = "_________".toCharArray();

        // print out grid
        printGrid(game);

        boolean isX = true;
        while (true) {
            while (true) {
                System.out.print("Enter the coordinates: ");
                String input = scanner.nextLine();
                int x;
                int y;

                try {
                    x = Integer.parseInt(input.substring(0, 1));
                    y = Integer.parseInt(input.substring(2, 3));
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                int coord = mapCoordinates(x, y);
                if (coord == -1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (game[coord] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (isX) game[coord] = 'X';
                    else game[coord] = 'O';
                    isX = !isX;
                    break;
                }
            }

            printGrid(game);

            String result = analyzeGame(game);

            switch (result) {
                case "x":
                    System.out.println("X wins");
                    break;
                case "o":
                    System.out.println("O wins");
                    break;
                case "d":
                    System.out.println("Draw");
                    break;
                default:
                    continue;
            }

            break;
        }
    }

    private static int mapCoordinates(int x, int y) {
        if (x == 1 && y ==1) return 0;
        if (x == 1 && y ==2) return 1;
        if (x == 1 && y ==3) return 2;
        if (x == 2 && y ==1) return 3;
        if (x == 2 && y ==2) return 4;
        if (x == 2 && y ==3) return 5;
        if (x == 3 && y ==1) return 6;
        if (x == 3 && y ==2) return 7;
        if (x == 3 && y ==3) return 8;

        return -1;
    }

    private static String analyzeGame(char[] game) {
        boolean xWins = isWon(game, 'X');
        boolean yWins = isWon(game, 'O');

        if (Math.abs(count(game, 'X') - count(game, 'O')) > 1 || (xWins && yWins)) {
            return "i";
        } else if (!xWins && !yWins && hasSpace(game)) {
            return "n";
        } else if (!xWins && !yWins && !hasSpace(game)) {
            return "d";
        } else if (xWins) {
            return "x";
        } else {
            return "o";
        }
    }

    private static boolean hasSpace(char[] game) {
        for (char c : game) {
            if (c == '_') return true;
        }
        return false;
    }

    private static int count(char[] game, char player) {
        int sum = 0;
        for (char c : game) {
            if (c == player) sum++;
        }

        return sum;
    }

    private static boolean isWon(char[] game, char c) {
        if (game[0] == game[1] && game[1] == game[2] && game[2] == c) {
            return true;
        }

        if (game[3] == game[4] && game[4] == game[5] && game[5] == c) {
            return true;
        }

        if (game[6] == game[7] && game[7] == game[8] && game[8] == c) {
            return true;
        }

        if (game[0] == game[3] && game[3] == game[6] && game[6] == c) {
            return true;
        }

        if (game[1] == game[4] && game[4] == game[7] && game[7] == c) {
            return true;
        }

        if (game[2] == game[5] && game[5] == game[8] && game[8] == c) {
            return true;
        }

        if (game[0] == game[4] && game[4] == game[8] && game[8] == c) {
            return true;
        }

        return game[2] == game[4] && game[4] == game[6] && game[6] == c;
    }

    private static void printGrid(char[] game) {
        System.out.println("---------");
        int index = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char c = game[index++];
                if (c == '_') c = ' ';
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
