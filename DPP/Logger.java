/**
 * Logger - a Singleton utility class.
 *
 * Guarantees that only ONE instance of Logger ever exists in the
 * application, and that every part of the application logs through
 * that same instance.
 *
 * Pattern used: Lazy initialization with Double-Checked Locking.
 *   - "Lazy" because the instance is only created the first time
 *     it's actually needed (not when the class is loaded).
 *   - "Double-checked locking" + "volatile" because this makes the
 *     getInstance() method safe to call from multiple threads at
 *     once without creating two different instances.
 */
public class Logger {

    // The single instance. 'volatile' ensures that when one thread
    // creates the instance, other threads immediately see the
    // fully-constructed object (prevents a subtle multithreading bug).
    private static volatile Logger instance;

    // Some simple state to prove later that everyone shares it.
    private int logCount = 0;

    /**
     * Private constructor: this is the key to the pattern.
     * Because it's private, NO other class can do `new Logger()`.
     * The only way to get a Logger is through getInstance().
     */
    private Logger() {
        System.out.println("[Logger] Instance created: " + this.hashCode());
    }

    /**
     * The single global access point to the Logger instance.
     *
     * First check (no lock): fast path once the instance already
     * exists — avoids paying for synchronization on every call.
     *
     * Second check (inside the lock): protects against two threads
     * both passing the first check at the same time and creating
     * two separate instances.
     */
    public static Logger getInstance() {
        if (instance == null) {                 // 1st check (no locking)
            synchronized (Logger.class) {
                if (instance == null) {          // 2nd check (with locking)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Logs a message, prefixed with a running count, so we can verify
     * that calls from different parts of the program all accumulate
     * into the same counter (proof that it's the same instance).
     */
    public synchronized void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }

    public int getLogCount() {
        return logCount;
    }
}