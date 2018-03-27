import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static void sendLog(String first_name, String last_name, long user_id, String txt){
        System.out.println("----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + " (id = " + user_id + ") \n Text - " + txt);
    }
}