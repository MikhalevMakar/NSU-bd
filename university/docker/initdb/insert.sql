/* GROUP */
INSERT INTO university.group (number)
VALUES
    (21211),
    (21212),
    (21213);

/* STUDENT */
INSERT INTO university.student (first_name, middle_name, last_name, average_score, number_phone, number_group)
VALUES
    ('Mikhalev', 'Makar', 'Andreevich', 4.6, '89831264094', 21211),
    ('Efremov', 'Nikita', 'Nikolaevich', 4.7, '89998887766', 21211),
    ('Romanov', 'Oleg', 'Alexandrovich', 4.2, '89261803732', 21211),
    ('Ivanova', 'Maria', 'Sergeevna', 4.8, '89101234567', 21212),
    ('Petrov', 'Nikolai', 'Ivanovich', 3.7, '89087654321', 21213),
    ('Kozlov', 'Igor', 'Sergeevich', 4.5, '89991112233', 21212);

/* GROUP LEADER */
INSERT INTO university.group_leader (group_id, leader_student_id)
VALUES
    (21211, 1),
    (21212, 6),
    (21213, 5);

/* SUBJECT */
INSERT INTO university.subject (title) VALUES ('Математика');
INSERT INTO university.subject (title) VALUES ('Русский язык');
INSERT INTO university.subject (title) VALUES ('Английский язык');
INSERT INTO university.subject (title) VALUES ('Физ-ра');
INSERT INTO university.subject (title) VALUES ('Информатика');

/* DEGREE EDUCATION */
INSERT INTO university.degree_education (degree)
VALUES
    ('Магистр'),
    ('Доктор наук'),
    ('Ассистент'),
    ('Профессор');

/* TEACHER */
INSERT INTO university.teacher (first_name, middle_name, last_name, degree_id)
VALUES
    ('Сидоров', 'Иван', 'Петрович', 1),
    ('Козлова', 'Мария', 'Ивановна', 2),
    ('Иванов', 'Петр', 'Александрович', 3),
    ('Михайлова', 'Анна', 'Сергеевна', 1),
    ('Захаров', 'Алексей', 'Игоревич', 2);

/* SCHEDULE */
INSERT INTO university.schedule (lesson_delivery, group_id, subject_id, teacher_id)
VALUES
    ('2023-10-30', 21211, 1, 1),
    ('2023-11-02', 21211, 2, 2),
    ('2023-11-05', 21211, 3, 3),
    ('2023-11-08', 21212, 4, 4),
    ('2023-11-10', 21212, 5, 5),
    ('2023-11-15', 21212, 1, 1),
    ('2023-11-15', 21213, 1, 3);

/* SELECT */
SELECT * FROM university.student;
SELECT * FROM university.group;
SELECT * FROM university.subject;
SELECT * FROM university.degree_education;
SELECT * FROM university.teacher;
SELECT * FROM university.group_leader;
