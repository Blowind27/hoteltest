package aaa.bbb;

import aaa.bbb.PushCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;

/**
 * Title:Server 这是发送消息的服务端
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author Unclue_liu
 */
public class ServerMQTT {

    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://127.0.0.1:1883";
    //定义一个主题
    public static final String TOPIC = "环境状态";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server";

    private MqttClient client;
    private static MqttTopic topic11;
    private String userName = "guest";  //非必须
    private String passWord = "guest";  //非必须

    private static MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic11 = client.getTopic(TOPIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public static void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);

        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }


    public static void sendMessage(int temp,String co2,String id)throws Exception{
        ServerMQTT server = new ServerMQTT();
        server.message = new MqttMessage();
        server.message.setQos(1);  //保证消息能到达一次
        server.message.setRetained(false);
        String str = "temp:" + temp+",co2:" +co2 + ",id:" + id+"" ;//"{\"clieId\":\""+clieId+"\",\"mag\":\""+msg+"\"}";
        server.message.setPayload(str.getBytes());
        try{
            publish(server.topic11 , server.message);
            //断开连接
//            server.client.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws Exception {
        for(int i = 0;i<300;i++){
            Random rd = new Random();
            int temp = rd.nextInt(100);
            String co2 = String.valueOf(rd.nextInt(99)*0.01);
            sendMessage(temp,co2,"001");
            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }

    }
}
