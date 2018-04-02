package org.adam.test.goku.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.adam.goku.task.chain.income.Job;
import org.adam.goku.task.service.api.IJobCreaterService;
import org.adam.test.goku.task.chain.income.MyJob;
import org.springframework.stereotype.Component;

@Component
public class JobCreaterService implements IJobCreaterService {

	@Override
	public List<Job> createJobs(int masterIndex) {
		System.out.println("creating jobs");
		List<Job> jobs = new ArrayList<Job>();
		Job job = new MyJob();
		jobs.add(job);

		return jobs;
	}

}
