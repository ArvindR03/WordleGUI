package org.example;

public class Supo1 {
    int sum(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res += a[i];
        }
        return res;
    }

    int[] cumsum(int[] a) {
        int[] res = new int[0];
        int[] cumlist = new int[0];
        cumlist[0] = res[0];
        for (int i = 1; i < a.length; i++) {
            cumlist[i] = sum(cumlist);
        }
        return cumlist;
    }

    int[] positives(int[] a) {
        int[] res = new int[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0) {
                res[res.length] = a[i];
            }
        }
        return res;
    }

    float[][] Matrix(int n) {
        // creates array of arrays of 0.0
        float[][] res = new float[0][];
        for (int i = 0; i < n; i++) {
            float[] temp = new float[0];
            for (int j = 0; j < n; j++) {
                temp[j] = 0.0F;
            }
            res[i] = temp;
        }
        return res;
    }

    public static int[] vectorAdd(int x, int y, int dx, int dy) {
        x = x + dx;
        y = y + dy;
        return new int[] {x, y}; //read the output from an array of length 2
    }

    static class LinkedList {
        private Node head;
        private Node next;

        class Node {
            private int value;
            private Node next;

            Node(int v, Node n) {
                this.value = v;
                this.next = n;
            }
        }

        LinkedList(int n) {
            Node node = new Node(n, null);
            this.head = node;
            this.next = node.next;
        }

        private void addElemToHead(int n) {
            this.next = this.head;
            this.head = new Node(n, this.next);
        }

        private void discardHead() {
            this.head = this.next;
            this.next = this.next.next;
        }

        private int nthElem(int nth) {
            Node c = this.head;
            for (int i = 0; i < nth; i++) {
                c = c.next;
            }
            return c.value;
        }

        private int lengthList() {
            Node c = this.head;
            int count = 0;
            while (c.next != null) {
                count++;
            }
            return count;
        }

        boolean contains(Node[] a, Node b) {
            for (int i=0; i < a.length; i++) {
                if (a[i] == b) {
                    return false;
                }
            }
            return true;
        }

        private boolean checkCycle() {
            Node c = this.head;
            Node[] n = new Node[0];
            while (!(contains(n, c)) && (c.next != null)) {
                n[n.length] = c;
                c = c.next;
            }
            if (c.next == null) {
                return false;
            } else {
                return true;
            }
        }

    }

    static class Stack {
        private int top;
        private int[] values;
        private int length;

        private void push(int x) {
            this.top = x;
            int[] res = new int[this.length];
            res[0] = x;
            if (this.values != null) {
                for (int i = 0; i < this.values.length; i++) {
                    res[i + 1] = this.values[i];
                    this.length++;
                }
            }
            this.values = res;
        }

        Stack(int[] n) {
            for (int i=0; i<n.length;i++) {
                this.length++;
                push(n[i]);
            }
        }
    }
}
