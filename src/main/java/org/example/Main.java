package org.example;

public class Main {
    public static void main(String[] args) {
        // implementation of question 6
        int a=0;
        int b=2;
        int[] res = Supo1.vectorAdd(a,b,1,1);
        a = res[0];
        b = res[1];
        System.out.println((a+", "+b));
        Supo1.Stack s = new Supo1.Stack(new int[] {1,2,3});
        Supo1.LinkedList ll = new Supo1.LinkedList(5);
    }
}

//gitlab key glpat-NjCTjzP__nQ2HtaVycxY