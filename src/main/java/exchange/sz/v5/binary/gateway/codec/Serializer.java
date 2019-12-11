package exchange.sz.v5.binary.gateway.codec;

/**
 * @author xuejian.sun
 * @date 2019/12/9 10:33
 */
public interface Serializer {
    /**
     * 序列化
     *
     * @param object 数据对象
     * @return 二进制内容
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * @param objClass 数据类型 class
     * @param data 数据内容
     * @return obj
     */
    <T> T deserialize(Class<T> objClass, byte[] data);

    /**
     * 序列化 算法
     * @return
     */
    byte serializerAlgo();
}
