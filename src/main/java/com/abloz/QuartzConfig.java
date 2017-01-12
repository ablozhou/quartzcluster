/**
 * QuartzConfig.java
 * @Author zhouhh
 * 版权所有 (c) 2017. 保留所有权利.
 */
package com.abloz;

import java.io.IOException;
import java.util.Properties;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * QuartzConfig
 * @author zhouhh
 * @Date 2017年1月11日
 */
@Configuration
public class QuartzConfig {
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	@Bean(name="quartzProperties")
	public Properties getQuartzProperties() throws IOException{
		Properties properties = PropertiesLoaderUtils.loadAllProperties("quartz.properties");
//		properties.put("org.quartz.dataSource.myDS.URL", dataSourceUrl);
		return properties;
	}
	
	@Bean(name="schedulerFactoryBean")
	public SchedulerFactoryBean getSchedulerFactoryBean() throws IOException{
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");//通过applicationContextSchedulerContextKey属性配置spring上下文
//		schedulerFactoryBean.setStartupDelay(5);//单位秒，启动后延迟5秒启动定时任务
		schedulerFactoryBean.setQuartzProperties(this.getQuartzProperties());//设置quartz配置
		return schedulerFactoryBean;
	}
	
	@Bean(name="jobFactory")
    public JobFactory jobFactory(ApplicationContext applicationContext) {  
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();  
        jobFactory.setApplicationContext(applicationContext);  
        return jobFactory;  
    }  
	
}
