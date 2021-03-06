package adi.com.deloitte.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import adi.com.deloitte.model.TaskInfo;
import adi.com.deloitte.service.TaskInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskInfoControllerTest {

	@Mock
	private TaskInfoService service;

	@InjectMocks
	private TaskInfoController controller;

	@Autowired
	private MockMvc mockMvc;
	
	private final String user = "admin";

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	String currentDateTime = dateFormat.format(new Date());
	
	@Test
	public void ContextLoader() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void withoutSecurityRedirectTestOne() throws Exception{
		mockMvc.perform(get("/list-tasks"))
		.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	public void withoutSecurityRedirectTestTwo() throws Exception{
		mockMvc.perform(get("/add-task"))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	@WithMockUser(username="test", password = "pwd123")
	public void showTasksTest() throws Exception{

		TaskInfo task1 = new TaskInfo(1L, user, "Task 1", "Description 1", new Date(), "In Progress", currentDateTime);
		TaskInfo task2 = new TaskInfo(2L, user, "Task 2", "Description 2", new Date(), "In Progress", currentDateTime);
		
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		list.add(task1);
		list.add(task2);
		
		when(service.getTaskInfoByUser(user)).thenReturn(list);
		
		mockMvc.perform(get("/list-tasks"))
		.andExpect(status().isOk())
		.andExpect(view().name("list-tasks"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/list-tasks.jsp"));
		
		verifyNoMoreInteractions(service);
	}
	
	@Test
	@WithMockUser(username="test", password = "pwd123")
	public void addTaskTest() throws Exception{
		
		mockMvc.perform(get("/add-task"))
		.andExpect(status().isOk())
		.andExpect(view().name("task"))
		.andExpect(forwardedUrl("/WEB-INF/jsp/task.jsp"));
	}

}
