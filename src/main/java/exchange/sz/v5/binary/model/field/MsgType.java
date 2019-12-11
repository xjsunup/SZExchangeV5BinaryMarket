package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.model.field.base.IntField;
import lombok.ToString;

/**
 * @author xuejian.sun
 * @date 2019/12/4 16:34
 */
@ToString
public class MsgType extends IntField {

    private SZV5MsgType msgTypeEnum;

    public MsgType(SZV5MsgType msgTypeEnum) {
        super(msgTypeEnum.getCode());
        this.msgTypeEnum = msgTypeEnum;
    }

    public SZV5MsgType getEnumValue(){
        return msgTypeEnum;
    }
}
