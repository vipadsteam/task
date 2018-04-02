/**
 * 
 */
package org.adam.test.goku.task.service.impl;

import org.adam.goku.task.service.api.IMasterElectService;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
public class MasterElectService implements IMasterElectService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.adam.goku.task.service.api.IMasterElectService#isMaster()
	 */
	@Override
	public boolean isMaster() {
		return true;
	}

	@Override
	public int getMasterLoopTime() {
		return 3000;
	}

	@Override
	public int getMasterRandomTime() {
		// TODO Auto-generated method stub
		return 5000;
	}

}
