import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int PART = 12121;

    public static void main(String[] args) throws IOException {

        //Много информации передавать/получать не нужно, фактически лишь номер числа, и отправить лишь значение.
        // Нечего кэшировать и нечем другим нагрузить потоки. Поэтому пускай ждут.
        ExecutorService es = Executors.newFixedThreadPool(2);
        try {
            es.submit(() -> new Server().start(Main.PART));
            es.submit(() -> new Client().start(Main.PART));
            es.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            es.shutdown();
        }


    }
}
