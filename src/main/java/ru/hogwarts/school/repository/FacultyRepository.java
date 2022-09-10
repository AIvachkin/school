package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>
//        QuerydslBinderCustomizer<QUser>,
//        QuerydslPredicateExecutor<Faculty>
{

//    @Override
//    default void customize (QuerydslBindings bindings, QUser faculty){
//
//    }

    default Collection<Faculty> findByColor(String color) {
        return findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
