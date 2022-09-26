package com.fakhruddin.mtproto.tl.core;

import com.fakhruddin.mtproto.MTMessage;

import java.util.ArrayList;
import java.util.List;

public class MsgContainer extends TLObject {

    public static final int ID = 0x73f1f8dc;
    public static final boolean IS_CONSTRUCTOR = true;
    public static String NAME = "msg_container";

    public List<MTMessage> messageList = new ArrayList<>();

    public MsgContainer() {

    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public boolean isConstructor() {
        return IS_CONSTRUCTOR;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected void writeParams(TLOutputStream outputStream) throws Exception {
        outputStream.writeInt(messageList.size());
        for (MTMessage message : messageList) {
            message.writeBody(outputStream);
        }
    }

    @Override
    protected void readParams(TLInputStream inputStream) throws Exception {
        int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            MTMessage message = new MTMessage();
            message.readBody(inputStream);
            messageList.add(message);
        }
    }

    public void addMessage(MTMessage message) {
        messageList.add(message);
    }

    @Override
    public String toString() {
        return "MsgContainer{" +
                "messageList=" + messageList +
                '}';
    }
}
