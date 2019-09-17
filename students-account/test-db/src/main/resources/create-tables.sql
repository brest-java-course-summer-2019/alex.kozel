DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;

CREATE TABLE courses (
  course_id INT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(255) NOT NULL UNIQUE,
  course_date DATE NOT NULL,
  PRIMARY KEY (course_id)
);

CREATE TABLE students (
  student_id INT NOT NULL AUTO_INCREMENT,
  student_name VARCHAR(255) NOT NULL,
  course_id INT NOT NULL,
  PRIMARY KEY (student_id),
  FOREIGN KEY (course_id) REFERENCES course(course_id)
);