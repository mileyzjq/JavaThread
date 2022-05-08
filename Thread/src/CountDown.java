import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDown {
    public static void main(String[] args) throws InterruptedException {
        Date startTime = new Date(System.currentTimeMillis());
        int cnt = 10;
        while(true) {
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis());
            cnt--;
            if(cnt <= 0) {
                break;
            }
        }
    }
}
