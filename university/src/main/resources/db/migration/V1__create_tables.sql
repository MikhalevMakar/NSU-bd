DROP TABLE IF EXISTS university.student CASCADE;
DROP TABLE IF EXISTS university.group CASCADE;
DROP TABLE IF EXISTS university.subject CASCADE;
DROP TABLE IF EXISTS university.teacher CASCADE;
DROP TABLE IF EXISTS university.group_student CASCADE;
DROP TABLE IF EXISTS university.degree_education CASCADE;
DROP TABLE IF EXISTS university.schedule CASCADE;
DROP TABLE IF EXISTS university.group_leader CASCADE;

CREATE TABLE IF NOT EXISTS university.group (
    number BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS university.student (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    middle_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    average_score DECIMAL CHECK (average_score >= 0 AND average_score <= 5),
    number_phone VARCHAR(11) CHECK (number_phone ~ '^[0-9]{11}$'),
    number_group BIGINT NOT NULL,
    FOREIGN KEY (number_group) REFERENCES university.group(number) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS university.group_leader (
    group_id BIGINT PRIMARY KEY,
    leader_student_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES university.group(number) ON DELETE CASCADE,
    FOREIGN KEY (leader_student_id) REFERENCES university.student(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS university.subject (
    id BIGSERIAL PRIMARY KEY,
    title varchar(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS university.degree_education  (
    id BIGSERIAL PRIMARY KEY,
    degree VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS university.teacher (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    middle_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    degree_id BIGINT NOT NULL,
    FOREIGN KEY(degree_id) REFERENCES university.degree_education(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS university.teacher_degree (
     teacher_id BIGINT NOT NULL,
     degree_id BIGINT NOT NULL,
     PRIMARY KEY (teacher_id, degree_id),
     FOREIGN KEY (teacher_id) REFERENCES university.teacher(id) ON DELETE CASCADE,
     FOREIGN KEY (degree_id) REFERENCES university.degree_education(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS university.schedule (
    lesson_delivery TIMESTAMP NOT NULL,
    group_id BIGINT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES university.group(number) ON DELETE CASCADE,
    subject_id BIGINT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES university.subject(id) ON DELETE CASCADE,
    teacher_id BIGINT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES university.teacher(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, subject_id, teacher_id)
);