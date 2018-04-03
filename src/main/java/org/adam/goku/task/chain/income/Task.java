/**
 * 
 */
package org.adam.goku.task.chain.income;

/**
 * @author USER
 *
 */
public abstract class Task {

	private String taskType;

	public abstract void doTask();

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}
