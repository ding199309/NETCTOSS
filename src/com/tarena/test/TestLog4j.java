package com.tarena.test;

import org.apache.log4j.Logger;

public class TestLog4j {
	
	public static void main(String[] args) {
		
		//������־��¼��
		Logger logger=Logger.getLogger(TestLog4j.class);
		
		//��¼��ͬ�������־
		
		logger.debug("���ү");
		logger.info("���ү");
		logger.warn("����ү");
		logger.error("����ү");
		logger.fatal("����ү");
		
		
		
	}

}
