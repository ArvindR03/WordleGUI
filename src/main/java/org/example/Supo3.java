package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.example.Supo3.Time.Memento;

import java.time.*;

class Supo3 {
    
    public class Car {

        private String manufacturer;
        private int age;

        public Car(String manufacturer, int age) {
            this.manufacturer = manufacturer;
            this.age = age;
        }

    }

    class SortByManufacturer implements Comparator<Car> {

        @Override
        public int compare(Car o1, Car o2) {
            //returns negative if o1 < o2 else positive (alphabetically)
            return o1.manufacturer.compareTo(o2.manufacturer);
        }

    }

    static class Singleton {
        private static Singleton inner;

        private Singleton() {}

        public static Singleton getSingleton() {
            if (inner==null) {
                inner = new Singleton();
            }
            return inner;
        }

    }

    // an implementation showing how the Memento pattern works -
    // it saves snapshots of state in time, like simplified version control
    static class Time {

        private LocalDateTime datetime;

        public Time() {
            this.Set();
        }

        public void Set() {
            this.datetime = LocalDateTime.now();
        }

        public LocalDateTime getDateTime() {
            return this.datetime;
        }

        public Memento getMemento() {
            return new Memento(this.datetime);
        }

        public static class Memento {

            private final LocalDateTime datetime;
            public Memento(LocalDateTime d) {
                this.datetime = d;
            }
            public LocalDateTime viewDateTime() {
                return this.datetime;
            }

        }



    }

    static Exception notValidIndex;
    static class CareTaker {
        private List<Time.Memento> historyOfDateTimes = new ArrayList<Time.Memento>();

        public LocalDateTime returnSelectedDateTime(int i) throws Exception {
            if (i < this.historyOfDateTimes.size()) {
                return this.historyOfDateTimes.get(i).viewDateTime();
            } else {
                throw notValidIndex;
            }
            
        }

        public List<Time.Memento> returnAllDateTimes() {
            return this.historyOfDateTimes;
        }

        public void addMemento(Memento memento) {
            this.historyOfDateTimes.add(memento);
        }
    }


    public static void main(String[] args) throws Exception {

        CareTaker caretaker = new CareTaker();

        for (int i = 0; i < 5; i++) {

            Time time = new Time();
            caretaker.addMemento(time.getMemento());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println(caretaker.returnSelectedDateTime(3));

    }
    


}
