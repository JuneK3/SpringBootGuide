package com.rootlab.ch8.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

	private final static String KEY = "Z3VpZGVzZWNyZXRrZXk=";
	private final static String ALGORITHM = "PBEWithMD5AndDES";
	private final static String COUNT = "1000";
	private final static String POOL_SIZE = "1";
	private final static String BASE64 = "base64";

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(KEY);
		config.setAlgorithm(ALGORITHM);
		config.setKeyObtentionIterations(COUNT);
		config.setPoolSize(POOL_SIZE);
		config.setStringOutputType(BASE64);

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);
		return encryptor;
	}

}



