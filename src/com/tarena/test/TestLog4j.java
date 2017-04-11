package com.tarena.test;

import org.apache.log4j.Logger;

public class TestLog4j {
	
	public static void main(String[] args) {
		
		//创建日志记录器
		Logger logger=Logger.getLogger(TestLog4j.class);
		
		//记录不同级别的日志
		
		logger.debug("你大爷");
		logger.info("你二爷");
		logger.warn("你三爷");
		logger.error("你四爷");
		logger.fatal("你五爷");
		
		
		
	}

}
