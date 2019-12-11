import exchange.sz.v5.binary.model.message.SZV5Logon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author xuejian.sun
 * @date 2019/12/9 13:02
 */
public class AppTest {

    public static void main(String[] args) {
        SZV5Logon data = new SZV5Logon("","a","b",5,"1.0");
        byte[] bytes = data.toSZV5BinaryMessage();
        new Thread(() -> newObj(bytes.clone())).start();
        new Thread(() -> {
            try {
                invokeObj(bytes.clone());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void newObj(byte[] data) {
        int i = 1000000;
        LocalTime start = LocalTime.now();
        while(0 < i--) {
            SZV5Logon logon = new SZV5Logon();
            logon.resumeMessageFrom(data);
        }
        System.out.println("new cost -> "+ Duration.between(start,LocalTime.now()).toMillis());
    }

    private static void invokeObj(byte[] data) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        int i = 1000000;
        LocalTime start = LocalTime.now();
        Method method = null;
        Class<SZV5Logon> szv5LogonClass = SZV5Logon.class;
        while(0 < i--) {
            SZV5Logon logon = szv5LogonClass.newInstance();
            if(method == null){
                method = szv5LogonClass.getMethod("resumeMessageFrom", byte[].class);
            }
            method.invoke(logon,data);
        }
        System.out.println("invoker cost -> "+ Duration.between(start,LocalTime.now()).toMillis());
    }
}
