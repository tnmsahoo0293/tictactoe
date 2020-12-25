package com.company.tictactoe;

import java.util.Scanner;

public class tictactoeTwoplayer {
    public static void main(String[] args) {
        System.out.printf("Enter your sign to begin with, either O or X");
        Scanner scanner= new Scanner(System.in);
        char sign= scanner.nextLine().charAt(0);
        playGame(scanner, sign);
    }

    private static void playGame(Scanner scanner, char sign) {
        char[][] taoGameBoard = new char[3][3];
        char[] inputs= {'_','_','_','_','_','_','_','_','_'};
        String result= new String();
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                // Print border
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print("  "); // taoGameBoard[i][j] +
                if (j == 2) {
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
        int count=0;
         result =  promptMove(scanner, taoGameBoard, sign, count, inputs);
        System.out.println(result);
    }
    private static String promptMove(Scanner scanner, char[][] taoGameBoard, char sign, int count, char[] inputs) {
        boolean IsvalidInput = false;
        StringBuilder result = new StringBuilder();
       input: while (!IsvalidInput) {
            System.out.print("Enter coordinates:>");
            String coordinates = scanner.nextLine();
            String coord1 = coordinates.split(" ")[0];
            String coord2 = coordinates.split(" ")[1];

                // REGEX to check if coords numeric
                if (coord1.matches("-?\\d+?") && coord1.matches("-?\\d+?")) {
                    if (coord1.matches("^([1-3])$") && coord2.matches("^([1-3])$")) {
                        // Subtract 1 because of array indexes
                        int c1 = Integer.parseInt(coord1) - 1;
                        int c2 = Integer.parseInt(coord2) - 1;

                        if (taoGameBoard[c1][c2] == '_' || taoGameBoard[c1][c2] == ' ' || Character.isAlphabetic(taoGameBoard[c1][c2])) {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                        else if (count == 9) {
                            IsvalidInput = true;
                        }
                        else {
                            taoGameBoard[c1][c2] = sign;
                            inputs[c1 * 3 + c2] = sign;
                            count++;
                            if (sign == 'X')
                                sign = 'O';
                            else if (sign == 'O')
                                sign = 'X';
                            result.replace(0, result.length(), getGameResult(inputs));

                            if(result.toString().contains("X wins") ||result.toString().contains("Draw")||
                                    result.toString().contains("O wins"))
                                break input;
                            printGame(taoGameBoard);
                        }

                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
        }
        printGame(taoGameBoard);
        return result.toString();
    }
    private static String getGameResult(char[] taoGame) {
        String gameResult = "";
        int numberOfWins = 0;

        // row check
        for (int i = 0; i < taoGame.length; i += 3) {
            if ((taoGame[i] =='X' && taoGame[i+1]=='X' && taoGame[i+2]=='X')||
                    (taoGame[i] =='O' && taoGame[i+1]=='O' && taoGame[i+2]=='O')) {
                gameResult = taoGame[i] + " wins";
                numberOfWins++;
            }
        }

        // Col check
        for (int i = 0; i < 3; i++) {
            if ((taoGame[i] == 'X' && taoGame[i+3]=='X' && taoGame[i+6]=='X')||
                    (taoGame[i] == 'O' && taoGame[i+3]=='O' && taoGame[i+6]=='O')) {
                gameResult = taoGame[i] + " wins";
                numberOfWins++;
            }
        }

        // Diagonal check
        if(taoGame[0]=='X'|| taoGame[0]=='O') {
            if ((taoGame[0] == taoGame[4] && taoGame[0] == taoGame[8])
                    || (taoGame[2] == taoGame[4] && taoGame[2] == taoGame[6])) {
                gameResult = taoGame[4] + " wins";
                numberOfWins++;
            }
        }
        else{
            int numberOfX = 0;
            int numberOfO = 0;
            for (int i = 0; i < taoGame.length; i++) {
                if (taoGame[i] == 'X') {
                    numberOfX++;
                } else if (taoGame[i] == 'O') {
                    numberOfO++;
                } else {
                    gameResult = "Game not finished";
                }
            }
            if ((numberOfX + numberOfO) == 9) {
                gameResult = "Draw";
            }
        }

        if (gameResult.equals("")) {
            int numberOfX = 0;
            int numberOfO = 0;

            for (int i = 0; i < taoGame.length; i++) {
                if (taoGame[i] == 'X') {
                    numberOfX++;
                } else if (taoGame[i] == 'O') {
                    numberOfO++;
                } else {
                    gameResult = "Game not finished";
                }
            }

            if (gameResult.equals("")) {
                if ((numberOfX + numberOfO) == 9) {
                    gameResult = "Draw";
                }
            }
            else if (numberOfX - numberOfO > 1 || numberOfO - numberOfX > 1) {
                gameResult = "Impossible";
            }
        } else {
            if (numberOfWins > 1) {
                gameResult = "Impossible";
            }
        }

        return gameResult;
    }

    static void printGame(char[][]game){
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                // Print border
                if (j == 0) {
                    System.out.print("| ");
                }
                if( Character.isAlphabetic(game[i][j]))
                    System.out.print(game[i][j]+" ");
                // taoGameBoard[i][j] +
                else
                    System.out.print("  ");
                if (j == 2) {
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
    }

}
