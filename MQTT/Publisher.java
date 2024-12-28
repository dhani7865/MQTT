package MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Publisher {
	
	private static final String Broker_URL = "tcp://iot.eclipse.org:1883";
	
	private static final String publisherID = "16027165";
	
	//private static final String NAME = publisherID +"/test";

	private static final String TOPIC = null;

	private static final String TOPIC_RFID = null;

	private MqttClient publisherClient;

	public Publisher()
	{
		try
		{
			//Creating Mqtt Client with defined settings for connection.
			publisherClient = new MqttClient(Broker_URL, publisherID+"qwe"); //???
			
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(false); //set to true to solve my problem. set as a clean session everytime.
			options.setWill(publisherClient.getTopic("/LWT"), "Program Terminated".getBytes(), 0, false);
			
			publisherClient.connect(options);
		}
		catch (MqttException e)
		{
			e.getMessage();
			System.exit(1);
		}
	}
	
	public void publishRFID(final String rfidTag) throws MqttException
	{
		final MqttTopic topic = publisherClient.getTopic(TOPIC_RFID);
		topic.publish(new MqttMessage(rfidTag.getBytes()));
		System.out.println("Publising Topic as: " + topic.getName() + " Message = " + rfidTag);
		
	}
	
	public void publishTest(String sensorVal) throws MqttException
	{
		final MqttTopic topic = publisherClient.getTopic(TOPIC);
		topic.publish(new MqttMessage(sensorVal.getBytes()));
		
		System.out.println("Publising Topic as: " + topic.getName() + " Message = " +sensorVal);
		
	}
	
	public void publishGeneric(String sensorName, String sensorVal) throws MqttException
	{
		final MqttTopic topic = publisherClient.getTopic(publisherID +"/"+sensorName);
		topic.publish(new MqttMessage(sensorVal.getBytes()));
		
		System.out.println("Publising Topic as: " + topic.getName() + " Message = " +sensorVal);
		
	}
	
}
