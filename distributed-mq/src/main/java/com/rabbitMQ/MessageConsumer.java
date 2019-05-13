package com.rabbitMQ;

import com.google.gson.JsonObject;
import com.model.VO.Hongbao;
import com.model.VO.UserInfoVO;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

//MessageListener �Զ��ظ�
//ChannelAwareMessageListener �ֶ��ظ�
public class MessageConsumer  implements ChannelAwareMessageListener	 {

	private static final Logger logger = Logger.getLogger(MessageConsumer.class);

	public void test(){
		System.out.println("test");
	}

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		byte[] b = message.getBody();
		Object o = Consumer.byteTOobject(b);
		if(o instanceof UserInfoVO){
			UserInfoVO obj = (UserInfoVO)o;
			logger.info("��������һ����Ϣ����Ϣ���ݣ�" + obj.getUsername() +"  "+obj.getPassword());

			//Thread.sleep(10000);
			logger.info("������Ϣ���");
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			logger.info("ack��Ϣ���гɹ�");
		}else if(o instanceof Hongbao){
			Hongbao hb =(Hongbao)o;
			logger.info(hb.getUserId() +"������һ��"+hb.getMoney()+"�ĺ������̨������Ѿ����ˡ�");
		}else if(o instanceof JsonObject){
			JsonObject obj = (JsonObject)o;
			logger.info(obj.get("userId").toString() +"������һ��"+obj.get("money")+"�ĺ������̨������Ѿ����ˡ�");
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}

	}
}
