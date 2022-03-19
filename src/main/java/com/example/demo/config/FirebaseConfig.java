package com.example.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

	@PostConstruct
	public void init() {
		try {
			final FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "serviceAccountKey.json").openStream()))
					.setConnectTimeout(5000)
					.setReadTimeout(5000)
					.build();
			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
