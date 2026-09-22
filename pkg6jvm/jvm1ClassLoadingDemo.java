package pkg6jvm;

/*
 * jvm1ClassLoadingDemo.java
 * ---------------------
 * Shows the class loader hierarchy and the order of static initialization.
 *
 * CLASS LOADING PHASES:
 *   Loading -> Linking (Verify -> Prepare -> Resolve) -> Initialization
 * LOADER DELEGATION (parent-first):
 *   Bootstrap (core JDK) -> Platform -> Application (your classpath)
 *
 * INITIALIZATION ORDER (on first active use of a class):
 *   static fields & static blocks run top-to-bottom, exactly once.
 */
public class jvm1ClassLoadingDemo {

    static class Config {
        static final String NAME;
        static int counter;
        static {                                   // static initializer block
            System.out.println("  [Config static block] runs once on first use");
            NAME = "JavaMastery";
            counter = 100;
        }
        Config() { System.out.println("  [Config constructor] runs per instance"); }
    }

    public static void main(String[] args) {
        // 1) Class loader hierarchy
        ClassLoader app = jvm1ClassLoadingDemo.class.getClassLoader();
        System.out.println("Application loader : " + app);
        System.out.println("Platform loader    : " + app.getParent());
        System.out.println("Bootstrap loader   : " + null + " (represented as null; loads java.* core)");

        // Core classes are loaded by the bootstrap loader (null)
        System.out.println("String's loader    : " + String.class.getClassLoader() + " (bootstrap)");

        // 2) Static initialization happens on first ACTIVE use
        System.out.println("\nBefore touching Config (not initialized yet)...");
        System.out.println("Touching Config.NAME now:");
        System.out.println("  Config.NAME = " + Config.NAME);   // triggers static block
        System.out.println("Creating two instances:");
        new Config(); new Config();                              // static block does NOT rerun
        System.out.println("Config.counter = " + Config.counter);
    }
}
