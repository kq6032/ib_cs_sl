package com.sl1_2.challenges;

// beat 1351 attempts

public class Challenge1 {
    static int attemptNum =  0;
    static final int[] NUMBER_LIST = new int[]{1, 2, 3, 4, 5, 6, 7, 8 , 9, 10};

    public static boolean isDivisible(int num, int place) {
        if (num == -1) {
            return false;
        } else if ((num % NUMBER_LIST[place]) != 0) {
            return false;
        }

        attemptNum++;
        return true;
    }

    public static boolean solveBacktrack(int[] set, int[] usedNums, int place) {
        if (completed(set, usedNums)) {
            return true;
        }

        String currentNum = "";

        for (int i = 0; i < set.length; i++) {
            if (place != 9 && i == 0) {
                continue;
            }

            if (place != 4 && i == 5) {
                continue;
            }

            if (usedNums[i] == -1) {
                continue;
            }

            currentNum = "";

            if (place != 0) {
                currentNum = "";
                for (int k = 0; k < place; k++) {
                    currentNum += Integer.toString(set[k]);
                }
            }
            currentNum += Integer.toString(i);

            try {
                if (isDivisible(Integer.parseInt(currentNum), place)) {
                    set[place] = i;
                    usedNums[i] = -1;

                    if (solveBacktrack(set, usedNums, place + 1)) {
                        return true;
                    }

                    set[place] = -1;
                    usedNums[i] = i;
                }
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    public static boolean completed(int[] set, int[] usedNums) {
        int divIndex = 0;

        for (int i : set) {
            if (isDivisible(i, divIndex));
            else {
                return false;
            }
            divIndex++;
        }

        for (int j : usedNums) {
            if (j != -1) {
                return false;
            }
        }
        return true;
    }

    public static void solve() {
        int[] workingSet = new int[]{-1, -1, -1, -1, 5, -1, -1, -1, -1, 0};
        int[] usedNums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        if (solveBacktrack(workingSet, usedNums, 0)) {
            for (int i : workingSet) {
                System.out.print(i);
            }
        }
    }

    public static void main(String[] args) {
        solve();
        System.out.printf("\nTries: %d", attemptNum);
    }
}
