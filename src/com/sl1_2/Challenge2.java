package com.sl1_2;

import java.util.Random;

/**
 * Quicksort and Heapsort Challenge
 * Find the average execution time using several iterations of sorting
 */
public class Challenge2 {

    // Size of array to sort
    public static final int ARRAY_SIZE = 1000000;

    /**
     * Partitioning method for quicksort
     * Utilizes random pivot index to compare the elements
     * @param array Array of numbers to be sorted
     * @param low Lower bound for sub-array
     * @param high Upper bound for sub-array
     * @param rm Random object for pivot
     * @return The pivot index
     */
    public static int partition(int[] array, int low, int high, Random rm) {
        int pivot = array[rm.nextInt(high - low) + low]; // Accessing array for pivot value
        int i = low - 1;
        int j = high + 1;
        int temp;

        // Swapping the elements of the array
        while (true) {
            // Finding the first element in the array with greater value than the pivot
            do {
                i++;
            } while (array[i] < pivot);

            // Finding the first element in the array from the back with lesser value than pivot
            do {
                j--;
            } while (array[j] > pivot);

            // Returning index to split the original array into further sub-arrays
            if (i >= j) {
                return j;
            }

            // Swapping the elements in array[i] and array[j]
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Recursive quicksort method
     * @param array Array of numbers to sort
     * @param low Lower bound for sub-array in recursive calls
     * @param high Upper bound for sub-array in recursive calls
     * @param rm Random object for partitioning method pivot
     */
    public static void quicksort(int[] array, int low, int high, Random rm) {
        int splitIndex;

        // Continues sorting while lower and upper indices are different
        if (low < high) {
            splitIndex = partition(array, low, high, rm);
            quicksort(array, low, splitIndex, rm);
            quicksort(array, splitIndex + 1, high, rm);
        }
    }

    public static void heapsort(int[] array) {

    }

    public static void buildMaxHeap (int[] array) {

    }

    /**
     * Main method of the program
     * @param args
     */
    public static void main(String[] args) {
        Random rm = new Random();

        int[] numbers = new int[ARRAY_SIZE];
        double startTime, endTime;
        double sum = 0;
        int runs = 20;

        for (int i = 0; i < runs; i++) {
            for (int j = 0; j < numbers.length; j++) {
                numbers[i] = rm.nextInt();
            }

            startTime = System.nanoTime();
            quicksort(numbers, 0, numbers.length - 1, rm);
            endTime = System.nanoTime();

            sum += (endTime - startTime) / 1000000000;
        }

        System.out.printf("Array size: %d\nAverage runtime after %d runs: %f seconds", ARRAY_SIZE, runs, sum / 20);
    }
}
