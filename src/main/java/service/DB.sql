use project_knk;

create table Students(
std_id integer primary key auto_increment,
std_name varchar(50) not null,
std_lastName varchar(50) not null,
email varchar(100) not  null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
school_id integer not null,
major_id integer not null,
level_id integer not null,
gender char not null,
birthday date not null,
foreign key(major_id) references majors(major_id),
foreign key(school_id) references School(school_id),
foreign key(level_id) references Grade_level(level_id),
foreign key(address_id) references Address(address_id)
);
select * from teachers;

create table Teachers(
t_id integer primary key auto_increment,
t_name  varchar(50) not null,
t_lastName varchar(50) not null,
email varchar(100) not  null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
title varchar(100) not null,
education varchar(500) not null,
school_id integer not null,
subject_id integer not null,
gender char not null,
birthday date not null,
foreign key(address_id) references Address(address_id),
foreign key(school_id) references School(school_id),
foreign key(subject_id) references subjects(subject_id)

);

create table Administrator(
a_id integer primary key auto_increment,
a_name varchar(50) not null,
a_lastName varchar(50) not null,
email varchar(100) not  null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
foreign key(address_id) references Address(address_id)
);

create table Subjects(
subject_id integer primary key auto_increment,
subject_name varchar(100) not null
);

create table Address(
address_id integer primary key auto_increment,
city varchar(100) not null
);

create table School(
school_id integer primary key auto_increment,
school_name varchar(100) not null
);

create table address_school(
address_id integer,
school_id integer,
primary key(address_id, school_id),
foreign key(address_id) references Address(address_id),
foreign key(school_id) references School(school_id)
);


create table majors(
major_id integer primary key auto_increment,
major_name varchar(100)
);

create table school_major(
school_id integer,
major_id integer,
primary key (school_id, major_id),
foreign key(school_id) references School(school_id),
foreign key(major_id) references majors(major_id)
);



create table city_school(
address_id integer,
school_id integer,
primary key(address_id, school_id),
foreign key(address_id) references Address(address_id),
foreign key(school_id) references School(school_id)
);

create table student_teacher(
std_id integer,
t_id integer,
subject_id integer,
level_id integer,
primary key(std_id, t_id, subject_id, level_id),
foreign key(std_id) references Students(std_id),
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id),
foreign key(level_id) references Grade_level(level_id)
);

create table Grade_level(
level_id integer primary key auto_increment,
level_name varchar(100) not null
);

create table Period(
period_id integer primary key auto_increment,
period_name varchar(100) not null
);


create table Grades(
grade_id integer primary key auto_increment,
level_id integer not null,
period_id integer not null,
std_id integer not null,
subject_id integer not null,
t_id integer not null,
grade integer not null,
foreign key(level_id) references Grade_level(level_id),
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id),
foreign key(std_id) references Students(std_id),
foreign key(period_id) references Period(period_id)
);

create table teacher_subject(
t_id integer,
subject_id integer,
primary key(t_id, subject_id),
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id)
);

ALTER TABLE Grades
ADD CONSTRAINT check_grade_range CHECK (grade >= 1 AND grade <= 5);

ALTER TABLE Students
ADD CONSTRAINT fk_students_major_id FOREIGN KEY (major_id) REFERENCES majors(major_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_students_school_id FOREIGN KEY (school_id) REFERENCES School(school_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_students_period_id FOREIGN KEY (level_id) REFERENCES Grade_level(level_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_students_address_id FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE;

ALTER TABLE Teachers
ADD CONSTRAINT fk_teachers_address_id FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE;


ALTER TABLE Administrator
ADD CONSTRAINT fk_administrator_address_id FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE;

ALTER TABLE Grades
ADD CONSTRAINT fk_grades_t_id FOREIGN KEY (t_id) REFERENCES Teachers(t_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_grades_subject_id FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_grades_std_id FOREIGN KEY (std_id) REFERENCES Students(std_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_grades_period_id FOREIGN KEY (period_id) REFERENCES Period(period_id) ON DELETE CASCADE; 

insert into address (city)
values
("City1"),
("City2"),
("City3"),
("City4");

insert into address (city)
values
("City5"),
("City6"),
("City7");

insert into School(school_name)
values
("School 1"),
("School 2"),
("School 3"),
("School 4"),
("School 5"),
("School 6"),
("School 7"),
("School 8"),
("School 9");


insert into majors(major_name)
values
("Major 1"),
("Major 2"),
("Major 3"),
("Major 4");

insert into grade_level(level_name)
values
("10th grade"),
("11th grade"),
("12th grade");

insert into Period(period_name)
values
("First period"),
("Second period");

insert into address_school(address_id, school_id)
values
(2,4),
(3,7),
(4,1),
(5,4),
(6,7),
(7,1);

insert into school_major(school_id, major_id)
values
(1,1),
(1,2),
(2,3),
(2,4),
(3,1),
(3,2),
(4,3),
(4,4),
(5,1),
(5,2),
(6,3),
(6,4),
(7,1),
(7,2),
(8,3),
(8,4),
(9,1),
(9,2);


select * from address_school;

SELECT s.school_name FROM School s
INNER JOIN address_school cs ON cs.school_id = s.school_id 
INNER JOIN Address a ON a.address_id = cs.address_id 
WHERE a.city = "City1";

ALTER TABLE Students
MODIFY COLUMN passwordHash VARCHAR(300) NOT NULL;


select * from students;

SELECT m.major_name FROM majors m 
INNER JOIN school_major sm ON sm.major_id = m.major_id 
INNER JOIN School s ON s.school_id = sm.school_id 
WHERE s.school_name ="School 1"; 

select * from period;

DELETE FROM period
WHERE period_id IN (6,2,3,4,5);

