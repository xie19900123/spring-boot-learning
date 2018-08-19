package cn.lqdev.learning.springboot.chapter22.config;

import java.text.ParseException;
import java.util.Date;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class QuartzConfig {
	
	/**
	 * 通过工厂类，创建job实例
	 * @return
	 */
	@Bean
	public MethodInvokingJobDetailFactoryBean customJobDetailFactoryBean() {
        
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        //设置执行任务的bean
		jobDetail.setTargetBeanName("quartzTask");
		//设置具体执行的方法
        jobDetail.setTargetMethod("quartzTask");
        //同步执行，上一任务未执行完，下一任务等待
        //true 任务并发执行
        //false 下一个任务必须等待上一任务完成
        jobDetail.setConcurrent(false);
        return jobDetail; 
	}
	
	/**
	 * 通过工厂类创建Trigger
	 * @param jobDetailFactoryBean
	 * @return
	 * @throws ParseException 
	 */
	@Bean(name = "cronTriggerBean")
	public Trigger cronTriggerBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean) throws ParseException {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
		cronTriggerFactoryBean.setCronExpression("0/3 * * * * ?");//每3秒执行一次
		cronTriggerFactoryBean.setName("customCronTrigger");
		cronTriggerFactoryBean.afterPropertiesSet();
		return cronTriggerFactoryBean.getObject();

	}
	
	/**
	 * 调度工厂类,自动注入Trigger
	 * @return
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(Trigger... triggers) {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		
		//也可以直接注入 ApplicationContext,利于 getBeansOfType获取trigger
//		Map<String,Trigger> triggerMap = appContext.getBeansOfType(Trigger.class);
//		if(triggerMap != null) {
//			List<Trigger> triggers = new ArrayList<>(triggerMap.size());
//			//
//			triggerMap.forEach((key,trigger)->{
//				triggers.add(trigger);
//			});
//			bean.setTriggers(triggers.toArray(new Trigger[triggers.size()]));
//		}
		//这里注意 对应的trigger 不能为null 不然会异常的
		bean.setTriggers(triggers);
		return bean;
	} 
	
	@Component("quartzTask")
	public class QuartzTask {
	  
		public void quartzTask() {
			log.info("Quartz定时任务：" + new Date());
		}
	}
}
