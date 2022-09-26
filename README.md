# WavegramApi

Telegram client and server implementation

## [Config.java](src/main/java/com/fakhruddin/wavegram/Config.java)

Setup your config

```java
public class Config {
    //For client obtain API_ID and API_HASH from https://my.telegram.org
    public static final int API_ID = 0;
    public static final String API_HASH = "";
    public static final String APP_VERSION = "1.0";
    //For server set up a mysql server (e.g. wamp, xampp) and
    //replace MYSQL_USERNAME and MYSQL_PASSWORD with your details
    public static final int SERVER_PORT = 443;
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "12345678";
    public static final String MYSQL_DBNAME = "wavegram";
}
```

## Client Example
 
[ClientMain.java](src/main/java/com/fakhruddin/wavegram/ClientMain.java)

## Server Example

You have to write all content-related method responses
implementing [MessageHandler.java](src/main/java/com/fakhruddin/wavegram/server/MessageHandler.java). Default
implementation
is [WavegramMessageHandler.java](src/main/java/com/fakhruddin/wavegram/server/WavegramMessageHandler.java) (I don't
know If I will implement all methods).

[ServerMain.java](src/main/java/com/fakhruddin/wavegram/ServerMain.java)

## Resources

* [MTPROTO](https://core.telegram.org/mtproto)