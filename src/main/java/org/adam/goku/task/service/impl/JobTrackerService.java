/**
 * 
 */
package org.adam.goku.task.service.impl;

import java.util.List;

import org.adam.goku.task.chain.income.Job;
import org.adam.goku.task.service.api.IJobTrackerService;
import org.adam.goku.task.service.api.ITaskCreaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
public class JobTrackerService implements IJobTrackerService {

	@Autowired
	private ITaskCreaterService taskCreaterService;

	@Override
	public void trackJobs(List<Job> jobs) {
		for (Job job : jobs) {
			taskCreaterService.createTasks(job);
		}
	}

}
