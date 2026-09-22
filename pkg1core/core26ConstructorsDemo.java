package pkg1core;

/*
 * core26ConstructorsDemo.java
 * ---------------------------
 * Default, parameterized, and chained constructors; constructor overloading.
 *
 * EXPLANATION:
 *  - If you write no constructor, the compiler adds a no-arg default constructor.
 *  - Once you define any constructor, the default is NOT generated.
 *  - this(...) must be the first statement — chains to another constructor.
 *  - super(...) calls the parent constructor (must be first in subclass ctor).
 */
public class core26ConstructorsDemo {

    static class Employee {
        final String name;
        final int id;
        String department;

        Employee() {                          // default
            this("Unknown", 0);
        }

        Employee(String name, int id) {       // parameterized
            this(name, id, "General");
        }

        Employee(String name, int id, String department) {
            this.name = name;
            this.id = id;
            this.department = department;
        }

        @Override
        public String toString() {
            return name + " #" + id + " (" + department + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Employee());
        System.out.println(new Employee("Ravi", 101));
        System.out.println(new Employee("Maya", 102, "Engineering"));
    }
}
