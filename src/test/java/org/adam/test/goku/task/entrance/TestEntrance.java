/**
 * 
 */
package org.adam.test.goku.task.entrance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author USER
 *
 */
public class TestEntrance {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		while (true) {
			Thread.sleep(10000);
		}
	}

}
