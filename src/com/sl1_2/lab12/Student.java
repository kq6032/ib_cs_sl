package com.sl1_2.lab12;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private int grade;
    private String lastName;
    private String firstName;
    private String gender;

    /**
     * Constructor for Student class
     * @param id student ID number
     * @param grade current grade of the student
     * @param lastName last name of the student
     * @param firstName first name of the student
     * @param gender gender of the student
     */
    public Student(int id, int grade, String lastName, String firstName, String gender) {
        this.id = id;
        this.grade = grade;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
    }

    /**
     * Get ID number
     * @return ID number
     */
    public int getId() {
        return id;
    }

    /**
     * Set new ID number
     * @param id new ID number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get grade level
     * @return grade level
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Set new grade level
     * @param grade new grade level
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Get last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set new last name
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set new first name
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set new gender
     * @param gender new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
