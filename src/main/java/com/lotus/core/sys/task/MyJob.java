package com.lotus.core.sys.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * Quarz 测试job类
 * @author wyy
 */
@Service
@DisallowConcurrentExecution
public class MyJob implements Job {


    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        System.out.println("=====================Message: Quarz==================");
    }
}