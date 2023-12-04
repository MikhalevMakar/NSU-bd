CREATE SCHEMA IF NOT EXISTS university AUTHORIZATION back;

DROP TABLE IF EXISTS university.group_number CASCADE;
DROP TABLE IF EXISTS university.student CASCADE;
DROP TABLE IF EXISTS university.group_leader CASCADE;
DROP TABLE IF EXISTS university.subject CASCADE;
DROP TABLE IF EXISTS university.degree_education CASCADE;
DROP TABLE IF EXISTS university.teacher CASCADE;
DROP TABLE IF EXISTS university.teacher_degree CASCADE;
DROP TABLE IF EXISTS university.schedule CASCADE;
DROP TABLE IF EXISTS university.grade CASCADE;

CREATE TABLE IF NOT EXISTS university.group_number (
    number BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS university.student (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    middle_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    number_phone VARCHAR(11) CHECK (number_phone ~ '^[0-9]{11}$'),
    average_score DECIMAL(38, 2),
    group_number BIGINT NOT NULL,
    UNIQUE (first_name, middle_name, last_name),
    FOREIGN KEY (group_number) REFERENCES university.group_number(number) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS university.subject (
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS university.grade (
  student_id BIGINT NOT NULL,
  subject_id BIGINT NOT NULL,
  score DECIMAL(38, 2) CHECK (score >= 0 AND score <= 5),
  FOREIGN KEY (student_id) REFERENCES university.student,
  FOREIGN KEY (subject_id) REFERENCES university.subject,
  PRIMARY KEY (student_id, subject_id)
);

CREATE OR REPLACE FUNCTION university.average_score()
RETURNS trigger AS $$
BEGIN
    UPDATE university.student
    SET average_score = (SELECT AVG(score)
                         FROM university.grade AS grade
                         WHERE grade.student_id = NEW.id
                         );
    return NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER "update_average_score_on_insert_grade"
AFTER INSERT ON university.student
FOR EACH ROW
EXECUTE PROCEDURE university.average_score();

CREATE TABLE IF NOT EXISTS university.group_leader (
    group_id BIGINT PRIMARY KEY,
    leader_student_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES university.group_number(number) ON DELETE CASCADE,
    FOREIGN KEY (leader_student_id) REFERENCES university.student(id) ON DELETE CASCADE
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
    id BIGSERIAL PRIMARY KEY,
    lesson_delivery TIMESTAMP NOT NULL,
    subject_id BIGINT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES university.subject(id) ON DELETE CASCADE,
    teacher_id BIGINT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES university.teacher(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS university.schedule_subject (
    schedule_id_group BIGINT NOT NULL,
    schedule_id_subject BIGINT NOT NULL,
    schedule_id_teacher BIGINT NOT NULL,
    fk_subject_id BIGINT NOT NULL,
    PRIMARY KEY (schedule_id_group, schedule_id_subject, schedule_id_teacher, fk_subject_id)
);

alter table if exists university.group_number add column schedule_id bigint;

alter table if exists university.group_number
    add constraint FK5b2yt7u607uahfdbqtque4y9v
    foreign key (schedule_id) references university.schedule;
