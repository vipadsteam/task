/**
 * 
 */
package org.adam.test.goku.task.log;

import org.adam.goku.task.service.api.IRequestLogService;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
public class RequestLogService implements IRequestLogService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.adam.goku.task.service.api.IRequestLogService#getMasterLogFlag()
	 */
	@Override
	public int getMasterLogFlag() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.adam.goku.task.service.api.IRequestLogService#getSlaveLogFlag()
	 */
	@Override
	public int getSlaveLogFlag() {
		return 0;
	}

}
