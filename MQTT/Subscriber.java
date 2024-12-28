package MQTT;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

	private static final String BROKER_URL = "tcp://iot.eclipse.org:1883";
	
	private static final String publisherID = "16027165";
	private static final String subscriberID = publisherID+ getMacAddress(); //subscriber unique ID.
	
	private MqttClient subscriberClient;
	
	public static void main(String... args) 
    {
        new Subscriber();
    }
	
	public Subscriber()
	{
		try
		{
			subscriberClient = new MqttClient(BROKER_URL, subscriberID);
			start();
			
		}
		catch(MqttException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void start()
	{
		
		
		try
		{
			String name = publisherID +"/test";
			
			subscriberClient = new MqttClient(BROKER_URL, subscriberID);
			subscriberClient.setCallback((MqttCallback) new motorCallBack());
			subscriberClient.connect();
			
			
			
			subscriberClient.subscribe(name);
			
			System.out.println("Now listening to " + name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
    public static String getMacAddress() 
    {
        String Result = "";

        try {
            for (NetworkInterface ni : Collections.list(
                    NetworkInterface.getNetworkInterfaces())) {
                byte[] hardwareAdd = ni.getHardwareAddress();

                if (hardwareAdd != null) {
                    for (int j = 0; j < hardwareAdd.length; j++)
                    	Result += String.format((j == 0 ? "" : "-") + "%02X", hardwareAdd[j]);

                    return Result;
                }
            }

        } catch (SocketException e) {
            System.out.println("Could not find out MAC Adress. Exiting Application ");
            System.exit(1);
        }
        return Result;
    }
	
}
