/**
 * 
 */
package org.adam.goku.task.run;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.adam.goku.task.chain.income.ITaskQueue;
import org.adam.goku.task.chain.income.Task;
import org.adam.goku.task.service.api.IRequestLogService;
import org.adam.test.goku.task.log.LogService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.adam.common.utils.AdamExceptionUtils;
import org.springframework.adam.common.utils.ThreadLocalHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
@Lazy(false)
public class SlaverIniter implements InitializingBean {

	@Autowired
	private LogService logService;

	@Autowired
	private ITaskQueue taskerQueue;

	@Autowired
	private IRequestLogService requestLogService;

	private void initSlaver() {
		ThreadLocalHolder.setRequestLogFlag(requestLogService.getSlaveLogFlag());
		while (true) {
			List<Task> tasks = taskerQueue.getTasks();
			if (CollectionUtils.isEmpty(tasks)) {
				sleep();
				continue;
			}
			for (Task task : tasks) {
				task.doTask();
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			logService.sendErrorAccountLog("slave job出错：" + AdamExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		SlaverThread slaverThread = new SlaverThread();
		slaverThread.setName("slaver_thread");
		singleThreadPool.execute(slaverThread);
		singleThreadPool.shutdown();
	}

	private class SlaverThread extends Thread {
		@Override
		public void run() {
			try {
				initSlaver();
			} catch (Exception e) {
				logService.sendErrorAccountLog("Master job启动线程出错：" + AdamExceptionUtils.getStackTrace(e));
			}
		}

	}
}
