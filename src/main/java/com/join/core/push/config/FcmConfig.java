package com.join.core.push.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FcmConfig {

	@Value("${fcm.config.file-path}")
	private String fcmKeyPath;

	@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		ClassPathResource resource = new ClassPathResource(fcmKeyPath);
		InputStream refreshToken = resource.getInputStream();

		FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(refreshToken))
			.build();

		FirebaseApp.initializeApp(options);
		log.info("Fcm Setting Completed");
		return FirebaseMessaging.getInstance();
	}

}
