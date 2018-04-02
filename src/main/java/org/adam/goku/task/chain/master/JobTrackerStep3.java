/**
 * 
 */
package org.adam.goku.task.chain.master;

import org.adam.goku.task.chain.income.MasterIncome;
import org.adam.goku.task.common.ServiceEnumConstants;
import org.adam.goku.task.common.ServiceErrorCodeConstants;
import org.adam.goku.task.service.api.IJobTrackerService;
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
@ServiceOrder(30)
@ServiceErrorCode(ServiceErrorCodeConstants.MASTER_ERROR)
public class JobTrackerStep3 implements IService<MasterIncome, String> {

	private static final Log log = LogFactory.getLog(JobTrackerStep3.class);

	@Autowired
	private IJobTrackerService jobTrackerService;

	@Override
	public AbsCallbacker doService(MasterIncome income, ResultVo<String> output) throws Exception {
		// 处理的jobList
		jobTrackerService.trackJobs(income.getJobs());
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
