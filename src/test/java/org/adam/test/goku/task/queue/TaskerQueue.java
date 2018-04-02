/**
 * 
 */
package org.adam.test.goku.task.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.adam.goku.task.chain.income.ITaskQueue;
import org.adam.goku.task.chain.income.Task;
import org.springframework.stereotype.Component;

/**
 * @author USER
 *
 */
@Component
public class TaskerQueue implements ITaskQueue {

	private static Queue<Task> taskQueue = new LinkedBlockingDeque<Task>();

	public void addTasks(List<Task> tasks) {
		taskQueue.addAll(tasks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.adam.goku.task.chain.income.ITaskerQueue#getTasks()
	 */
	@Override
	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		for (int i = 0; i < 100; i++) {
			Task task = taskQueue.poll();
			if (null == task) {
				break;
			}
			tasks.add(task);
		}
		return tasks;
	}

}
