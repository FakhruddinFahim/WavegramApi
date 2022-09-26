package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.core.TLObject;

/**
 * Created by Fakhruddin Fahim on 25/07/2022
 */

public interface OnMessage {
    void object(TLObject object);
}

