CREATE SCHEMA IF NOT EXISTS university AUTHORIZATION back;

DROP TABLE IF EXISTS university.group_number CASCADE;
DROP TABLE IF EXISTS university.student CASCADE;
DROP TABLE IF EXISTS university.group_leader CASCADE;
DROP TABLE IF EXISTS university.subject CASCADE;
DROP TABLE IF EXISTS university.degree_education CASCADE;
DROP TABLE IF EXISTS university.teacher CASCADE;
DROP TABLE IF EXISTS university.teacher_degree CASCADE;
DROP TABLE IF EXISTS university.schedule CASCADE;

CREATE TABLE IF NOT EXISTS university.group_number (
    number BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS university.student (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    middle_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    average_score NUMERIC(38,2) CHECK (average_score >= 0 AND average_score <= 5),
    number_phone VARCHAR(11) CHECK (number_phone ~ '^[0-9]{11}$'),
    group_number BIGINT NOT NULL,
    UNIQUE (first_name, middle_name, last_name),
    FOREIGN KEY (group_number) REFERENCES university.group_number(number) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS university.group_leader (
    group_id BIGINT PRIMARY KEY,
    leader_student_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES university.group_number(number) ON DELETE CASCADE,
    FOREIGN KEY (leader_student_id) REFERENCES university.student(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS university.subject (
    id    BIGSERIAL    PRIMARY KEY,
    title VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS university.degree_education (
    id BIGSERIAL PRIMARY KEY,
    degree VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS university.teacher (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    middle_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    UNIQUE (first_name, middle_name, last_name)
);

CREATE TABLE IF NOT EXISTS university.teacher_degree (
     teacher_id BIGINT NOT NULL,
     degree_id BIGINT NOT NULL,
     PRIMARY KEY (teacher_id, degree_id),
     FOREIGN KEY (teacher_id) REFERENCES university.teacher(id) ON DELETE RESTRICT,
     FOREIGN KEY (degree_id)  REFERENCES university.degree_education(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS university.schedule (
    lesson_delivery TIMESTAMP NOT NULL,
    group_id BIGINT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES university.group_number(number) ON DELETE CASCADE,
    subject_id BIGINT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES university.subject(id) ON DELETE CASCADE,
    teacher_id BIGINT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES university.teacher(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, subject_id, teacher_id)
);

CREATE TABLE IF NOT EXISTS university.schedule_subject (
    schedule_id_group BIGINT NOT NULL,
    schedule_id_subject BIGINT NOT NULL,
    schedule_id_teacher BIGINT NOT NULL,
    fk_subject_id BIGINT NOT NULL,
    PRIMARY KEY (schedule_id_group, schedule_id_subject, schedule_id_teacher, fk_subject_id)
);