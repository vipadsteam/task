/**
 * 
 */
package org.adam.goku.task.chain.income;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 *
 */
public class MasterIncome {

	private int masterIndex = 0;

	private List<Job> jobs = new ArrayList<Job>();

	public int getMasterIndex() {
		return masterIndex;
	}

	public void setMasterIndex(int masterIndex) {
		this.masterIndex = masterIndex;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
