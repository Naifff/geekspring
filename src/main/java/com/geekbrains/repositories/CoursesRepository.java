package com.geekbrains.repositories;

import com.geekbrains.entities.Course;
import com.geekbrains.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {
    @Query(value = "SELECT c.id\n" +
            "FROM courses c\n" +
            "LEFT JOIN students_courses cs on c.id = cs.course_id\n" +
            "GROUP BY c.id\n" +
            "ORDER BY count(distinct cs.student_id) DESC;", nativeQuery = true)
    List<Integer> getCoursesIdsByStudentsCountDesc(PageRequest pageRequest);

    @Query(value = "SELECT c FROM Course c LEFT JOIN c.students s GROUP BY c.id ORDER BY count(s)")
    List<Course> getCoursesByStudentsCountDescHQL(Pageable pageable);
}
