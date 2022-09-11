select *from student
where age < 39 AND age >22 ;

select student.name from student;

select *from student
where name LIKE ('%g%');

select *from student
where student.age < student.id;

select *from student
Order By age;