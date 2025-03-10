package SpringBoot.College_Management.Assigns.Services;

import SpringBoot.College_Management.Courses.Course_DTO;
import SpringBoot.College_Management.Courses.Course_Entity;
import SpringBoot.College_Management.Courses.Course_Repository;
import SpringBoot.College_Management.Departments.Department_DTO;
import SpringBoot.College_Management.Departments.Department_Entity;
import SpringBoot.College_Management.Departments.Department_Repository;
import SpringBoot.College_Management.Professors.Professor_Entity;
import SpringBoot.College_Management.Professors.Professor_Repository;
//import SpringBoot.College_Management.Semesters.Semester_DTO;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Repository;
//import SpringBoot.College_Management.Semesters.Semester_DTO;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Repository;
//import SpringBoot.College_Management.Semesters.Semester_DTO;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
//import SpringBoot.College_Management.Semesters.Semester_Repository;
//import SpringBoot.College_Management.Semesters.Semester_Entity;
import SpringBoot.College_Management.Students.Student_Entity;
import SpringBoot.College_Management.Students.Student_Repository;
import SpringBoot.College_Management.Subjects.Subject_DTO;
import SpringBoot.College_Management.Subjects.Subject_Entity;
import SpringBoot.College_Management.Subjects.Subject_Repository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Assigning_Services {

    private final ModelMapper modelMapper;
    private final Course_Repository courseRepository;
    private final Student_Repository studentRepository;
    private final Subject_Repository subjectRepository;
//    private final Semester_Repository semesterRepository;
    private final Department_Repository departmentRepository;
    private final Professor_Repository professorRepository;

    //ASSIGNING SUBJECTS TO SEMESTER ____________________________________________________________________________________________________________________________________

//    public Course_DTO assignSubjectsToSemester(String courseName,String semester, String subjectName) {
//
//        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);
//        Optional<Semester_Entity> semesterEntity = semesterRepository.findBySemester(semester);
//        Optional<Subject_Entity> subjectEntity = subjectRepository.findBySubject(subjectName);
//
//        return courseEntity.flatMap(course -> semesterEntity.flatMap(semesters -> subjectEntity.map(
//                subject -> {
//                    subject.setSemester(semesters);
//                    subjectRepository.save(subject);
//                    semesters.getSubjects().add(subject);
//                    courseRepository.save(course);
//
//                    return modelMapper.map(course,Course_DTO.class);
//                }
//        ))).orElse(null);
//    }

    // ASSIGNING STUDENTS TO COURSES_________________________________________________________________________________________________________________________

    public Course_DTO assignCourseToStudents(String courseName,Long year, Long studentId) {

        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);
        Optional<Student_Entity> studentEntity = studentRepository.findById(studentId);

        if (courseRepository.existsByStudents(studentEntity.get())) {
            throw new RuntimeException("Student with id " + studentId + " already exists");
        }

        return courseEntity.flatMap(course -> studentEntity.map(
                student -> {
                    student.setCourse(course);
                    studentRepository.save(student);
                    course.getStudents().add(student);
                    courseRepository.save(course);
                    return modelMapper.map(course, Course_DTO.class);
                }
        )).orElse(null);
    }


    // ASSIGNING SEMESTERS TO COURSES _________________________________________________________________________________________________________________________

//    public Course_DTO assignSemestersToCourse(String courseName, String semester) {
//
//        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);
//        Optional<Semester_Entity> semesterEntity = semesterRepository.findBySemester(semester);
//
//        return courseEntity.flatMap(course -> semesterEntity.map(
//                semesters -> {
//                    semesters.getCourses().add(course);
//                    semesterRepository.save(semesters);
//                    course.getSemesters().add(semesters);
//                    courseRepository.save(course);
//                    return modelMapper.map(course, Course_DTO.class);
//                }
//        )).orElse(null);
//    }


    // ASSIGNING COURSES TO DEPARTMENT---------------------------------------------------------------------------------------------------------------------------------

    public Department_DTO assignCoursesToDepartment(String departmentName, String courseName) {
        Optional<Department_Entity> departmentEntity = departmentRepository.findByDepartment(departmentName);
        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);


        if (departmentRepository.existsByCourse(courseEntity.get())) {
            throw new RuntimeException("Course with name " + courseName + " is already exists in other department");
        }


        return departmentEntity.flatMap(department -> courseEntity.map(
                course -> {
                    course.setDepartment(department);
                    courseRepository.save(course);
                    department.getCourse().add(course);

                    return modelMapper.map(department, Department_DTO.class);
                }
        )).orElse(null);
    }

    //ASSIGNING PROFESSORS TO SUBJECTS ____________________________________________________________________________________________________________________________________

    public Subject_DTO assignProfessorsToSubjects(String subjectName, Long professorId, String professorName) {
        Optional<Professor_Entity> professorEntity = professorRepository.findByProfessorIdAndName(professorId, professorName);
        Optional<Subject_Entity> subjectEntity = subjectRepository.findBySubject(subjectName);

        return subjectEntity.flatMap(subject -> professorEntity.map(
                professor -> {
                    professor.getSubjects().add(subject);
                    professorRepository.save(professor);
                    subject.getProfessors().add(professor);
                    subjectRepository.save(subject);
                    return modelMapper.map(subject, Subject_DTO.class);
                }
        )).orElse(null);
    }



    //ASSIGNING SUBJECTS TO COURSES ____________________________________________________________________________________________________________________________________

    public Course_DTO assignSubjectsToCourses(String courseName, String subjectName) {
        Optional<Course_Entity> courseEntity = courseRepository.findByCourse(courseName);
        Optional<Subject_Entity> subjectEntity = subjectRepository.findBySubject(subjectName);

        return courseEntity.flatMap(course -> subjectEntity.map(
                subject -> {
                    subject.setCourse(course);
                    subjectRepository.save(subject);
                    course.getSubjects().add(subject);

                    return modelMapper.map(course,Course_DTO.class);
                }
        )).orElse(null);
    }
}
