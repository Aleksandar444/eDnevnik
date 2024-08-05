package com.example.schoolBE.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	public void Logs() {
		logger.debug("Ovo je DEBUG poruka");
		logger.info("Ovo je INFO poruka");
		logger.warn("Ovo je WARNING poruka");
		logger.error("Ovo je ERROR poruka");
	}
}
