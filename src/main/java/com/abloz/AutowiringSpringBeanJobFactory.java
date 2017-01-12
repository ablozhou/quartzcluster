/**
 * AutowiringSpringBeanJobFactory.java
 * @Author zhouhh
 * 版权所有 (c) 2017. 保留所有权利.
 */
package com.abloz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * AutowiringSpringBeanJobFactory
 * @author zhouhh
 * @Date 2017年1月11日
 */
@Component
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware
{
    private transient AutowireCapableBeanFactory beanFactory;
    private ApplicationContext applicationContext;

  
    public ApplicationContext getApplicationContext() {
    	return this.applicationContext;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
    	this.applicationContext = applicationContext;
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
       
    }


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception
    {
        Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}