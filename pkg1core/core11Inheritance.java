package pkg1core;

/*
 * core11Inheritance.java
 * ----------------
 * Reusing and extending behavior with `extends`, `super`, and constructors.
 *
 * EXPLANATION:
 *  - A subclass inherits accessible fields/methods of its superclass.
 *  - `super(...)` calls the parent constructor; `super.m()` calls the parent method.
 *  - Constructors run parent-first (Object -> ... -> subclass).
 *  - Favor composition over inheritance when "is-a" doesn't truly hold.
 */
public class core11Inheritance {

    static class Animal {
        protected String name;
        Animal(String name) { this.name = name; }
        String sound() { return "..."; }
        void describe() { System.out.println(name + " says " + sound()); }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }          // call parent constructor
        @Override String sound() { return "Woof"; } // override behavior
    }

    static class Puppy extends Dog {
        Puppy(String name) { super(name); }
        @Override String sound() {
            return super.sound() + " (tiny)";       // extend parent behavior
        }
    }

    public static void main(String[] args) {
        new Animal("Generic").describe();
        new Dog("Rex").describe();
        new Puppy("Bit").describe();

        // Subclass IS-A superclass
        Animal a = new Dog("Fido");
        System.out.println("Dog is Animal? " + (a instanceof Animal));
        System.out.println("a.sound() dispatches to Dog: " + a.sound());
    }
}
