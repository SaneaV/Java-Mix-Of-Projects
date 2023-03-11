package com.example.aop;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;

import com.example.aop.facade.api.ParticipantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class AspectJTestSliceApplication {

	private final ParticipantFacade participantFacade;

	public static void main(String[] args) {
		SpringApplication.run(AspectJTestSliceApplication.class, args);
	}

	@Bean
	public void test() {
		participantFacade.getParticipantRepository(DB_PRODUCT);
		participantFacade.getParticipantRepository(API_PRODUCT);
	}
}
