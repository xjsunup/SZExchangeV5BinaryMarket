# SZExchangeV5BinaryMarket
深交所v5版行情接入框架

标准的v5版二进制协议,

内部使用了netty作为框架, 封装了一个facade类用于客户端回调行情,已经系统消息.
按照quickfix实现方式,组装解析二进制协议, 可简单快速扩展新的数据类型处理.
