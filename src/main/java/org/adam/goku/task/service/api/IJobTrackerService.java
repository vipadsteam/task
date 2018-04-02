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
public interface IJobTrackerService {

	public void trackJobs(List<Job> jobs);

}
