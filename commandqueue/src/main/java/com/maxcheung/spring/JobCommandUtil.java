package com.maxcheung.spring;

import com.maxcheung.domain.JobCommand;

public class JobCommandUtil  {


	public static JobCommand createNextJob(final JobCommand currentJobCommand, final String nextName) {
		JobCommand nextCommand = new JobCommand();
		Long parentId = currentJobCommand.getParentid();
		if (parentId == 0L) {
			parentId = currentJobCommand.getId();
		}
		nextCommand.setParentid(parentId);
		nextCommand.setSourceid(currentJobCommand.getSourceid());
		nextCommand.setName(nextName);
		return nextCommand;
	}


}