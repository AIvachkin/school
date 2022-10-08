select *
from student
where age between 22 AND 39;

select student.name
from student;

select *
from student
where name LIKE '%g%';

select *
from student
where student.age < student.id;

select *
from student
Order By age;

select *
from student;

select s.*, faculty.color
from student as s,
     faculty
where s.faculty_id = faculty.id
  and faculty.id = 2;

select *
from student
order by id desc;

select count(distinct (faculty_id))
from student;

select min(age), max(age), avg(age)
from student;

select faculty_id, count(*)
from student
group by faculty_id;

select faculty_id, min(age), max(age)
from student
group by faculty_id;

select *
from student
order by id
limit 3 offset 3;