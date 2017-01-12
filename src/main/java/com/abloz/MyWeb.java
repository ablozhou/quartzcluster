/**
 * MyWeb.java
 * @Author zhouhh
 * 版权所有 (c) 2017. 保留所有权利.
 */
package com.abloz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
/**
 * 
 * MyWeb
 * @author zhouhh
 * @Date 2017年1月12日
 */
@RestController
public class MyWeb {
	Logger log = LoggerFactory.getLogger(MyWeb.class);
	
	@Autowired
	AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory;
	public String getOutput() {
		return "<h1>你好,Quartz!</h1><br>";
	}
	
	@RequestMapping("/")
    String home() {
		String names[] = autowiringSpringBeanJobFactory.getApplicationContext().getBeanDefinitionNames();
		
		String out = getOutput()+StringUtils.join(names, "<br>");
		log.info(out);
		return out;
    }
}
