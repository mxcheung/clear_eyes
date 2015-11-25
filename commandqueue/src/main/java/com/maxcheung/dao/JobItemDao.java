package com.maxcheung.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maxcheung.domain.JobItem;

@Service
public class JobItemDao {

	
	public List<JobItem> fetchItems() {
		List<JobItem> items = new ArrayList<JobItem>();
		Long id = 1L;
		items.add(createJobItem("CHECK_CONVERSATION_ID", id++, "conv1"));
		items.add(createJobItem("PROGRAMMER",id++, null));
		items.add(createJobItem("POLITICIAN",id++, null));
		items.add(createJobItem("CHECK_CONVERSATION_ID", id++, "conv2"));
		items.add(createJobItem("DOMESTIC_ENGINEER", id++, null));
		return items;
	}

	private JobItem createJobItem(String status, Long id, Object params) {
		JobItem jobitem = new JobItem();
		jobitem.setId(id);
		jobitem.setStatus(status);
		jobitem.setParams(params);
		return jobitem;
	}
	
	
	
}