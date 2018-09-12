package com.sl1_2.lab4;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Sep. 10, 2018
*/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class lab4 {

    public static void sumToN(Scanner input) {
        int number = 0;
        int sum = 0;
        boolean unfinished = true;

        while (true) {
            while (true) {
                try {
                    System.out.println("Please input a positive integer (natural number) to find the sum of the numbers preceding it. Input -1 to quit.");
                    number = input.nextInt();
                    if (number <= 0 && number != -1) {
                        System.out.println("That isn't a valid input. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Something wrong happened. Please try again.");
                    input.next();
                }
            }

            if (number == -1) {
                break;
            }

            sum = 0;

            while (number > 0) {
                if (number > 1) {
                    System.out.print(number + " + ");
                } else {
                    System.out.print(number + " = ");
                }
                sum += number;
                number -= 1;
            }

            System.out.print(sum + "\n");
        }
    }

    public static void gradingProgram(Scanner input) {
        double total = 0;
        double currentScore = 0;
        double sum = 0;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        ArrayList<Double> scores = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#####.###");

        while (true) {
            try {
                System.out.println("Please input the total number of points in the test.");
                total = input.nextDouble();
                if (total <= 0) {
                    System.out.println("Input a number greater than 0. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                input.next();
            }
        }

        while (true) {
            while (true) {
                try {
                    System.out.println("Please input a score. Input -1 to stop.");
                    currentScore = input.nextDouble();
                    if (currentScore <= 0 && currentScore != -1) {
                        System.out.println("Input a number greater than 0. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Something went wrong. Please try again.");
                    input.next();
                }
            }

            if (currentScore == -1) {
                break;
            }
            scores.add(currentScore);
        }

        sum = 0;

        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }

        System.out.println("\nThe average score of the class was " + df.format(sum / scores.size()) + " points");
        System.out.println("The average percentage of the class was " + df.format(sum / scores.size() / total * 100) + "% and the grade is ");

        if ((sum / scores.size() / total * 100) >= 90) {
            System.out.print("an A.\n");
        } else if ((sum / scores.size() / total * 100) >= 80 && (sum / scores.size() / total * 100) < 90) {
            System.out.print("a B.\n");
        } else if ((sum / scores.size() / total * 100) >= 70 && (sum / scores.size() / total * 100) < 80) {
            System.out.print("a C.\n");
        } else if ((sum / scores.size() / total * 100) >= 80 && (sum / scores.size() / total * 100) < 90) {
            System.out.print("a D.\n");
        } else {
            System.out.print("an F.\n");
        }

        for (double score : scores) {
            double grade = (score / total * 100);
            if (grade >= 90) {
                countA++;
            } else if (grade < 90 && grade >= 80) {
                countB++;
            } else if (grade < 80 && grade >= 70) {
                countC++;
            } else if (grade < 70 && grade >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        System.out.println("Amount per Grade:");
        System.out.println("A: " + countA + "\nB: " + countB + "\nC: " + countC + "\nD: " + countD + "\nF: " + countF);
    }

    public static void reciprocalAdder(Scanner input) {
        double sum = 0;
        double currentNumber = 0;
        int i = 0;
        int exitConfirm = 0;

        //Stuff for the challenge
        double multiple = 1;
        double numerator = 0;
        ArrayList<Double> numbers = new ArrayList<>();

        while (i < 10) {
            try {
                System.out.println("Current sum: " + sum);
                System.out.println("Current number: " + (i + 1));
                System.out.println("Please input a number. Its reciprocal will be added to the running sum.");
                System.out.println("Input 0 to automatically stop. Otherwise, it will ask for " + (9 - i) + " more numbers.");
                currentNumber = input.nextDouble();
                if (currentNumber == 0) {
                    while (true) {
                        try {
                            System.out.println("Are you sure you want to exit?\n1. Yes\n2. No");
                            exitConfirm = input.nextInt();
                            if (exitConfirm == 1) {
                                break;
                            } else if (exitConfirm == 2) {
                                break;
                            } else {
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("That isn't a valid input.");
                            input.next();
                        }
                    }
                    if (exitConfirm == 1) {
                        break;
                    } else {
                        continue;
                    }
                } else {
                    numbers.add(currentNumber);
                    sum += 1 / currentNumber;
                    multiple *= currentNumber;
                    i++;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                input.next();
            }
        }

        for (int j = 0; j < 10; j++) {
            numerator += multiple / numbers.get(j);
        }

        System.out.println("Final Sum: " + sum);
        System.out.println("Final Sum (Fraction)" + numerator + " / " + multiple);
    }

    public static void lcm_gcd(Scanner input) {
        int num1 = 0;
        int num2 = 0;

        int lcm = 0;
        int gcd = 0;

        while (true) {
            try {
                System.out.println("Please input the first number.");
                num1 = Math.abs(input.nextInt());
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                input.next();
            }
        }

        while (true) {
            try {
                System.out.println("Please input the second number.");
                num2 = Math.abs(input.nextInt());
                break;
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                input.next();
            }
        }

        if (num1 != num2) {
            lcm = (num1 > num2) ? num2 : num1;
            while (true) {
                if (lcm % num1 == 0 && lcm % num2 == 0) {
                    break;
                }
                lcm++;
            }
            gcd = (num1 * num2) / lcm;
        } else {
            lcm = num1;
            gcd = num1;
        }

        System.out.println("The least common multiple is " + lcm);
        System.out.println("The greatest common denominator is " + gcd);

    }

    public static void main(String args[]) {
        int option = 0;

        Scanner input = new Scanner(System.in);

        while (true) {

            boolean loopBool = true;
            System.out.println("\nOptions:");
            System.out.println("1. Sum of preceding numbers of number");
            System.out.println("2. Grading Program");
            System.out.println("3. Reciprocal Adder");
            System.out.println("4. Least Common Multiple / Greatest Common Divisor");
            System.out.println("5. Quit");
            System.out.println("Please input option number:");

            while (loopBool) {
                if (input.hasNextInt()) {
                    loopBool = false;
                }

                option = input.nextInt();

                if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
                    System.out.println("Please try again. That isn't an option.");
                }
            }

            if (option == 1) {
                sumToN(input);
            } else if (option == 2) {
                gradingProgram(input);
            } else if (option == 3) {
                reciprocalAdder(input);
            } else if (option == 4) {
                lcm_gcd(input);
            } else {
                System.out.println("Bye!");
                break;
            }
        }
    }
}
