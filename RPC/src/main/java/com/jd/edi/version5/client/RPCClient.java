package com.jd.edi.version5.client;


import com.jd.edi.version5.common.RPCRequest;
import com.jd.edi.version5.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
