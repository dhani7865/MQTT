package MQTT;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


import sensors.servo_Motor;

public class motorCallBack implements MqttCallback {
	
	public static final String publisherID = "16027165";
	

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void messageArrived(String name, MqttMessage value) throws Exception 
	{

		servo_Motor.moveMotor();
		
		if (("16027165/LWT").equals(name)) 
        {
            System.err.println("Program is terminated.");
        }
	
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

}
