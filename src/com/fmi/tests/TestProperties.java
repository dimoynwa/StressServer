package com.fmi.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fmi.Properties;

public class TestProperties {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRequest() {
		String exampleReq = "GET http://java.voidland.org/ HTTP/1.1\n" + "Host: java.voidland.org\n\n" ;
		assertEquals(exampleReq, Properties.REQUEST);
	}
	
	@Test
	public void testResponse() {
		String exampleRes = "HTTP/1.1 200 OK";
		assertEquals(exampleRes, Properties.RESPONSE);
	}
	
	@Test
	public void testHostAndPort() {
		String expHost = "java.voidland.org";
		assertEquals(expHost, Properties.HOST);
		int expPort = 80;
		assertEquals(expPort, Properties.PORT);
	}

}
