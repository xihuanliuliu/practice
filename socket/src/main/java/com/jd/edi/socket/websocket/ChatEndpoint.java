package com.jd.edi.socket.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.edi.socket.entity.Message;
import com.jd.edi.socket.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class ChatEndpoint {

    /**
     * 存储登录的用户
     */
    public static Map<String, ChatEndpoint> onlineUser = new ConcurrentHashMap<>();

    /**
     * 用来给客户端发送消息
     */
    private Session session;

    /**
     * 用来获取在登录成功后，放在httpsession域中存放的username
     */
    private HttpSession httpSession;


    /**
     * @param session
     * @param endpointConfig
     * @Onopen注解表示建立链接时调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        log.info("建立连接时调用");
        this.session = session;
        // 获取httpsession对象
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        // 获取httpsession对象存放的user值
        String username = (String) httpSession.getAttribute("username");
        // 存放username
        onlineUser.put(username, this);
        // 推送消息给所有在线的用户--广播，某某用户上线
        // 封装系统推送消息，前端onmessage接收到的数据
        String message = MessageUtils.getMessage(true, null, getAllOnlineUsername());
        broadcastMsgToAllOnlineUsers(message);
    }

    private Set<String> getAllOnlineUsername() {
        Set<String> strings = onlineUser.keySet();
        return strings;
    }

    private void broadcastMsgToAllOnlineUsers(String message) {
        for (String username : onlineUser.keySet()) {
            ChatEndpoint chatEndpoint = onlineUser.get(username);
            // 获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                log.error("on open send message error: {}", e.getMessage());
            }
        }
    }

    /**
     * 接收到客户端发送的数据时调用.
     * @param message 客户端发送的数据
     * @param session session对象
     * @return void
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            // 接收该信息的用户
            String toName = msg.getToName();
            // 信息内容
            String data = msg.getMessage();
            // 当前登录的用户
            String username = (String) httpSession.getAttribute("username");
            // 封装发送的消息
            String sendMsg = MessageUtils.getMessage(false, username, data);
            onlineUser.get(username).session.getBasicRemote().sendText(sendMsg);
        } catch (JsonProcessingException ex) {
            log.error("message convert error: {}", ex.getMessage());
        } catch (IOException e) {
            log.error("on message send message error: {}", e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        String username = (String) httpSession.getAttribute("username");
        // 删除某个用户--某个用户下线了
        onlineUser.remove(username);
        // 封装消息
        String message = MessageUtils.getMessage(true, null, getAllOnlineUsername());
        // 广播
        broadcastMsgToAllOnlineUsers(message);
    }

}
