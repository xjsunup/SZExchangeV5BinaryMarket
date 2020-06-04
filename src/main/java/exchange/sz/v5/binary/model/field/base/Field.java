package exchange.sz.v5.binary.model.field.base;

/**
 * @author xuejian.sun
 * @date 2019/12/4 17:14
 */
public interface Field<T> {
    /**
     * 转成字节数组
     *
     * @return byte array
     */
    byte[] toBytes();

    /**
     * 从字节中读取
     * @param bytes 字节数据
     */
     void resumeFrom(byte[] bytes);
}
