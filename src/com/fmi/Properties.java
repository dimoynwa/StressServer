package com.fmi;

import java.io.BufferedReader;
import java.io.FileReader;

public class Properties {

	public static final int DEF_NUMBER_OF_REQUESTS = 20;
	
	public static final String HOST = "java.voidland.org";
	public static final int PORT = 80;
	
	public static final String REQUEST;
	public static final String RESPONSE;
	
	static {
		try(BufferedReader brreq = new BufferedReader(new FileReader("src/com/fmi/data/example_request.txt"));
				BufferedReader brres = new BufferedReader(new FileReader("src/com/fmi/data/example_expected_response.txt"))) {
			
			StringBuilder request = new StringBuilder();
			String lineReq = brreq.readLine();
			while(lineReq != null) {
				request.append(lineReq + "\n");
				lineReq = brreq.readLine();
			}
			REQUEST = request.toString();
			StringBuilder response = new StringBuilder();
			String lineRes = brres.readLine();
			while(lineRes != null) {
				response.append(lineRes);
				lineRes = brres.readLine();
			}
			RESPONSE = response.toString();
		} catch (Exception e) {
			throw new RuntimeException("Cannot read configuration file");
		}
	}
	
	private Properties() {}
	
}
