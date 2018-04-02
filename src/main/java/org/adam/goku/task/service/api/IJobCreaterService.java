/**
 * 
 */
package org.adam.goku.task.service.api;

import java.util.List;

import org.adam.goku.task.chain.income.Job;

/**
 * @author USER
 *
 */
public interface IJobCreaterService {

	public List<Job> createJobs(int masterIndex);

}
