package com.sl1_2.lab7;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Feb. 25, 2018
*/

import java.io.*;
import java.util.Scanner;

public class Lab7b {
    private static Scanner in = new Scanner(System.in);
    private static final int STUDENT_ARRAY_SIZE = 128;

    /**
     * Get integer input from user
     * @param message send message
     * @return the integer inputted by the user
     */
    private static int getIntInput(String message) {
        int input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            while (!in.hasNextInt()) {
                System.out.println("Invalid input.");
                in.next();
            }

            input = in.nextInt();
            run = false;
        }

        return input;
    }
    
    /**
     * Reads in from the "classlist.txt" file to get the students
     * @param studentList empty list of students to fill
     */
    private static void readFile(Student[] studentList) {
        // Data read in from the file
        int data;
        String dataString = new String();
        byte[] inputBuffer = new byte[16384];
        int curIndex = 0;

        // Array of strings separated by
        String[] splitStrings;

        // Student information
        int id, grade;
        String lastName, firstName, gender;

        int studentCount = 0;

        // Reading in from the file
        try (DataInputStream input = new DataInputStream(new FileInputStream(
                "C:\\Users\\kevin\\code\\java\\ib_cs_sl\\src\\com\\sl1_2\\lab7\\classlist.txt"))) {
            data = input.read(inputBuffer);

            // Checking if the input file is valid
            if (data != -1) {
                for (int i = 0; i < data; i++) {
                    dataString += (char) inputBuffer[i];
                }
            }

            // Splitting the string read in into separate parts by any whitespace
            splitStrings = dataString.split("[,\\s]+");

            while (curIndex < splitStrings.length) {
                id = Integer.parseInt(splitStrings[curIndex]);
                grade = Integer.parseInt(splitStrings[curIndex + 1]);
                lastName = splitStrings[curIndex + 2];
                firstName = splitStrings[curIndex + 3];
                gender = splitStrings[curIndex + 4];

                studentList[studentCount] = new Student(id, grade, lastName, firstName, gender);

                studentCount++;
                curIndex += 5;
            }
        } catch (IOException e) {
            System.out.printf("Read error: %s\n", e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Prints out the student information
     * @param student student to print info
     */
    private static void printStudentInfo(Student student) {
        if (student != null) {
            System.out.printf("\n%s %s (%d):\n\t- Grade: %s\n\t- Gender: %s",
                    student.getFirstName(), student.getLastName(),
                    student.getId(), student.getGrade(), student.getGender());
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Print specific student from an array of students found by id or name
     * @param studentList list of students to search
     */
    private static Student getStudent(Student[] studentList, String message) {
        System.out.println(message);

        // Clearing scanner buffer
        in.nextLine();
        String input = in.nextLine();

        Student student;

        try { // input is an ID
            int id = Integer.parseInt(input);

            student = findStudent(id, studentList);
        } catch (Exception e){ // input is a name
            System.out.println("Input first name of student");
            String firstName = in.nextLine();
            student = findStudent(input, firstName, studentList);
        }

        return student;
    }

    /**
     * Finds a student in an array using student id
     * @param id ID used to find student
     * @param studentList list of students to search
     * @return returns a student if found, null if no student
     */
    private static Student findStudent(int id, Student[] studentList) {
        Student student;

        for (int i = 0; i < studentList.length; i++) {
            student = studentList[i];

            if (student != null && (student.getId() == id)) {
                return student;
            }
        }

        return null;
    }

    /**
     * Finds a student in an array using name
     * @param lastName last name used to find student
     * @param firstName first name used to find student
     * @param studentList the number of students in the array (filled elements)
     * @return returns a student if found, null if no student
     */
    private static Student findStudent(String lastName, String firstName, Student[] studentList) {
        Student student;

        for (int i = 0; i < studentList.length; i++) {
            student = studentList[i];

            if (student != null && (student.getFirstName().equals(firstName))
                && (student.getLastName().equals(lastName))) {
                return student;
            }
        }

        return null;
    }

    /**
     * Removes a student from the array of students
     * @param students Array of students
     */
    private static void removeStudent(Student[] students) {
        Student student = getStudent(students, "Input ID or last name of student to remove.");

        if (student != null) {
            for (int i = 0; i < students.length; i++) {
                if (student == students[i]) {
                    students[i] = null;
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Add a student to the student list
     * @param students array of students to add to
     * @return returning array in case new array is made to increase size
     */
    private static Student[] addStudent(Student[] students) {
        int i = 0;
        boolean isFound = false;

        while (!isFound && i < students.length) {
            i++;

            if (students[i] == null) {
                isFound = true;
                break;
            }
        }

        // Clear scanner buffer
        in.nextLine();
        System.out.println("Input student last name");
        String lastName = in.nextLine();

        System.out.println("Input student first name");
        String firstName = in.nextLine();

        int id = getIntInput("Input student id");

        int grade = getIntInput("Input student grade");

        // Clear scanner buffer
        in.nextLine();
        System.out.println("Input student gender");
        String gender = in.nextLine();

        if (!isFound) {
            Student[] newStudents = new Student[students.length * 2];

            System.arraycopy(students, 0, newStudents, 0, i);

            newStudents[i + 1] = new Student(id, grade, lastName, firstName, gender);

            return newStudents;
        } else {
            students[i] = new Student(id, grade, lastName, firstName, gender);

            return students;
        }
    }

    /**
     * Rearranges students to remove empty spaces in list of students and move all null to end
     * @param students array of students
     */
    private static void rearrageStudents(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            if ((students[i] == null) && (students[i + 1] != null)) {
                students[i] = students[i + 1];
                students[i + 1] = null;
            }
        }
    }

    /**
     * Sort students in the array by name
     * @param students array of students
     */
    private static void sortStudentsName(Student[] students) {
        rearrageStudents(students);

        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if ((students[j] != null) && (students[j + 1] != null)) {
                    if (students[j].getLastName().compareTo(students[j + 1].getLastName()) > 0)  {
                        Student tmp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = tmp;
                    } else if ((students[j].getLastName().equals(students[j + 1].getLastName()))
                               && (students[j].getLastName().compareTo(students[j + 1].getLastName()) > 0)) {
                        Student tmp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = tmp;
                    }
                }
            }
        }
    }

    /**
     * Sort students in the array by id
     * @param students array of students
     */
    private static void sortStudentsID(Student[] students) {
        rearrageStudents(students);

        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if ((students[j] != null) && (students[j + 1] != null)) {
                    if (students[j].getId() > students[j + 1].getId()) {
                        Student tmp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = tmp;
                    }
                }
            }
        }
    }

    /**
     * Serialize students and write to a file
     * @param students array of students to serialize
     */
    private static void serializeStudents(Student[] students) {
        in.nextLine();
        System.out.println("Input the name of file to output to");
        String fileName = in.nextLine();

        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Writing the students to the specified file
            for (Student std : students) {
                o.writeObject(std);
            }

            o.close();
            f.close();
        } catch (Exception e) {
            System.out.println("File cannot be made.");
        }
    }

    /**
     * Deserialize Students from a file
     * @return an array of students read in
     */
    private static Student[] deserializeStudents() {
        in.nextLine();
        System.out.println("Input the name of file to read from");
        String fileName = in.nextLine();

        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            Student std;
            Student[] students = new Student[128];
            int i = 0;

            while ((std = (Student) oi.readObject()) != null) {
                students[i] = std;
                i++;
            }

            oi.close();
            fi.close();

            return students;
        } catch (Exception e) {
            System.out.println("File could not be found.");
            return null;
        }
    }

    /**
     * Print statistics of class
     * @param students array of students
     */
    private static void printStats(Student[] students) {
        int studentCount = 0;
        int males = 0, females = 0, other = 0;
        int freshmen = 0, sophomores = 0, juniors = 0, seniors = 0;

        for (int i = 0; i < students.length; i++) {
            Student student = students[i];

            if (student != null) {
                studentCount++;

                if (student.getGender().equals("Male")) {
                    males++;
                } else if (student.getGender().equals("Female")) {
                    females++;
                } else {
                    other++;
                }

                switch (student.getGrade()) {
                    case 9:
                        freshmen++;
                        break;

                    case 10:
                        sophomores++;
                        break;

                    case 11:
                        juniors++;
                        break;

                    case 12:
                        seniors++;
                        break;
                }
            }
        }

        System.out.printf("\nThere are %d students.\n\nMales: %d\nFemales: %d\nOther: %d\n\nFreshmen: %d" +
                        "\nSophomores: %d\nJuniors: %d\nSeniors: %d\n",
                studentCount, males, females, other, freshmen, sophomores, juniors, seniors);
    }

    /**
     * Main method to run
     */
    public static void main(String args[]) {
        Student[] students = new Student[STUDENT_ARRAY_SIZE];
        readFile(students);

        boolean run = true;

        while (run) {
            int input = 0;

            input = getIntInput("\nInput command number:\n0. Exit\n1. Find Student\n2. Delete Student" +
                                "\n3. Add Student\n4. Sort Students by Name\n5. Sort Students by ID" +
                                "\n6. Print Students (Command)\n7. Serialize Students\n8. Read Students" +
                                "\n9. Get Class Statistics");

            if (input == 1) {
                Student tmp = getStudent(students, "Input the last name or ID of student");
                printStudentInfo(tmp);
            } else if (input == 2) {
                removeStudent(students);
            } else if (input == 3) {
                addStudent(students);
            } else if (input == 4) {
                sortStudentsName(students);
            } else if (input == 5) {
                sortStudentsID(students);
            } else if (input == 6) {
                for (int i = 0; i < students.length; i++) {
                    Student tmp = students[i];

                    if (tmp != null) {
                        System.out.print("\n" + i + " ");
                        printStudentInfo(tmp);
                    }
                }
            } else if (input == 7) {
                serializeStudents(students);
            } else if (input == 8) {
                Student[] tmpStudents = deserializeStudents();

                if (tmpStudents != null) {
                    students = tmpStudents;
                }
            } else if (input == 9) {
                printStats(students);
            } else if (input == 0) {
                in.close();
                run = false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}
