package exchange.sz.v5.binary.annotation;

import exchange.sz.v5.binary.enums.SZV5MsgType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuejian.sun
 * @date 2019/12/11 14:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SZV5Message {
    /**
     * 消息类型
     * @return SZV5MsgType
     */
    SZV5MsgType msgType();
}
