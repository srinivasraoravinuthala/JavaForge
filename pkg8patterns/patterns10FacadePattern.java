package pkg8patterns;

/*
 * Facade (Structural)
 * -------------------
 * INTENT: provide a single, simplified interface to a complex subsystem.
 * UML: Facade --> {SubsystemA, SubsystemB, SubsystemC}.
 * PROS: reduces coupling; easier to use; hides complexity.
 * CONS: can become a god object if it grows unchecked.
 * REAL-WORLD: javax.faces, JOptionPane, a "service" wrapping repositories.
 */
public class patterns10FacadePattern {

    // Complex subsystem
    static class Cpu { String freeze() { return "CPU freeze"; } String execute() { return "CPU execute"; } }
    static class Memory { String load(String data) { return "Memory load(" + data + ")"; } }
    static class HardDrive { String read() { return "HardDrive read boot sector"; } }

    // Facade exposes a simple operation
    static class Computer {
        private final Cpu cpu = new Cpu();
        private final Memory memory = new Memory();
        private final HardDrive disk = new HardDrive();
        void start() {
            System.out.println(cpu.freeze());
            System.out.println(memory.load(disk.read()));
            System.out.println(cpu.execute());
            System.out.println("Computer started.");
        }
    }

    public static void main(String[] args) {
        new Computer().start();   // client uses one simple call
    }
}
