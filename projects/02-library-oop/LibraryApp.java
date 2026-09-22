import java.util.ArrayList;
import java.util.List;

/** Tiny library domain — Project 02 (after OOP chapters). */
public class LibraryApp {
    static class Book {
        private final String isbn;
        private final String title;
        private String borrowedBy;

        Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        boolean isAvailable() { return borrowedBy == null; }

        void checkout(String memberId) {
            if (!isAvailable()) throw new IllegalStateException("Already borrowed");
            borrowedBy = memberId;
        }

        void giveBack() { borrowedBy = null; }

        @Override
        public String toString() {
            return title + " [" + isbn + "] " + (isAvailable() ? "available" : "→ " + borrowedBy);
        }
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("978-1", "Effective Java"));
        books.add(new Book("978-2", "Java Concurrency in Practice"));

        books.get(0).checkout("member-42");
        books.forEach(System.out::println);
        books.get(0).giveBack();
        System.out.println("--- after return ---");
        books.forEach(System.out::println);
    }
}
