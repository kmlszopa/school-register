package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.kamilszopa.model.repository.AdultRepository;
import pl.kamilszopa.model.repository.NoteRepository;
import pl.kamilszopa.model.repository.SchoolClassRepository;
import pl.kamilszopa.model.repository.StudentRepository;
import pl.kamilszopa.model.repository.SubjectGradesRepository;
import pl.kamilszopa.model.repository.SubjectRepository;
import pl.kamilszopa.model.repository.TeacherRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final NoteRepository noteRepository;
	private final SchoolClassRepository schoolClassRepository;
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;
	private final TeacherRepository teacherRepository;
	private final AdultRepository adultRepository;
	private final SubjectGradesRepository subjectGradesRepository;

	@Autowired
	public DatabaseLoader(NoteRepository noteRepository, SchoolClassRepository schoolClassRepository,
			StudentRepository studentRepository, SubjectRepository subjectRepository,
			TeacherRepository teacherRepository, AdultRepository adultRepository,
			SubjectGradesRepository subjectGradesRepository) {
		super();
		this.noteRepository = noteRepository;
		this.schoolClassRepository = schoolClassRepository;
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.teacherRepository = teacherRepository;
		this.adultRepository = adultRepository;
		this.subjectGradesRepository = subjectGradesRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Subject jAngielski = this.subjectRepository.save(new Subject("Język angielski"));
		Subject biol = this.subjectRepository.save(new Subject("Biologia"));
		Subject mat = this.subjectRepository.save(new Subject("Matematyka"));
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

		SubjectGrades jAngGrades = this.subjectGradesRepository.save(new SubjectGrades(jAngielski));
		SubjectGrades biolGrades = this.subjectGradesRepository.save(new SubjectGrades(biol));
		SubjectGrades matGrades = this.subjectGradesRepository.save(new SubjectGrades(mat));

		List<SubjectGrades> subjectGrades = new ArrayList<>();

		subjectGrades.add(jAngGrades);
		subjectGrades.add(biolGrades);
		subjectGrades.add(matGrades);

		Teacher krystynaCzarnecka = this.teacherRepository
				.save(new Teacher("Czarnecka", "Krystyna", "KrysiaCzarnecka@dayrep.com", "53 831 26 68", jAngielski));
//		Teacher piotrZawadzki = this.teacherRepository
//				.save(new Teacher("Zawadzki", "Piotr", "PiotrZawadzki@rhyta.com", "53 403 34 78", biol));
//		Teacher felicjaKucharska = this.teacherRepository
//				.save(new Teacher("Kucharska", "Felicja", "FelicjaKucharska@armyspy.com", "88 892 97 80", mat));

		SchoolClass a4 = this.schoolClassRepository.save(new SchoolClass("4A", krystynaCzarnecka));
		Student jolantaCzerwinska = this.studentRepository
				.save(new Student("Czerwinska", "Jolanta", a4, romanCzerwinski, subjectGrades));

	}

}