package adi.com.deloitte.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import adi.com.deloitte.model.TaskInfo;
import adi.com.deloitte.repository.TaskInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskInfoServiceTest {
	
	@Autowired
	private TaskInfoService service;
	
	@MockBean
	private TaskInfoRepository repository;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	String currentDateTime = dateFormat.format(new Date());
	
	private final String user = "admin";

	@Test
	public void getTaskInfoByUserTest() {
		
		List<TaskInfo> tasks = new ArrayList<>();
		tasks.add(new TaskInfo(1, user, "Task one", "Perform some task 1", new Date(), "In Profress", currentDateTime));
		tasks.add(new TaskInfo(2, user, "Task two", "Perform some task 2", new Date(), "Complete", currentDateTime));
		
		Mockito.when(repository.findByUserName(user)).thenReturn(tasks);
		
		assertEquals(2, service.getTaskInfoByUser(user).size());
	}
	
	@Test
	public void saveTaskInfoTest() {
		TaskInfo task = new TaskInfo(1, user, "Task one", "Perform some task 1", new Date(), "In Progress", currentDateTime);
		
		Mockito.when(repository.save(task)).thenReturn(task);
		
		assertThat(service.saveTaskInfo(task)).isEqualTo(task);
	}
	
	@Test
	public void deleteTaskInfoTest() {
		long id = 1L;
		Optional<TaskInfo> taskInfo = Optional.of(new TaskInfo(id, user, "Task one", "Perform some task 1", new Date(), "In Profress", currentDateTime));
		repository.deleteById(id);
		Mockito.when(repository.findById(id)).thenReturn(taskInfo);
		Mockito.when(repository.existsById(id)).thenReturn(false);
		
		assertFalse(repository.existsById(id));
		assertThat(repository.count()).isEqualTo(0L);
	}

}
