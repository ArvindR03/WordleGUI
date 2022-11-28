package org.example;

import java.util.ArrayList;
import java.util.List;

public class Supo2 {

    // question 1 accompanying examples

    static class Class {
        private int x = 3; // this is only callable by the class and its methods
        protected int y = 4; // this is callable by the class, and all of its sublcasses too
                             // as well as its methods

        void method() {
            System.out.println("this is the normal class implementation of method");
        }
    }

    abstract static class AClass extends Class {
        @Override
        abstract void method();
    }

    public static class AClassChild extends AClass {
        public void method() {
            System.out.println("this is the abstract class implementation of method");
        }
    }

    public static interface Interface {
        void method();
    }

    static class InterfaceClass implements Interface {
        public void method() {
            System.out.println("this is the interface implementation of method");
        }
    }




    public static class Question9 {
        // implementing 2a ie. the shop scenario, see Main for the test, executable

        public static class Shop {
            private String name;
            private String address;
            private List<Item> items = new ArrayList<Item>();
            private List<Department> departments = new ArrayList<Department>();

            Shop(String name, String address, List<Item> items, List<Department> departments) {
                this.name = name;
                this.address = address;
                this.items = items;
                this.departments = departments;
            }

            Shop(String name, String address) {
                this.name = name;
                this.address = address;
            }

            public String getLocation() {
                return this.address;
            }

            public String getName () {
                return this.name;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void addItem(Item item) {
                this.items.add(item);
            }

            public void resetItems() {
                this.items.clear();
            }

            public List<Item> getItems() {
                return this.items;
            }

            public void addDepartment(Department department) {
                this.departments.add(department);
            }

            public void resetDepartments() {
                this.departments.clear();
            }

            public List<Department> getDepartments() {
                return this.departments;
            }
        
        }

        public static class Item {
            private String name;
            private float value = 0.0F;
            private boolean discount = false;
            private List<Shop> shops = new ArrayList<Shop>();

            Item(String name, float value, boolean discount, List<Shop> shops) {
                this.name = name;
                this.value = value;
                this.discount = discount;
                this.shops = shops;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public float getValue() {
                return this.value;
            }

            public void setValue(float value) {
                this.value = value;
            }

            public boolean getDiscount() {
                return this.discount;
            }

            public void addDiscount(float percentDiscount) {
                this.discount = true;
                float p = 1 - percentDiscount;
                this.value = this.value * p;
            }

            public void resetDiscount(float originalPrice) {
                this.discount = false;
                this.value = originalPrice;
            }

            public List<Shop> getShops() {
                return this.shops;
            }

            public void addShop(Shop shop) {
                this.shops.add(shop);
            }

            public void resetShops() {
                this.shops.clear();
            }

            public Shop setShop(int i, Shop shop) {
                return this.shops.set(i, shop);
            }

            public void inflateValue(float percentHike) {
                float p = percentHike + 1;
                this.value = this.value * p;
            }

        }

        public static class Department {
            private String name;
            private Shop shop;
            private Manager manager;
            private List<Assistant> assistants = new ArrayList<Assistant>();

            Department(String name, Shop shop, Manager manager, List<Assistant> assistants) {
                this.name = name;
                this.shop = shop;
                this.manager = manager;
                this.assistants = assistants;
            }

            Department(String name, Shop shop, Manager manager) {
                this.name = name;
                this.shop = shop;
                this.manager = manager;
            }

            Department(String name, Shop shop) {
                this.name = name;
                this.shop = shop;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public Shop getShop() {
                return this.shop;
            }

            public Manager getManager() {
                return this.manager;
            }

            public void setManager(Manager manager) {
                this.manager = manager;
            }

            public void addAssistant(Assistant assistant) {
                this.assistants.add(assistant);
            }

            public void resetAssistants() {
                this.assistants.clear();
            }

            public List<Assistant> getAssistants() {
                return this.assistants;
            }
        }

        public static class Person {
            protected String name, role;
            protected float salary;
            protected Department department;

            Person(String name, String role, float salary, Department department) {
                this.name = name;
                this.role = role;
                this.salary = salary;
                this.department = department;
            }
        }

        public static class Manager extends Person {
            private String experience = "junior";

            Manager(String name, String role, float salary, Department department, String experience) {
                super(name, role, salary, department);
                this.experience = experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public String getExperience() {
                return this.experience;
            }

        }

        public static class Assistant extends Person {
            private String role;

            Assistant(String name, String role, float salary, Department department) {
                super(name, role, salary, department);
                this.role = role;
            }

            public void changeRole(String role) {
                this.role = role;
            }

            public String getRole() {
                return this.role;
            }
        }



        public static void main(String[] args) {

            // implemented a shop where we have milk, a manager of a hr department and a shop assistant.
            // printed the experience of the manager, and added a discount, then printed whether there is one or not and the new price
    
            // prints "junior", "1.89", "true", "0.00188..."
            
            Shop sainsburys = new Shop("Sainsbury's", "27 Eddington Avenue, Cambridge, CB3 1SE");
            sainsburys.addDepartment(new Department("HR", sainsburys));
            sainsburys.addDepartment(new Department("customer service", sainsburys));
            List<Shop> shops = new ArrayList<Shop>();
            shops.add(sainsburys);
            sainsburys.addItem(new Item("milk", 1.89F, false, shops));
            sainsburys.getDepartments().get(0).setManager(new Manager("Baylee Neal", "hr manager", 100000, sainsburys.getDepartments().get(0), "junior"));
            sainsburys.getDepartments().get(0).addAssistant(new Assistant("Faustina Das", "cashier", 2000, sainsburys.getDepartments().get(1)));
    
            System.out.println(sainsburys.getDepartments().get(0).getManager().getExperience()); ;
            System.out.println(sainsburys.getItems().get(0).getValue());
    
            sainsburys.getItems().get(0).addDiscount(0.999F);
            System.out.println(sainsburys.getItems().get(0).getDiscount());
            System.out.println(sainsburys.getItems().get(0).getValue());
    
        }

        

    }
}
