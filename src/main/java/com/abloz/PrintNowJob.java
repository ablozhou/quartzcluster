/**
 * PrintNow.java
 * @Author zhouhh
 */
package com.abloz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 
 * PrintNow
 * @author zhouhh
 * @Date 2017年1月11日
 */
@Component
public class PrintNowJob extends QuartzJobBean
{
    private static final Log log = LogFactory.getLog(PrintNowJob.class);

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
    	log.info("begin to execute task," + DateUtils.dateToString(new Date()));

    	log.info("*  username is " + System.getProperty("user.name"));
    	log.info("*  os name is " + System.getProperty("os.name"));


        log.info("end to execute task," + DateUtils.dateToString(new Date()));

    }
}
