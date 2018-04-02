/**
 * 
 */
package org.adam.test.goku.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.adam.goku.task.chain.income.ITaskQueue;
import org.adam.goku.task.chain.income.Job;
import org.adam.goku.task.chain.income.Task;
import org.adam.goku.task.service.api.ITaskCreaterService;
import org.adam.test.goku.task.chain.income.TestTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
public class TaskCreaterService implements ITaskCreaterService {

	@Autowired
	private ITaskQueue taskQueue;
	
	@Override
	public void createTasks(Job job) {
		List<Task> tasks = new ArrayList<Task>();
		Task task = new TestTask();
		tasks.add(task);
		taskQueue.addTasks(tasks);
	}

}
