package com.example.demo.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.Order;
import com.example.demo.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class JdbcOrderRepository{
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order_Tacos");
		this.objectMapper = new ObjectMapper();
	}
	public Order save(Order order) {
		order.setPlaceAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		List<Taco> tacos = order.getTacos();
		for(Taco taco : tacos) {
			saveTacoToOrder(taco, orderId);
		}
		return order;
	}
	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = new HashMap<>();
		values.put("deliveryName", order.getName());
		values.put("deliveryStreet", order.getStreet());
		values.put("deliveryCity", order.getCity());
		values.put("deliveryState", order.getState());
		values.put("deliveryZip", order.getZip());
		values.put("ccNumber", order.getCcNumber());
		values.put("ccExpiration", order.getCcExpiration());
		values.put("ccCVV", order.getCcCVV());
		log.info(order.getPlaceAt().toString());
		values.put("placedAt", new Timestamp(order.getPlaceAt().getTime()));
		long orderId = orderInserter.executeAndReturnKey(values).longValue();
		return orderId;
	}
	private void saveTacoToOrder(Taco taco, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		log.info(taco.getId().toString());
		orderTacoInserter.execute(values);
	}
}
