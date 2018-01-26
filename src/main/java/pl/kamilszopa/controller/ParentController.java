package pl.kamilszopa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kamilszopa.model.Attendance;
import pl.kamilszopa.model.Parent;
import pl.kamilszopa.model.Student;
import pl.kamilszopa.model.Teacher;
import pl.kamilszopa.model.repository.AttendanceRepository;
import pl.kamilszopa.model.repository.ParentRepository;
import pl.kamilszopa.model.repository.StudentRepository;

@Controller
public class ParentController {

	@Autowired
	private StudentRepository		studentRepository;

	@Autowired
	private AttendanceRepository	attendanceRepository;

	@Autowired
	private ParentRepository		parentRepository;

	@RequestMapping(value = "/parent")
	public String showSearch(ModelMap modelMap) {
		return ("search-student");
	}

	@RequestMapping(value = "/getStudent")
	public String findStudent(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();
		Parent parent = this.parentRepository.findByEmailAdress(principal.getUsername());
		Student student = this.studentRepository.findStudentByParent(parent);
		List<Student> studentList = new ArrayList<>();
		studentList.add(student);
		modelMap.addAttribute("studentList", studentList);
		return "pick-student-parent";
	}
	
	

	@RequestMapping(value = "/parent/grades/{studentId}")
	public String studentGrades(@PathVariable(value = "studentId") Long id, ModelMap modelMap) {
		if (id == null) {
			return "student-grades-parent";
		}
		Student student = this.studentRepository.findOne(id);
		modelMap.addAttribute("student", student);
		return "student-grades-parent";
	}
	
	@RequestMapping(value = "/parent/grades")
	public String studentGradesByParam(@RequestParam(value = "studentId") Long id, ModelMap modelMap) {
		if (id == null) {
			return "student-grades-parent";
		}
		Student student = this.studentRepository.findOne(id);
		modelMap.addAttribute("student", student);
		return "student-grades-parent";
	}



	@RequestMapping("/parent/attendance/{id}")
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
			if ((c.get(Calendar.WEEK_OF_YEAR) == week) && (c.get(Calendar.DAY_OF_WEEK) != 1)
			        && (c.get(Calendar.DAY_OF_WEEK) != 7)) {
				weekDays.add(c.getTime());
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
		modelMap.addAttribute("weekDays", weekDays);
		modelMap.addAttribute("attendances", result);
		modelMap.addAttribute("student", student);
		return "student-attendance";
	}

	/// attendance/1/getPreviousWeek/2018-01-22%2019:08:26.068
	@RequestMapping("/parent/attendance/{id}/getPreviousWeek/{date}")
	public String previousWeekStudentAttendance(@PathVariable Long id, @PathVariable String date, ModelMap modelMap)
	        throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date convertedDate = format.parse(date);

		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		week--;
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
			if ((c.get(Calendar.WEEK_OF_YEAR) == week) && (c.get(Calendar.DAY_OF_WEEK) != 1)
			        && (c.get(Calendar.DAY_OF_WEEK) != 7)) {
				weekDays.add(c.getTime());
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
		modelMap.addAttribute("weekDays", weekDays);
		modelMap.addAttribute("attendances", result);
		modelMap.addAttribute("student", student);
		return "student-attendance";
	}

	@RequestMapping("/parent/attendance/{id}/getNextWeek/{date}")
	public String nextWeekStudentAttendance(@PathVariable Long id, @PathVariable String date, ModelMap modelMap)
	        throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date convertedDate = format.parse(date);

		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		week++;
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
			if ((c.get(Calendar.WEEK_OF_YEAR) == week) && (c.get(Calendar.DAY_OF_WEEK) != 1)
			        && (c.get(Calendar.DAY_OF_WEEK) != 7)) {
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
