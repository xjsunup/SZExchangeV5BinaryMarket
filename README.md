# SZExchangeV5BinaryMarket
深交所v5版行情接入框架

标准的v5版二进制协议,

内部使用了netty作为框架, 封装了一个facade类用于客户端回调行情,已经系统消息.
按照quickfix实现方式,组装解析二进制协议, 可简单快速扩展新的数据类型处理.

- 已完成的消息处理
- [ x ] 深圳level1行情快照
- [ x ] 登陆消息
- [ x ] 登出消息
- [ x ] 心跳消息

如需处理指数,逐笔委托等消息请参考,level1消息处理流程进行扩展.

## quickstart

在该类中填写测试环境地址账号等,即可接收到行情, 需要测试环境请加QQ: 785272464

```java
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SZExchangeConfigure configure = new SZExchangeConfigure();
        configure.setReconnect(5);
        configure.setReconnectInterval(10);
        configure.setServerHost("127.0.0.1");
        configure.setServerPort(7777);
        configure.setHeartbeatInterval(10);
        configure.setPassword("");
        configure.setSenderCompId("Test");
        configure.setTargetCompId("YFY_Send");
        configure.setVersion("1.01");
        SZV5MarketBootstrap bootstrap = SZV5MarketBootstrap.getInstance(configure, new SZV5MessageFacade());
        Runtime.getRuntime().addShutdownHook(new Thread(bootstrap::logout));
        boolean connect = bootstrap.connect();
        if(connect) {
            bootstrap.login();
        }
        new CountDownLatch(1).await();
    }
```
