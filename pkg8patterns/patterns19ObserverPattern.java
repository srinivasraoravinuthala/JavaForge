package pkg8patterns;

/*
 * Observer (Behavioral)
 * ---------------------
 * INTENT: define a one-to-many dependency so that when one object changes state,
 *         all its dependents are notified automatically.
 * UML: Subject + subscribe()/notify() ; Observers + update(state).
 * PROS: loose coupling; dynamic subscriptions; supports event-driven design.
 * CONS: notification order undefined; possible memory leaks (forgotten unsubscribe).
 * REAL-WORLD: listeners/events, pub-sub, reactive streams, MVC.
 */
import java.util.*;

public class patterns19ObserverPattern {

    interface Observer { void update(double price); }

    static class Stock {                       // Subject
        private final List<Observer> observers = new ArrayList<>();
        private double price;
        void subscribe(Observer o) { observers.add(o); }
        void unsubscribe(Observer o) { observers.remove(o); }
        void setPrice(double price) {
            this.price = price;
            for (Observer o : observers) o.update(price);   // notify all
        }
    }

    public static void main(String[] args) {
        Stock stock = new Stock();
        Observer trader = p -> System.out.println("Trader sees price " + p);
        Observer logger = p -> System.out.println("Logger records price " + p);

        stock.subscribe(trader);
        stock.subscribe(logger);
        stock.setPrice(100.0);

        System.out.println("-- trader unsubscribes --");
        stock.unsubscribe(trader);
        stock.setPrice(105.5);
    }
}
