package iscas.otcaix.di.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ESUtil {
	
	public static Client getClient() {
		Client client = null;
		try {
			client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			
//			Settings settings = Settings.settingsBuilder().put("cluster.name", "my-healthcare").build();  
//	        client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	
}
