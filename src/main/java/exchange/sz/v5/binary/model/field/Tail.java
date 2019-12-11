package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.UInt32Field;

/**
 * checksum
 * @author xuejian.sun
 * @date 2019/12/5 13:36
 */
public class Tail extends UInt32Field {

    public Tail(int checksum) {
        super(checksum);
    }

    public static Tail generateChecksum(byte[] data){
        int idx = 0;
        int cks = 0;
        //此处需要减去末尾所占的4个字节的checksum值
        while(idx < data.length - 4){
            cks += data[idx++];
        }
        return new Tail(cks % 256);
    }
}
