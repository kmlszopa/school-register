package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.kamilszopa.model.repository.AdultRepository;
import pl.kamilszopa.model.repository.AttendanceRepository;
import pl.kamilszopa.model.repository.SchoolClassRepository;
import pl.kamilszopa.model.repository.StudentRepository;
import pl.kamilszopa.model.repository.SubjectGradesRepository;
import pl.kamilszopa.model.repository.SubjectRepository;
import pl.kamilszopa.model.repository.TeacherRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final SchoolClassRepository schoolClassRepository;
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;
	private final TeacherRepository teacherRepository;
	private final AdultRepository adultRepository;
	private final SubjectGradesRepository subjectGradesRepository;
	private final AttendanceRepository attendanceRepository;

	@Autowired
	public DatabaseLoader(AttendanceRepository attendanceRepository, SchoolClassRepository schoolClassRepository,
			StudentRepository studentRepository, SubjectRepository subjectRepository,
			TeacherRepository teacherRepository, AdultRepository adultRepository,
			SubjectGradesRepository subjectGradesRepository) {
		super();
		this.schoolClassRepository = schoolClassRepository;
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.teacherRepository = teacherRepository;
		this.adultRepository = adultRepository;
		this.subjectGradesRepository = subjectGradesRepository;
		this.attendanceRepository = attendanceRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Subject eduWczesnoszkolna = this.subjectRepository.save(new Subject("Edukacja wczesnoszkolna"));
		Subject wf = this.subjectRepository.save(new Subject("Wychowanie fizyczne"));
		Subject jAngielski = this.subjectRepository.save(new Subject("Język angielski"));
		Subject jNiemiecki = this.subjectRepository.save(new Subject("Język niemiecki"));
		Subject religia = this.subjectRepository.save(new Subject("Religia"));
		Subject zInformatyczne = this.subjectRepository.save(new Subject("Zajęcia informtyczne"));

		Parent romanCzerwinski = this.adultRepository
				.save(new Parent("Roman", "Czerwinski", "RomanCzerwinski@armyspy.com", "password", "53 199 40 32" ));
		Parent katarzynaGrabowska = this.adultRepository
				.save(new Parent("Grabowska", "Katarzyna", "KasiaGrabowska@jourrapide.com", "password", "66 129 72 45"));

		SubjectGrades eduWczesnoszkolnaGrades = this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna));
		SubjectGrades wfGrades = this.subjectGradesRepository.save(new SubjectGrades(wf));
		SubjectGrades jAngielskiGrades = this.subjectGradesRepository.save(new SubjectGrades(jAngielski));
		SubjectGrades jNiemieckiGrades = this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki));
		SubjectGrades religiaGrades = this.subjectGradesRepository.save(new SubjectGrades(religia));
		SubjectGrades zInformatyczneGrades = this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne));

		List<SubjectGrades> szopaKamilGrades = new ArrayList<>();
		szopaKamilGrades.add(eduWczesnoszkolnaGrades);
		szopaKamilGrades.add(wfGrades);
		szopaKamilGrades.add(jAngielskiGrades);
		szopaKamilGrades.add(jNiemieckiGrades);
		szopaKamilGrades.add(religiaGrades);
		szopaKamilGrades.add(zInformatyczneGrades);

		List<SubjectGrades> ludwikGrabowskiGrades = new ArrayList<>();
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		Teacher krystynaCzarnecka = this.teacherRepository.save(
				new Teacher("Czarnecka", "Krystyna", "KrysiaCzarnecka@dayrep.com", "password", "53 831 26 68", eduWczesnoszkolna));
		// Teacher piotrZawadzki = this.teacherRepository
		// .save(new Teacher("Zawadzki", "Piotr", "PiotrZawadzki@rhyta.com", "53 403 34
		// 78", biol));
		// Teacher felicjaKucharska = this.teacherRepository
		// .save(new Teacher("Kucharska", "Felicja", "FelicjaKucharska@armyspy.com", "88
		// 892 97 80", mat));

		SchoolClass a3 = this.schoolClassRepository.save(new SchoolClass("3A", krystynaCzarnecka));

		Student szopaKamil = this.studentRepository
				.save(new Student("Szopa", "Kamil", a3, romanCzerwinski, szopaKamilGrades));
		Student ludwikGrabowski = this.studentRepository
				.save(new Student("Grabowski", "Ludwik", a3, katarzynaGrabowska, ludwikGrabowskiGrades));
		generateAttendance(szopaKamil);
		generateAttendance(ludwikGrabowski);
	}

	private void generateAttendance(Student student) {
		Random randomno = new Random();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -99);
		for (int i = 100; i > 0; i--) {
			Attendance attendance = new Attendance(student, c.getTime());
			if ((c.get(Calendar.DAY_OF_WEEK) != 7) && (c.get(Calendar.DAY_OF_WEEK) != 1)) {
				for (int j = 1; j < 8; j++) {
					Boolean bool = randomno.nextBoolean();
					attendance.getAttendanceOnClass().add(bool);
				}
				this.attendanceRepository.save(attendance);
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
	}
}