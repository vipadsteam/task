/**
 * 
 */
package org.adam.goku.task.chain.income;

import java.io.Serializable;

/**
 * @author USER
 *
 */
public abstract class Task implements Serializable {

	private static final long serialVersionUID = 5374999227696429085L;

	private String taskType;

	public abstract void doTask();

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}
