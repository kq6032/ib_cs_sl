package com.sl1_2.lab5;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Sep. 24, 2018
*/

import java.util.*;

public class lab5b {

    public static void guessingGame(Scanner in) {

        Random rand = new Random();
        int randomNum = rand.nextInt(1000);
        int guessCount = 0;
        int guess = 0;

        while (true) {
            System.out.printf("Guess a number between 0 and 1000 (not including 1000)!\nYour current number of guesses is %d\n", guessCount);
            try {
                guess = in.nextInt();
                if (guess < 0 || guess >= 1000) {
                    System.out.println("That isn't a valid input. Please try again.");
                    continue;
                }
                guessCount++;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }

            if (guess == randomNum) {
                System.out.println("That's the right number!");
                break;
            } else if (guess > randomNum) {
                System.out.println("Your guess is too high!");
            } else if (guess < randomNum) {
                System.out.println("Your guess is too low!");
            }
        }

        System.out.printf("\nYour total number of guesses is %d\n", guessCount);
    }

    public static void guessingGameChallenge(Scanner in) {

        Random rand = new Random();
        String randomNum = Integer.toString(rand.nextInt(1000));
        String guessString;
        int guessCount = 0;
        int guess = 0;
        int digitsCorrect = 0;

        while (randomNum.length() < 3) { randomNum = "0" + randomNum; }

        while (true) {
            System.out.printf("\nGuess a number between 0 and 1000 (not including 1000)!\nYour current number of guesses is %d\n", guessCount);
            try {
                guess = in.nextInt();
                if (guess < 0 || guess >= 1000) {
                    System.out.println("That isn't a valid input. Please try again.");
                    continue;
                }
                guessCount++;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }

            guessString = Integer.toString(guess);
            while (guessString.length() < 3) { guessString = "0" + guessString; }

            if (Objects.equals(guessString, randomNum)) {
                System.out.println("That's the right number!");
                break;
            }
            digitsCorrect = 0;
            if (guessString.charAt(0) == randomNum.charAt(0)) {
                digitsCorrect++;
            } if (guessString.charAt(1) == randomNum.charAt(1)) {
                digitsCorrect++;
            } if (guessString.charAt(2) == randomNum.charAt(2)) {
                digitsCorrect++;
            }

            System.out.printf("\nYou had %d digits correct in your guess.", digitsCorrect);
        }
        System.out.printf("\nYour total number of guesses is %d\n", guessCount);
    }

    public static void linedStairsGen(Scanner in) {

        int inputNum = 0;
        int count = 1;

        while (true) {
            System.out.println("Please input a positive integer.");
            try {
                inputNum = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }

        int lineNum = inputNum;
        while (lineNum > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(lineNum + "    ");
            }
            count++;
            lineNum--;
            System.out.println();
        }
    }

    public static void drawRect(Scanner in) {

        int width = 0;
        int height = 0;

        System.out.println("Welcome to the rectangle drawer!\n");

        while (true) {
            System.out.println("Please input the width of the rectangle");
            try {
                width = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }

        while (true) {
            System.out.println("Please input the height of the rectangle.");
            try {
                height = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }

        for (int heightLp = 1; heightLp <= height; heightLp++) {
            for (int widthLp = 1; widthLp <= width; widthLp++) {
                if (heightLp == 1 || heightLp == height || widthLp == 1 || widthLp == width)  {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void factorialTable(Scanner in) {

        int startNum = 0;

        System.out.println("Welcome to the factorial table generator!");

        while (true) {
            System.out.println("Please input a positive integer.");
            try {
                startNum = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }

        int factorial;
        System.out.println("0! = 1");
        for (int i = 1; i <= startNum; i++) {
            factorial = 1;
            System.out.printf("%d! = ", i);
            for (int j = i; j >= 1; j--) {
                if (j == 1) {
                    break;
                } else {
                    System.out.printf("%d x ", j);
                }
                factorial *= j;
            }
            System.out.printf("1 = %d\n", factorial);
        }
    }

    public static void primeGen(Scanner in) {

        int genNum = 0;
        int currentNum = 3;
        int counter = 0;
        boolean isPrime;
        ArrayList<Integer> primes = new ArrayList<>();

        System.out.println("Welcome to the prime number generator.\n ");
        while (true) {
            System.out.println("Please input the number of primes you want to generate.");
            try {
                genNum = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }

        System.out.print("2 ");
        primes.add(2);
        while (primes.size() < genNum) {

            isPrime = false;
            for (int j = 0; j < primes.size(); j++) {
                if (currentNum % primes.get(j) == 0) {
                    isPrime = false;
                    break;
                } else {
                    isPrime = true;
                }
            }

            if (isPrime) {
                System.out.printf("%d ", currentNum);
                primes.add(currentNum);
                counter++;
                if (counter == 10) {
                    counter = 0;
                    System.out.println();
                }
            }
            currentNum += 2;
        }
        System.out.println();
    }

    public static void main(String args[]) {

        int option = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            while (true) {
                System.out.println("Please input an option:");
                System.out.println("1. Number Guessing Game");
                System.out.println("2. Number Guessing Game (Challenge)");
                System.out.println("3. Line Stairs Generator");
                System.out.println("4. Draw a Rectangle");
                System.out.println("5. Factorial Table Generator");
                System.out.println("6. Prime Number Generator");
                System.out.println("7. Exit");

                try {
                    option = in.nextInt();
                    if (option < 1 || option > 7) {
                        System.out.println("That isn't a valid option. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("That isn't a valid option. Please try again.");
                    in.next();
                    continue;
                }
            }

            if (option == 1) {
                guessingGame(in);
            } else if (option == 2) {
                guessingGameChallenge(in);
            } else if (option == 3) {
                linedStairsGen(in);
            } else if (option == 4) {
                drawRect(in);
            } else if (option == 5) {
                factorialTable(in);
            } else if (option == 6) {
                primeGen(in);
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
