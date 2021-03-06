package adi.com.deloitte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adi.com.deloitte.model.TaskInfo;
import adi.com.deloitte.repository.TaskInfoRepository;

@Service
public class TaskInfoService{

	@Autowired
	private TaskInfoRepository repository;

	public List<TaskInfo> getTaskInfoByUser(String user) {
		return repository.findByUserName(user);
	}

	
	 public Optional<TaskInfo> getTaskInfoById(long id) { return
	  repository.findById(id); 
	 }
	 

	public void updateTaskInfo(TaskInfo taskInfo) {
		repository.save(taskInfo);
	}

	public void deleteTaskInfo(long id) {
		Optional<TaskInfo> taskInfo = repository.findById(id);
		if (taskInfo.isPresent()) {
			repository.delete(taskInfo.get());
		}
	}

	public TaskInfo saveTaskInfo(TaskInfo taskInfo) {
		return repository.save(taskInfo);
	}
}