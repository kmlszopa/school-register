package pl.kamilszopa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kamilszopa.model.Attendance;
import pl.kamilszopa.model.SchoolClass;
import pl.kamilszopa.model.Student;
import pl.kamilszopa.model.SubjectGrades;
import pl.kamilszopa.model.Teacher;
import pl.kamilszopa.model.repository.AttendanceRepository;
import pl.kamilszopa.model.repository.SchoolClassRepository;
import pl.kamilszopa.model.repository.StudentRepository;
import pl.kamilszopa.model.repository.SubjectGradesRepository;
import pl.kamilszopa.model.repository.TeacherRepository;

@Controller
public class TeacherController {

	@Autowired
	private SubjectGradesRepository subjectGradesRepository;

	@Autowired
	private SchoolClassRepository schoolClassRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@RequestMapping(value = "/teacher")
	public String showLogin(ModelMap modelMap) {

		return ("login-like-teacher");
	}

	@RequestMapping(value = "/teacherLogin")
	public String searchStudent(@RequestParam(value = "name") String name, ModelMap modelMap) {
		String[] splitedName = name.split(" ");
		String firstName = splitedName[0];
		String surName = splitedName[1];
		Teacher teacher = this.teacherRepository.findBySurNameAndFirstName(surName, firstName);
		SchoolClass schoolClass = this.schoolClassRepository.getSchoolClassByTeacher(teacher);
		List<Student> studentList = this.studentRepository.getStudentsBySchoolClass(schoolClass);
		modelMap.addAttribute("studentList", studentList);
		return "pick-student";
	}

	@RequestMapping(value = "/teacher/grades")
	public String studentGrades(@RequestParam(value = "studentId") Long id, ModelMap modelMap) {
		if (id == null) {
			return "pick-student";
		}
		Student student = this.studentRepository.findOne(id);
		modelMap.addAttribute("student", student);
		return "student-grades-teacher";
	}

	// http://localhost:8080/teacher/grades/1/1?grade=6
	@RequestMapping(value = "/teacher/grades/{studentId}/{subjectGradesId}")
	public String putGrade(@RequestParam(value = "grade") Integer grade, @PathVariable Long studentId,
			@PathVariable Long subjectGradesId, ModelMap modelMap) {
		if (grade == null) {
			return "student-grades-teacher";
		}
		Student student = this.studentRepository.findOne(studentId);
		SubjectGrades subjectGrades = this.subjectGradesRepository.findOne(subjectGradesId);
		subjectGrades.getGrades().add(grade);
		this.subjectGradesRepository.save(subjectGrades);
		modelMap.addAttribute("student", student);
		return "student-grades-teacher";
	}

	@RequestMapping(value = "/teacher/attendance")
	public String studentAttendance(@RequestParam(value = "studentId") Long id, ModelMap modelMap) {
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
		return "student-attendance-teacher";
	}
	
	@RequestMapping("/teacher/attendance/{id}/getPreviousWeek/{date}")
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
		return "student-attendance-teacher";
	}
	
	@RequestMapping("/teacher/attendance/{id}/getNextWeek/{date}")
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
		return "student-attendance-teacher";
	}
	
//	http://localhost:8080/teacher/attendance/change/attendance.id/0
	@RequestMapping("/teacher/attendance/change/{attendanceId}/{attendanceIndex}")
	public String changeAttendance(@PathVariable Long attendanceId, @PathVariable Integer attendanceIndex, ModelMap modelMap) {
		Attendance attendance = this.attendanceRepository.findOne(attendanceId);
		Student student = attendance.getStudent();
		List<Attendance> attendances = this.attendanceRepository.findByStudent(student);
		List<Date> weekDays = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -10);
		c.setTime(attendance.getDay());
		int week = c.get(Calendar.WEEK_OF_YEAR);
		for (int i = 15; i > 0; i--) {
			if ((c.get(Calendar.WEEK_OF_YEAR) == week) && (c.get(Calendar.DAY_OF_WEEK) != 1)
					&& (c.get(Calendar.DAY_OF_WEEK) != 7)) {
				weekDays.add(c.getTime());
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
		List<Attendance> result = new ArrayList<Attendance>();
		for (Attendance a : attendances) {
			c.setTime(a.getDay());
			if (c.get(Calendar.WEEK_OF_YEAR) == week) {
				result.add(a);
			}
		}
		attendance.changeAttendance(attendanceIndex);
		this.attendanceRepository.save(attendance);
		modelMap.addAttribute("weekDays", weekDays);
		modelMap.addAttribute("attendances", result);
		modelMap.addAttribute("student", student);
		return "student-attendance-teacher";
		
	}
}
