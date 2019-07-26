DROP TABLE IF EXISTS course;
CREATE TABLE course (
  course_id INT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (course_id)
);

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  student_id INT NOT NULL AUTO_INCREMENT,
  student_name VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  course_id INT NOT NULL,
  PRIMARY KEY (student_id),
  FOREIGN KEY (course_id) REFERENCES course(course_id)
);