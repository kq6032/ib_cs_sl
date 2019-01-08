package com.sl1_2;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }
}

class LinkedList {
    public Node head;

    public void appendNode(int data) {
        if (this.head == null) {
            head = new Node(data);
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
    }

    public void printLinkedList() {
        Node current = this.head;

        while (current != null) {
            System.out.println(current.data);

            current = current.next;
        }
    }
}

/**
 * Tester File - All things in this file are temporary.
 */

public class Tester {

    public static final double PLANCKS_CONST = 6.626 * Math.pow(10, -34);
    public static final double LIGHT_SPEED = 3.00 * Math.pow(10, 17);

    public static void main(String args[]) {
        /*double[] vals = new double[]{600, 660, 670, 630, 650, 530, 490, 570, 660, 670};
        double freq, unc, ener;

        for (int i = 0; i < vals.length; i++) {
            freq = LIGHT_SPEED / vals[i];
            unc = 10 / vals[i] * freq;

//            System.out.println(i + ": " + freq + "\n\t" + unc);

            ener = PLANCKS_CONST * freq;
            unc = unc / freq * ener;
            System.out.println(i + ": " + ener + "\n\t" + unc);
        }*/

        System.out.println("Breakpoint 1");
        System.out.println("Breakpoint 2");

        LinkedList testList = new LinkedList();

        testList.appendNode(5);
        testList.appendNode(4);
        testList.appendNode(3);
        testList.printLinkedList();
    }
}
