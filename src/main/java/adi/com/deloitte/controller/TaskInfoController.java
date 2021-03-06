package adi.com.deloitte.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adi.com.deloitte.model.TaskInfo;
import adi.com.deloitte.service.TaskInfoService;

@Controller
public class TaskInfoController {

	@Autowired
	private TaskInfoService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/list-tasks")
	public String showTasks(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("tasks", service.getTaskInfoByUser(name));
		return "list-tasks";
	}

	
	@GetMapping("/delete-task")
	public String deleteTask(@RequestParam long id) {
		service.deleteTaskInfo(id);
		return "redirect:/list-tasks";
	}

	@GetMapping("/update-task")
	public String showUpdateTaskPage(@RequestParam long id, ModelMap model) {
		TaskInfo task = service.getTaskInfoById(id).get();
		model.put("task", task);
		return "task";
	}

	@PostMapping("/update-task")
	public String updateTask(ModelMap model, @Valid TaskInfo task, BindingResult result) {

		if (result.hasErrors()) {
			return "task";
		}

		task.setUserName(getLoggedInUserName(model));
		task.setLastUpdatedDate(assignLastUpdatedDateTime());

		service.updateTaskInfo(task);
		return "redirect:/list-tasks";
		//return "list-tasks";
	}
	

	@GetMapping("/add-task")
	public String showAddTaskPage(ModelMap model) {
		model.addAttribute("task", new TaskInfo());
		return "task";
	}

	@PostMapping("/add-task")
	public String addTask(ModelMap model, @Valid TaskInfo task, BindingResult result) {

		if (result.hasErrors()) {
			return "task";
		}

		task.setUserName(getLoggedInUserName(model));
		task.setLastUpdatedDate(assignLastUpdatedDateTime());

		service.saveTaskInfo(task);
		return "redirect:/list-tasks";
	}

	private String assignLastUpdatedDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(new Date());

	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

}
