package pkg9io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * io5Serialization.java
 * ---------------------
 * Java object serialization: turning objects into bytes and back.
 *
 * DEFINITION:
 *   Serialization writes an object graph to a byte stream (Serializable);
 *   deserialization reconstructs it. Used for caching, deep copy, and (legacy)
 *   network transfer.
 *
 * KEY POINTS:
 *   - A class must implement Serializable; non-serializable fields need transient.
 *   - serialVersionUID pins the class version for compatibility.
 *   - transient fields are skipped (e.g. secrets, caches, derived data).
 *   - SECURITY: never deserialize untrusted data — prefer JSON for external I/O.
 */
public class io5Serialization {

    static class User implements Serializable {
        private static final long serialVersionUID = 1L;     // version contract
        String name;
        int age;
        transient String password;     // NOT serialized (skipped)

        User(String name, int age, String password) {
            this.name = name; this.age = age; this.password = password;
        }
        @Override public String toString() {
            return "User{name=" + name + ", age=" + age + ", password=" + password + "}";
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User original = new User("Ada", 36, "s3cr3t");
        System.out.println("Original     : " + original);

        // SERIALIZE to a byte array
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(original);
        }
        System.out.println("Serialized   : " + bytes.size() + " bytes");

        // DESERIALIZE back into a new object
        User restored;
        try (ObjectInputStream ois =
                 new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            restored = (User) ois.readObject();
        }
        System.out.println("Deserialized : " + restored);
        System.out.println("password lost (transient)? " + (restored.password == null));
        System.out.println("distinct objects (deep copy)? " + (original != restored));
    }
}
