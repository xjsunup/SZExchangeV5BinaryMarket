package exchange.sz.v5.binary.gateway.codec;

import exchange.sz.v5.binary.model.message.Message;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xuejian.sun
 * @date 2019/12/9 10:37
 */
@Slf4j
public class SZV5MessageSerializer implements Serializer {

    private Method resumeMethod;

    @Override
    public byte[] serialize(Object object) {
        if(object instanceof Message) {
            Message message = (Message) object;
            return message.toSZV5BinaryMessage();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> objClass, byte[] data) {
        try {
            T t = objClass.newInstance();
            if(t instanceof Message) {
                if(resumeMethod == null) {
                    resumeMethod = objClass.getMethod("resumeMessageFrom", byte[].class);
                }
                resumeMethod.invoke(t, (Object) data);
                return t;
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public byte serializerAlgo() {
        return 1;
    }
}
