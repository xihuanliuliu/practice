package com.jd.edi.socket.websocket;



import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;

/**
 * 用来获取HttpSession对象.
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {


    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 获取httpsession对象
        HttpSession httpSession1 = (HttpSession) request.getHttpSession();
        // 存放httpsession对象
        Map<String, Object> userProperties1 = sec.getUserProperties();
        userProperties1.put(HttpSession.class.getName(), httpSession1);
    }
}
