package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		Subject jAngielski = this.subjectRepository.save(new Subject("Język angielski"));
		Subject biol = this.subjectRepository.save(new Subject("Biologia"));
		Subject mat = this.subjectRepository.save(new Subject("Matematyka"));
		Iterable<Subject> findAll = this.subjectRepository.findAll();
		Subject findOne = this.subjectRepository.findOne(1l);
		// Subject plast = this.subjectRepository.save(new Subject("Plastyka"));
		// Subject reli = this.subjectRepository.save(new Subject("Religia"));
		// Subject inf = this.subjectRepository.save(new Subject("Informatyka"));
		// Subject hist = this.subjectRepository.save(new Subject("Historia"));
		// Subject chem = this.subjectRepository.save(new Subject("Chemia"));
		// Subject fiz = this.subjectRepository.save(new Subject("Fizyka"));
		// Subject geo = this.subjectRepository.save(new Subject("Geografia"));
		// Subject wf = this.subjectRepository.save(new Subject("Wychowanie fizyczne"));

		Parent romanCzerwinski = this.adultRepository
				.save(new Parent("Roman", "Czerwinski", "RomanCzerwinski@armyspy.com", "53 199 40 32"));
		Parent katarzynaGrabowska = this.adultRepository
				.save(new Parent("Grabowska", "Katarzyna", "KasiaGrabowska@jourrapide.com", "66 129 72 45"));

		SubjectGrades jAngGrades = this.subjectGradesRepository.save(new SubjectGrades(jAngielski));
		SubjectGrades biolGrades = this.subjectGradesRepository.save(new SubjectGrades(biol));
		SubjectGrades matGrades = this.subjectGradesRepository.save(new SubjectGrades(mat));

		List<SubjectGrades> jolantaCzerwinskaGrades = new ArrayList<>();
		jolantaCzerwinskaGrades.add(jAngGrades);
		jolantaCzerwinskaGrades.add(biolGrades);
		jolantaCzerwinskaGrades.add(matGrades);

		Teacher krystynaCzarnecka = this.teacherRepository
				.save(new Teacher("Czarnecka", "Krystyna", "KrysiaCzarnecka@dayrep.com", "53 831 26 68", jAngielski));
		// Teacher piotrZawadzki = this.teacherRepository
		// .save(new Teacher("Zawadzki", "Piotr", "PiotrZawadzki@rhyta.com", "53 403 34
		// 78", biol));
		// Teacher felicjaKucharska = this.teacherRepository
		// .save(new Teacher("Kucharska", "Felicja", "FelicjaKucharska@armyspy.com", "88
		// 892 97 80", mat));

		SchoolClass a4 = this.schoolClassRepository.save(new SchoolClass("4A", krystynaCzarnecka));

		Student szopaKamil = this.studentRepository
				.save(new Student("Szopa", "Kamil", a4, romanCzerwinski, jolantaCzerwinskaGrades));
		// Student ludwikGrabowski = this.studentRepository
		// .save(new Student("Grabowski", "Ludwik", a4, katarzynaGrabowska,
		// ludwikGrabowskiCzerwinskaGrades));
		generateAttendance(szopaKamil);
	}

	private void generateAttendance(Student student) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -99);
		for (int i = 100; i > 0; i--) {
			Attendance attendance = new Attendance(student, c.getTime());
			if ((c.get(Calendar.DAY_OF_WEEK) != 7) && (c.get(Calendar.DAY_OF_WEEK) != 1)) {
				for (int j = 1; j < 8; j++) {
					attendance.getHours().add(j);
				}
				this.attendanceRepository.save(attendance);
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
	}
}