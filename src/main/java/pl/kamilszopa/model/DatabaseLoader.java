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
import pl.kamilszopa.model.repository.RoleRepository;
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
	private final RoleRepository roleRepository;

	@Autowired
	public DatabaseLoader(AttendanceRepository attendanceRepository, SchoolClassRepository schoolClassRepository,
			StudentRepository studentRepository, SubjectRepository subjectRepository,
			TeacherRepository teacherRepository, AdultRepository adultRepository,
			SubjectGradesRepository subjectGradesRepository, RoleRepository roleRepository) {
		super();
		this.schoolClassRepository = schoolClassRepository;
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.teacherRepository = teacherRepository;
		this.adultRepository = adultRepository;
		this.subjectGradesRepository = subjectGradesRepository;
		this.attendanceRepository = attendanceRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		Role teacher = this.roleRepository.save(new Role("TEACHER"));
		Role parent = this.roleRepository.save(new Role("PARENT"));
		
		Subject eduWczesnoszkolna = this.subjectRepository.save(new Subject("Edukacja wczesnoszkolna"));
		Subject wf = this.subjectRepository.save(new Subject("Wychowanie fizyczne"));
		Subject jAngielski = this.subjectRepository.save(new Subject("Język angielski"));
		Subject jNiemiecki = this.subjectRepository.save(new Subject("Język niemiecki"));
		Subject religia = this.subjectRepository.save(new Subject("Religia"));
		Subject zInformatyczne = this.subjectRepository.save(new Subject("Zajęcia informtyczne"));

		Parent romanCzerwinski = this.adultRepository
				.save(new Parent("Roman", "Czerwinski", "RomanCzerwinski@armyspy.com", "password", "53 199 40 32"));
		Parent katarzynaGrabowska = this.adultRepository.save(
				new Parent("Grabowska", "Katarzyna", "KasiaGrabowska@jourrapide.com", "password", "66 129 72 45"));
		romanCzerwinski.getRoles().add(parent);
		katarzynaGrabowska.getRoles().add(parent);
		this.adultRepository.save(katarzynaGrabowska);
		this.adultRepository.save(romanCzerwinski);

		Parent michalinaMajewska = this.adultRepository.save(new Parent("Michalina", "Majewska",
				"MichalinaMajewska@teleworm.us", "password", "53 199 40 32", parent));

		Parent weronikaWysocka = this.adultRepository.save(
				new Parent("Weronika", "Wysocka", "WeronikaWysocka@teleworm.us", "password", "53 199 40 32", parent));

		Parent franciszekDabrowski = this.adultRepository.save(new Parent("Franciszek", "Dąbrowski",
				"FranciszekDabrowski@dayrep.com", "password", "53 199 40 32", parent));

		Parent tytusKaminski = this.adultRepository
				.save(new Parent("Tytus", "Kamiński", "TytusKaminski@teleworm.us", "password", "53 199 40 32", parent));

		Parent marekZajac = this.adultRepository
				.save(new Parent("Marek", "Zając", "MarekZajac@teleworm.us", "password", "53 199 40 32", parent));

		Parent korneliaKowalska = this.adultRepository.save(new Parent("Kornelia", "Kowalska",
				"KorneliaKowalska@jourrapide.com", "password", "53 199 40 32", parent));

		Parent bozenaSobczak = this.adultRepository.save(
				new Parent("Bożena", "Sobczak", "bozenaSobczak@jourrapide.com", "password", "53 199 40 32", parent));

		Parent annaChmielewska = this.adultRepository.save(new Parent("Anna", "Chmielewska",
				"AniaChmielewska@jourrapide.com", "password", "53 199 40 32", parent));

		Parent zygfrydPiotrowski = this.adultRepository.save(new Parent("Zygfryd", "Piotrowski",
				"ZygfrydPiotrowski@jourrapide.com", "password", "53 199 40 32", parent));

		Parent roksanaNowak = this.adultRepository.save(
				new Parent("Roksana", "Nowak", "RoksanaNowak@jourrapide.com", "password", "53 199 40 32", parent));

		Parent sylwiaKaminska = this.adultRepository.save(
				new Parent("Sylwia", "Kaminska", "SylwiaKaminska@dayrep.com", "password", "53 199 40 32", parent));

		Parent jarogniewDudek = this.adultRepository.save(
				new Parent("Jarogniew", "Dudek", "JarogniewDudek@jourrapide.com", "password", "53 199 40 32", parent));

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

		List<SubjectGrades> karolinaMajewskaGrades = new ArrayList<>();
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		karolinaMajewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> ludwikGrabowskiGrades = new ArrayList<>();
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		ludwikGrabowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> nataliaWysockaGrades = new ArrayList<>();
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		nataliaWysockaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> leszekKaminskiGrades = new ArrayList<>();
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		leszekKaminskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> mariuszZajacGrades = new ArrayList<>();
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		mariuszZajacGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> aleksandraKowalskaGrades = new ArrayList<>();
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		aleksandraKowalskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> barbaraSobczakGrades = new ArrayList<>();
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		barbaraSobczakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> joannaChmielewskaGrades = new ArrayList<>();
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		joannaChmielewskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> januszPiotrowskiGrades = new ArrayList<>();
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		januszPiotrowskiGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> damianNowakGrades = new ArrayList<>();
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		damianNowakGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> jolantaKaminskaGrades = new ArrayList<>();
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		jolantaKaminskaGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));

		List<SubjectGrades> przemekDudekGrades = new ArrayList<>();
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(eduWczesnoszkolna)));
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(wf)));
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jAngielski)));
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(jNiemiecki)));
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(religia)));
		przemekDudekGrades.add(this.subjectGradesRepository.save(new SubjectGrades(zInformatyczne)));
		
		Teacher krystynaCzarnecka = this.teacherRepository.save(new Teacher("Czarnecka", "Krystyna",
				"KrysiaCzarnecka@dayrep.com", "password", "53 831 26 68", eduWczesnoszkolna));
		krystynaCzarnecka.getRoles().add(teacher);
		this.teacherRepository.save(krystynaCzarnecka);

		SchoolClass a3 = this.schoolClassRepository.save(new SchoolClass("3A", krystynaCzarnecka));

		Student karolinaMajewska = this.studentRepository.save(new Student("Karolina", "Majewska", a3, michalinaMajewska, karolinaMajewskaGrades));
		generateAttendance(karolinaMajewska);
		Student nataliaWysocka = this.studentRepository.save(new Student("Wysocka", "Natalia", a3, weronikaWysocka, nataliaWysockaGrades));
		generateAttendance(nataliaWysocka);
		Student leszekKaminski = this.studentRepository.save(new Student("Kaminski", "Leszek", a3, tytusKaminski, leszekKaminskiGrades));
		generateAttendance(leszekKaminski);
		Student jacekZajac = this.studentRepository.save(new Student("Zajac", "Jacek", a3, marekZajac, mariuszZajacGrades));
		generateAttendance(jacekZajac);
		Student szopaKamil = this.studentRepository
				.save(new Student("Szopa", "Kamil", a3, romanCzerwinski, szopaKamilGrades));
		generateAttendance(szopaKamil);
		Student ludwikGrabowski = this.studentRepository
				.save(new Student("Grabowski", "Ludwik", a3, katarzynaGrabowska, ludwikGrabowskiGrades));
		generateAttendance(ludwikGrabowski);
		Student zuzannaKowalska = this.studentRepository.save(new Student("Kowalska", "Aleksandra", a3, korneliaKowalska, aleksandraKowalskaGrades));
		generateAttendance(zuzannaKowalska);
		Student barbaraSobczak = this.studentRepository.save(new Student("Sobczak", "Barbara", a3, bozenaSobczak, barbaraSobczakGrades));
		generateAttendance(barbaraSobczak);
		Student joannaChmielewska = this.studentRepository.save(new Student("Chemielewska", "Joanna", a3, annaChmielewska, joannaChmielewskaGrades));
		generateAttendance(joannaChmielewska);
		Student januszPiotrowski = this.studentRepository.save(new Student("Piotrowski", "Janusz", a3, zygfrydPiotrowski, januszPiotrowskiGrades));
		generateAttendance(januszPiotrowski);
		Student damianNowak = this.studentRepository.save(new Student("Nowak", "Damian", a3, roksanaNowak, damianNowakGrades));
		generateAttendance(damianNowak);
		Student jolantaKaminska = this.studentRepository.save(new Student("Kaminska", "Jolanta", a3, sylwiaKaminska, jolantaKaminskaGrades));
		generateAttendance(jolantaKaminska);
		Student przemekDudek = this.studentRepository.save(new Student("Dudek", "Przemysław", a3, jarogniewDudek, przemekDudekGrades));
		generateAttendance(przemekDudek);
		
	}

	private void generateAttendance(Student student) {
		Random randomno = new Random();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -99);
		for (int i = 100; i > 0; i--) {
			Attendance attendance = new Attendance(student, c.getTime());
			if ((c.get(Calendar.DAY_OF_WEEK) != 7) && (c.get(Calendar.DAY_OF_WEEK) != 1)) {
				for (int j = 1; j < randomno.nextInt(4) + 5; j++) {
					Boolean bool;
					if (randomno.nextInt(10) < 2) {
						bool = false;
					} else {
						bool = true;
					}
					attendance.getAttendanceOnClass().add(bool);
				}
				this.attendanceRepository.save(attendance);
			}
			c.add(Calendar.DAY_OF_YEAR, +1);
		}
	}
}
