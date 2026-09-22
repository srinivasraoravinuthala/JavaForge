package pkg8patterns;

/*
 * Factory Method (Creational)
 * ---------------------------
 * INTENT: define an interface for creating an object, but let subclasses decide
 *         which class to instantiate.
 * UML: Creator + factoryMethod(): Product ; ConcreteCreator overrides it.
 * PROS: decouples client from concrete classes; open/closed for new products.
 * CONS: many small subclasses.
 * REAL-WORLD: java.util.Calendar.getInstance, NumberFormat.getInstance.
 */
public class patterns2FactoryMethodPattern {

    interface Notification { String send(); }
    static class Email implements Notification { public String send() { return "Email sent"; } }
    static class Sms implements Notification { public String send() { return "SMS sent"; } }
    static class Push implements Notification { public String send() { return "Push sent"; } }

    // Factory method centralizes object creation
    static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new Email();
            case "sms"   -> new Sms();
            case "push"  -> new Push();
            default -> throw new IllegalArgumentException("unknown type: " + type);
        };
    }

    public static void main(String[] args) {
        for (String t : new String[]{"email", "sms", "push"}) {
            System.out.println(t + " -> " + create(t).send());
        }
    }
}
