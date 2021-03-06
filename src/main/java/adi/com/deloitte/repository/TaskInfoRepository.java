package adi.com.deloitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adi.com.deloitte.model.TaskInfo;


public interface TaskInfoRepository extends JpaRepository<TaskInfo, Long>{
	List<TaskInfo> findByUserName(String user);
}
