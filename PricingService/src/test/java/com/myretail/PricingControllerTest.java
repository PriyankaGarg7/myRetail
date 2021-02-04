package com.myretail;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.controller.PricingController;
import com.myretail.model.Price;
import com.myretail.repository.PricingRepository;
import com.myretail.service.PricingService;

@EnableWebMvc
@WebAppConfiguration
@WebMvcTest(PricingController.class)
public class PricingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PricingService pricingService;

	@Mock
	private PricingRepository priceRepository;

	@Test
	public void getPrice() throws Exception {

		when(pricingService.getPrice(13860428l)).thenReturn(new Price(13860428l, BigDecimal.valueOf(13.49), "USD"));

		RequestBuilder request = MockMvcRequestBuilders.get("/product/13860428/price/")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{value: 13.49, currencyCode: USD}")).andReturn();

		Mockito.verify(pricingService).getPrice(13860428l);
	}

	@Test
	public void createPrice() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/product/13860428/price/")
				.content(asJsonString(new Price(13860428l, BigDecimal.valueOf(10), "USD")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void updatePrice() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .put("/product/13860428/price/", 2)
	      .content(asJsonString(new Price(13860428l, BigDecimal.valueOf(10), "USD")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
}
