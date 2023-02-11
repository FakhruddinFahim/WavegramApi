package com.fakhruddin.wavegram;

import com.fakhruddin.wavegram.server.MySqlServerManager;
import com.fakhruddin.wavegram.server.WavegramMessageHandler;
import com.fakhruddin.wavegram.server.WavegramServer;

public class ServerMain {
    private static final String TAG = ServerMain.class.getSimpleName();

    public static void main(String[] args) {
        WavegramServer wavegramServer = new WavegramServer(Config.SERVER_PORT);
        wavegramServer.thisDc = Config.SERVER_DC_ID;
        wavegramServer.setServerManager(new MySqlServerManager());
        wavegramServer.setMessageHandler(new WavegramMessageHandler());
        wavegramServer.start();
    }


}
