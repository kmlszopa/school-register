package pl.kamilszopa.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kamilszopa.model.Attendance;
import pl.kamilszopa.model.Student;
import pl.kamilszopa.model.repository.AttendanceRepository;
import pl.kamilszopa.model.repository.StudentRepository;

@Controller
public class ParentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@RequestMapping(value = "/parent")
	public String showSearch(ModelMap modelMap) {
		return ("search-student");
	}

	@RequestMapping(value = "/findStudent")
	public String findStudent(@RequestParam(value = "name") String name, ModelMap modelMap) {
		String[] splitedName = name.split(" ");
		String firstName = splitedName[0];
		String surName = splitedName[1];
		Student student = this.studentRepository.findBySurNameAndFirstName(surName, firstName);
		modelMap.addAttribute("student", student);
		return "student-grades";
	}

	@RequestMapping("/grades/{name}")
	public String studentGrades(@PathVariable String name, ModelMap modelMap) {
		String[] splitedName = name.split("\\+");
		String firstName = splitedName[0];
		String surName = splitedName[1];
		Student student = this.studentRepository.findBySurNameAndFirstName(surName, firstName);
		modelMap.addAttribute("student", student);
		return "student-grades";
	}

	@RequestMapping("/attendance/{id}")
	public String studentAttendance(@PathVariable Long id, ModelMap modelMap) {
		Calendar c = Calendar.getInstance();
		int week = c.get(Calendar.WEEK_OF_YEAR);
		Student student = this.studentRepository.findOne(id);
		List<Attendance> attendances = this.attendanceRepository.findByStudent(student);
		List<Attendance> result = new ArrayList<Attendance>();
		for (Attendance attendance : attendances) {
			c.setTime(attendance.getDay());
			if (c.get(Calendar.WEEK_OF_YEAR) == week) {
				result.add(attendance);
			}
		}
		List<Date> weekDays = new ArrayList<>();
		c.add(Calendar.DAY_OF_YEAR, -10);
		for (int i = 15; i > 0; i--) {
			if((c.get(Calendar.WEEK_OF_YEAR) == week) && (c.get(Calendar.DAY_OF_WEEK) != 1) && (c.get(Calendar.DAY_OF_WEEK) != 7)) {
				weekDays.add(c.getTime());
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
		modelMap.addAttribute("weekDays", weekDays);
		modelMap.addAttribute("attendances", result);
		modelMap.addAttribute("student", student);
		return "student-attendance";
	}

}
