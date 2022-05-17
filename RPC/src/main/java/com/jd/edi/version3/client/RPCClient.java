package com.jd.edi.version3.client;

import com.jd.edi.version3.common.RPCRequest;
import com.jd.edi.version3.common.RPCResponse;

public interface RPCClient {

    //
    RPCResponse sendResponse(RPCRequest request);
}
