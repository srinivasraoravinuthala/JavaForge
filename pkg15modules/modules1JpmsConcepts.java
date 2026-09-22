package pkg15modules;

/*
 * modules1JpmsConcepts.java
 * -------------------------
 * Java Platform Module System (JPMS) — concepts and vocabulary (Java 9+).
 *
 * DEFINITION:
 *   JPMS (Project Jigsaw) groups code into modules with explicit dependencies.
 *   A module declares what it exports (API) and what it requires (dependencies).
 *   Strong encapsulation: code not exported is inaccessible even via reflection.
 *
 * KEY POINTS:
 *   - module-info.java at the root of a module defines name, requires, exports, provides/uses.
 *   - Classpath apps (no module-info) run in the "unnamed module".
 *   - Automatic modules: a plain JAR on the module path becomes an automatic module.
 *   - Services: provides X with Y + uses X + ServiceLoader (see modules2).
 *
 * Run a real modular demo: see pkg15modules/jpms-demo/ (Maven multi-module).
 */
public class modules1JpmsConcepts {

    public static void main(String[] args) {
        Module thisModule = modules1JpmsConcepts.class.getModule();
        System.out.println("This class's module : " + thisModule.getName());
        System.out.println("Is named module?    : " + thisModule.isNamed());
        System.out.println("Can read java.base? : " + thisModule.canRead(ModuleLayer.boot().findModule("java.base").orElseThrow()));

        System.out.println("\nmodule-info.java skeleton:");
        System.out.println("""
              module com.myapp {
                  requires java.sql;           // dependency
                  requires transitive java.logging; // re-export to consumers
                  exports com.myapp.api;       // public API package
                  opens com.myapp.internal;    // deep reflection allowed
                  provides com.myapp.spi.Plugin with com.myapp.impl.PluginImpl;
                  uses com.myapp.spi.Plugin;
              }""");

        System.out.println("\nClasspath vs Module path:");
        System.out.println("  --class-path   : unnamed module, all jars visible (legacy)");
        System.out.println("  --module-path  : named modules, strong encapsulation");
        System.out.println("  java --module-path mods --module com.myapp/com.myapp.Main");
    }
}
