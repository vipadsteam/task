/**
 * 
 */
package org.adam.goku.task.chain.master;

import java.util.List;

import org.adam.goku.task.chain.income.Job;
import org.adam.goku.task.chain.income.MasterIncome;
import org.adam.goku.task.common.ServiceEnumConstants;
import org.adam.goku.task.common.ServiceErrorCodeConstants;
import org.adam.goku.task.service.api.IJobCreaterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.adam.common.bean.ResultVo;
import org.springframework.adam.common.bean.annotation.service.ServiceErrorCode;
import org.springframework.adam.common.bean.annotation.service.ServiceOrder;
import org.springframework.adam.common.bean.annotation.service.ServiceType;
import org.springframework.adam.service.AbsCallbacker;
import org.springframework.adam.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author user
 *
 */
@Component
@ServiceType(ServiceEnumConstants.MASTER)
@ServiceOrder(20)
@ServiceErrorCode(ServiceErrorCodeConstants.MASTER_ERROR)
public class JobCreaterStep2 implements IService<MasterIncome, String> {

	private static final Log log = LogFactory.getLog(JobCreaterStep2.class);

	@Autowired
	private IJobCreaterService jobCreaterService;

	@Override
	public AbsCallbacker doService(MasterIncome income, ResultVo<String> output) throws Exception {
		// 去数据库里面获取一下这个master需要处理的job
		List<Job> jobs = jobCreaterService.createJobs(income.getMasterIndex());
		income.setJobs(jobs);
		return null;
	}

	@Override
	public AbsCallbacker doSuccess(MasterIncome income, ResultVo<String> output) throws Exception {
		return null;
	}

	@Override
	public AbsCallbacker doFail(MasterIncome income, ResultVo<String> output) throws Exception {
		return null;
	}

	@Override
	public AbsCallbacker doComplate(MasterIncome income, ResultVo<String> output) throws Exception {
		return null;
	}

}
