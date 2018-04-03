package org.adam.test.goku.task.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.adam.client.ILogService;
import org.springframework.adam.common.bean.ResultVo;
import org.springframework.adam.common.utils.ThreadLocalHolder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class LogService implements ILogService {

	private static final Log log = LogFactory.getLog(LogService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.adam.client.sevendays.ILogService#
	 * sendRunningAccountLog(java.lang.Object)
	 */
	@Override
	public void sendRunningAccountLog(Object obj) {
		if (!isNeedInfoLog()) {
			return;
		}
		log.info("running_account:" + ra() + JSON.toJSONString(obj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.adam.client.sevendays.ILogService#
	 * sendRunningAccountLog(java.lang.Object,
	 * org.springframework.adam.common.bean.ResultVo, java.lang.String,
	 * java.lang.String, java.lang.Long)
	 */
	@Override
	public void sendRunningAccountLog(Object income, ResultVo output, String methodName, String remark, Long beginTime) {
		if (!isNeedInfoLog()) {
			return;
		}
		if (beginTime == null) {
			log.info("running_account: " + ra() + getFormatParamString(income, output, methodName, remark));
			return;
		}
		Long endTime = System.currentTimeMillis();
		log.info("running_account: " + ra() + getFormatParamString(income, output, methodName, remark + " useTime:" + (endTime - beginTime)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.client.sevendays.ILogService#sendRequestLog(java
	 * .lang.Object)
	 */
	@Override
	public void sendRequestLog(Object obj) {
		if (!isNeedInfoLog()) {
			return;
		}
		log.info("request_log: " + ra() + JSON.toJSONString(obj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.client.sevendays.ILogService#sendBeginRequestLog
	 * (java.lang.Object)
	 */
	@Override
	public void sendBeginRequestLog(Object obj) {
		if (!isNeedInfoLog()) {
			return;
		}
		log.info("request_begin_log: " + ra() + JSON.toJSONString(obj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.client.sevendays.ILogService#sendEndRequestLog(
	 * java.lang.Object)
	 */
	@Override
	public void sendEndRequestLog(Object obj) {
		if (!isNeedInfoLog()) {
			return;
		}
		log.info("request_end_log: " + ra() + JSON.toJSONString(obj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.client.sevendays.ILogService#sendErrorAccountLog
	 * (java.lang.Object)
	 */
	@Override
	public void sendErrorAccountLog(Object obj) {
		if (!isNeedErrorLog()) {
			return;
		}
		log.error("error_account_log: " + ra() + JSON.toJSONString(obj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.adam.client.sevendays.ILogService#sendErrorAccountLog
	 * (java.lang.Object, java.lang.Object, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendErrorAccountLog(Object income, Object output, String methodName, String type, String remark) {
		if (!isNeedErrorLog()) {
			return;
		}
		log.error("error_account_log: " + ra() + getFormatParamString(income, output, methodName, remark));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.adam.client.sevendays.ILogService#
	 * sendBussinessErrorAccountLog(java.lang.Object, java.lang.Object,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendBussinessErrorAccountLog(Object income, Object output, String methodName, String remark) {
		if (!isNeedErrorLog()) {
			return;
		}
		log.error("bussiness_error_account_log: " + ra() + getFormatParamString(income, output, methodName, remark));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.adam.client.sevendays.ILogService#
	 * sendOverTimeAccountLog(java.lang.Object, java.lang.Object,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendOverTimeAccountLog(Object income, Object output, String methodName, String remark) {
		if (!isNeedErrorLog()) {
			return;
		}
		log.error("overtime_error_account_log: " + ra() + getFormatParamString(income, output, methodName, remark));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.adam.client.sevendays.ILogService#
	 * sendTechnologyErrorAccountLog(java.lang.Object, java.lang.Object,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendTechnologyErrorAccountLog(Object income, Object output, String methodName, String remark) {
		if (!isNeedErrorLog()) {
			return;
		}
		log.error("technology_error_account_log: " + ra() + getFormatParamString(income, output, methodName, remark));
	}

	@Override
	public boolean isNeedLog() {
		return isNeedInfoLog();
	}

	/**
	 * 0,1 表示不需要log，2以上表示需要
	 * 
	 * @return
	 */
	private boolean isNeedInfoLog() {
		if (null == ThreadLocalHolder.getRequestLogFlag()) {
			return false;
		}
		return ThreadLocalHolder.getRequestLogFlag() >= 2;
	}

	/**
	 * 0 表示不需要log，1以上表示需要
	 * 
	 * @return
	 */
	private boolean isNeedErrorLog() {
		if (null == ThreadLocalHolder.getRequestLogFlag()) {
			return false;
		}
		return ThreadLocalHolder.getRequestLogFlag() >= 1;
	}

	/**
	 * @return
	 */
	private String ra() {
		String runningAccount = ThreadLocalHolder.getRunningAccount();
		return "ra:" + runningAccount + ", ";
	}

	/**
	 * @param income
	 * @param output
	 * @param methodName
	 * @param remark
	 * @return
	 */
	private String getFormatParamString(Object income, Object output, String methodName, String remark) {

		String tmpStr = "///methodName: " + methodName + "///remark: " + remark;

		return tmpStr + "///income: " + JSON.toJSONString(income) + "///output: " + JSON.toJSONString(output);
	}
}
