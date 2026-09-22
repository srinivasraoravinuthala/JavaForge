package pkg8patterns;

/*
 * Abstract Factory (Creational)
 * -----------------------------
 * INTENT: provide an interface for creating FAMILIES of related objects without
 *         specifying their concrete classes.
 * UML: AbstractFactory + createA(): A + createB(): B ; concrete factories per family.
 * PROS: guarantees products from one family are used together; easy to swap families.
 * CONS: adding a new product type requires changing every factory.
 * REAL-WORLD: cross-platform UI toolkits (Windows vs Mac widgets).
 */
public class patterns3AbstractFactoryPattern {

    interface Button { String render(); }
    interface Checkbox { String render(); }

    static class WinButton implements Button { public String render() { return "[Windows Button]"; } }
    static class WinCheckbox implements Checkbox { public String render() { return "[Windows Checkbox]"; } }
    static class MacButton implements Button { public String render() { return "(Mac Button)"; } }
    static class MacCheckbox implements Checkbox { public String render() { return "(Mac Checkbox)"; } }

    interface GuiFactory { Button button(); Checkbox checkbox(); }
    static class WinFactory implements GuiFactory {
        public Button button() { return new WinButton(); }
        public Checkbox checkbox() { return new WinCheckbox(); }
    }
    static class MacFactory implements GuiFactory {
        public Button button() { return new MacButton(); }
        public Checkbox checkbox() { return new MacCheckbox(); }
    }

    static void renderUI(GuiFactory factory) {
        System.out.println(factory.button().render() + " " + factory.checkbox().render());
    }

    public static void main(String[] args) {
        System.out.print("Windows UI: "); renderUI(new WinFactory());
        System.out.print("Mac UI:     "); renderUI(new MacFactory());
    }
}
