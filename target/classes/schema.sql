CREATE DATABASE student_tracker;

CREATE TABLE student (
student_id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
email VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE instructor_detail (
id BIGSERIAL PRIMARY KEY,
web_page VARCHAR(45) DEFAULT NULL,
hobby VARCHAR(45) DEFAULT NULL
);

CREATE TABLE instructor (
instructor_id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
email VARCHAR(45) NOT NULL UNIQUE,
instructor_detail_id BIGINT REFERENCES instructor_detail (id)
);

CREATE TABLE course (
course_id BIGSERIAL PRIMARY KEY,
title VARCHAR(145) NOT NULL UNIQUE,
instructor_id BIGINT REFERENCES instructor (instructor_id)
);

CREATE TABLE review (
review_id BIGSERIAL PRIMARY KEY,
comment VARCHAR(245) NOT NULL UNIQUE,
course_id BIGINT REFERENCES course (course_id)
);

CREATE TABLE course_student (
course_id BIGINT REFERENCES course (course_id),
student_id BIGINT REFERENCES student (student_id)
);