/**
 * 
 */
package org.adam.goku.task.run;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.adam.goku.task.chain.income.MasterIncome;
import org.adam.goku.task.common.ServiceEnumConstants;
import org.adam.goku.task.service.api.IMasterElectService;
import org.adam.goku.task.service.api.IRequestLogService;
import org.springframework.adam.client.ILogService;
import org.springframework.adam.common.bean.ResultVo;
import org.springframework.adam.common.utils.AdamExceptionUtils;
import org.springframework.adam.common.utils.ThreadLocalHolder;
import org.springframework.adam.service.chain.ServiceChain;
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
public class MasterIniter implements InitializingBean {

	@Autowired
	private ILogService logService;

	@Autowired
	private ServiceChain serviceChain;

	@Autowired
	private IMasterElectService masterElectService;

	@Autowired
	private IRequestLogService requestLogService;

	private void initMaster() {
		while (true) {
			try {
				ThreadLocalHolder.setRequestLogFlag(requestLogService.getMasterLogFlag());
				ResultVo<String> result = new ResultVo<String>();
				MasterIncome income = new MasterIncome();
				try {
					serviceChain.doServer(income, result, ServiceEnumConstants.MASTER);
				} catch (Exception e) {
					logService.sendErrorAccountLog("Master job出错：" + AdamExceptionUtils.getStackTrace(e));
				}
				sleep(masterElectService.getMasterLoopTime(), masterElectService.getMasterRandomTime());
			} catch (Exception e) {
				logService.sendErrorAccountLog("master出错：" + AdamExceptionUtils.getStackTrace(e));
				sleep(masterElectService.getMasterLoopTime(), masterElectService.getMasterRandomTime());
			}
		}
	}

	private void sleep(int second, int random) {
		try {
			int sleepTime = second + ThreadLocalRandom.current().nextInt(random);
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			logService.sendErrorAccountLog("sleep出错：" + AdamExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		MasterThread masterThread = new MasterThread();
		masterThread.setName("master_thread");
		singleThreadPool.execute(masterThread);
		singleThreadPool.shutdown();
	}

	private class MasterThread extends Thread {
		@Override
		public void run() {
			try {
				initMaster();
			} catch (Exception e) {
				logService.sendErrorAccountLog("Master job启动线程出错：" + AdamExceptionUtils.getStackTrace(e));
			}
		}

	}
}
