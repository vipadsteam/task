/**
 * 
 */
package org.adam.goku.task.chain.income;

import java.util.List;

/**
 * @author USER
 *
 */
public interface ITaskQueue {

	public void addTasks(List<Task> tasks);

	public List<Task> getTasks();

}
