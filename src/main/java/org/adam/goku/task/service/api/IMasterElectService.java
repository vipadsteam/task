/**
 * 
 */
package org.adam.goku.task.service.api;

import org.adam.goku.task.chain.income.MasterIncome;

/**
 * @author USER
 *
 */
public interface IMasterElectService {

	public boolean isMaster(MasterIncome income);

	public int getMasterLoopTime();

	public int getMasterRandomTime();

}
