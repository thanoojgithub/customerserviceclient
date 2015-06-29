package com.customerserviceclient;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.customerservice.bean.Customer;

@Component
@Qualifier("customerServiceClient")
public class CustomerServiceClient {

	@Autowired
	@Qualifier("customerservice")
	private org.apache.cxf.jaxrs.client.WebClient webClient;

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceClient.class);

	public Customer getCustomer(final Integer id) {
		Customer customer = null;
		webClient.reset();
		webClient.path("/getCustomer/" + id);
		Response response = webClient.get();
		if (response.getStatus() == 200) {
			customer = response.readEntity(Customer.class);
			LOGGER.info("getCustomer :: " + customer);
		}else{
			LOGGER.warn("getCustomer :: " + response.getStatus());
		}
		return customer;
	}
	
	public Customer createCustomer(Integer id, String name, String location) {
		Customer customer = null;
		webClient.reset();
		webClient.path("/createCustomer/"+ id+"/"+name+"/"+location);
		Response response = webClient.get();
		if (response.getStatus() == 200) {
			customer = response.readEntity(Customer.class);
			LOGGER.info("createCustomer :: " + customer);
		}else{
			LOGGER.warn("createCustomer :: " + response.getStatus());
		}
		return customer;
	}
}
