package com.jd.edi.version4.client;


import com.jd.edi.version4.common.RPCRequest;
import com.jd.edi.version4.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
