
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Single-threaded check ===");

        logger logger1 = Logger.getInstance();
        logger1.log("First message via logger1");

        Logger logger2 = Logger.getInstance();
        logger2.log("Second message via logger2");

        System.out.println("logger1 hashCode: " + logger1.hashCode());
        System.out.println("logger2 hashCode: " + logger2.hashCode());
        System.out.println("logger1 == logger2 ? " + (logger1 == logger2));
        System.out.println("Shared log count (should be 2): " + logger1.getLogCount());

        if (logger1 != logger2) {
            throw new AssertionError("FAILED: Two different Logger instances were created!");
        }
        System.out.println("PASSED: Only one instance exists.\n");

        System.out.println("=== Multi-threaded check ===");
        testMultiThreaded();
    }

   
    private static void testMultiThreaded() throws InterruptedException {
        int threadCount = 8;
        Thread[] threads = new Thread[threadCount];
        final Logger[] capturedInstances = new Logger[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int idx = i;
            threads[i] = new Thread(() -> {
                Logger logger = Logger.getInstance();
                capturedInstances[idx] = logger;
                logger.log(Thread.currentThread().getName() + " is logging");
            }, "Thread-" + i);
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        
        Logger reference = capturedInstances[0];
        boolean allSame = true;
        for (Logger l : capturedInstances) {
            if (l != reference) {
                allSame = false;
                break;
            }
        }

        System.out.println("All " + threadCount + " threads received the same instance? " + allSame);
        System.out.println("Final shared log count (should be 2 + " + threadCount + " = " + (2 + threadCount) + "): "
                + reference.getLogCount());

        if (!allSame) {
            throw new AssertionError("FAILED: Threads received different Logger instances!");
        }
        System.out.println("PASSED: Singleton holds under concurrent access.");
    }
}