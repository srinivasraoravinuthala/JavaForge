package pkg8patterns;

/*
 * Singleton (Creational)
 * ----------------------
 * INTENT: ensure a class has exactly one instance and a global access point.
 * UML:  Singleton  - instance: Singleton  + getInstance(): Singleton
 * PROS: controlled single instance; lazy init; saves resources.
 * CONS: global state (hard to test); can hide dependencies; concurrency care needed.
 * REAL-WORLD: Runtime, Logger, configuration, connection pool.
 */
public class patterns1SingletonPattern {

    // Best practice: enum singleton (thread-safe, serialization-safe).
    enum Config {
        INSTANCE;
        private int version = 1;
        int getVersion() { return version; }
    }

    // Classic lazy + thread-safe via holder idiom (lazy, no locking cost).
    static class Logger {
        private Logger() {}
        private static class Holder { static final Logger INSTANCE = new Logger(); }
        static Logger getInstance() { return Holder.INSTANCE; }
        void log(String m) { System.out.println("[LOG] " + m); }
    }

    public static void main(String[] args) {
        System.out.println("enum singleton same? " + (Config.INSTANCE == Config.INSTANCE));
        System.out.println("version = " + Config.INSTANCE.getVersion());

        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();
        System.out.println("holder singleton same? " + (a == b));
        a.log("hello from the one and only logger");
    }
}
