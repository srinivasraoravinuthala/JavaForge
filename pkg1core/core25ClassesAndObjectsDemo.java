package pkg1core;

/*
 * core25ClassesAndObjectsDemo.java
 * -------------------------------
 * Classes as blueprints; objects as instances; fields vs behavior.
 *
 * EXPLANATION:
 *  - A class defines state (fields) and behavior (methods).
 *  - `new` allocates an object on the heap; the reference variable points to it.
 *  - Each object has its own copy of instance fields; static fields are shared.
 */
public class core25ClassesAndObjectsDemo {

    static class Book {
        String title;
        int pages;

        void describe() {
            System.out.println("  " + title + " (" + pages + " pages)");
        }
    }

    public static void main(String[] args) {
        Book a = new Book();
        a.title = "Effective Java";
        a.pages = 416;

        Book b = new Book();
        b.title = "Clean Code";
        b.pages = 464;

        System.out.println("Two distinct objects:");
        a.describe();
        b.describe();

        System.out.println("a == b ? " + (a == b));           // different references
        System.out.println("a.title == b.title ? " + (a.title == b.title)); // false (different Strings)
    }
}
