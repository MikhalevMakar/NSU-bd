/* Вывести студентов с фамилией, начинающихся с определенной подстроки */
SELECT * FROM university.student
         WHERE university.student.first_name LIKE 'Mikh%';

/* Вывести список студентов у которых ведёт что-то преподаватель */
SELECT university.student.* from university.student
INNER JOIN university.schedule
ON university.student.number_group=university.schedule.group_id
WHERE university.schedule.teacher_id = 1;

/* Вывести расписание преподавателя */
SELECT
    university.teacher.first_name,
    university.teacher.middle_name,
    university.teacher.last_name,
    university.schedule.lesson_delivery,
    university.subject.title AS subject,
    university.group.number AS number_group
FROM university.schedule
INNER JOIN university.teacher
ON university.teacher.id = university.schedule.teacher_id
INNER JOIN university.group
ON university.group.number = university.schedule.group_id
INNER JOIN university.subject
ON university.subject.id = university.schedule.subject_id
WHERE university.schedule.teacher_id = 1;

/*
    Вывести количество студентов у каждого преподавателя, упорядочив
    преподавателей по убыванию этого количества
*/
SELECT
    university.teacher.id AS TeacherID,
    university.teacher.first_name AS TeacherFirstName,
    university.teacher.last_name AS TeacherLastName,
    COUNT(university.student.id) AS StudentCount
FROM
    university.teacher
LEFT JOIN
    university.schedule
ON
    university.teacher.id = university.schedule.teacher_id
LEFT JOIN
    university.student
ON
    university.schedule.group_id = university.student.number_group
GROUP BY
    university.teacher.id, university.teacher.first_name, university.teacher.last_name
ORDER BY
    StudentCount DESC;


/* подзапросы */
SELECT  university.group.number,
        AVG(university.student.average_score)
FROM university.group
JOIN university.student
ON university.group.number=university.student.number_group
GROUP BY(university.group.number);