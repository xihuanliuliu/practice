package com.jd.edi.es.hanlder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ESWriteFailedHandleChain {
    private static Logger logger = LoggerFactory.getLogger(ESWriteFailedHandleChain.class);
//    private static List<IFailPolicy> failPolicyList = new ArrayList<>();
//    private static boolean enable = false;
//
//    static {
//        List<IFailPolicy> ret = ServiceLoaderUtil.loadServices(IFailPolicy.class);
//        if (CollectionUtils.isNotEmpty(ret)) {
//            failPolicyList.addAll(ret);
//        }
//    }
//
//    public static void handle(EsMessage message) {
//        if (!enable || message == null || CollectionUtils.isEmpty(failPolicyList)) {
//            return;
//        }
//
//        for (IFailPolicy failPolicy : failPolicyList) {
//            try {
//                failPolicy.execute(message);
//            } catch (Throwable e) {
//                logger.error("ES写入失败消息处理失败，FailPolicy := {}", failPolicy.getPolicyName(), e);
//            }
//        }
//    }
}
