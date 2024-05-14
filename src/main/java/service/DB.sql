create database project_knk;

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
foreign key(major_id) references majors(major_id)
on delete cascade,
foreign key(school_id) references School(school_id)
on delete cascade,
foreign key(level_id) references Grade_level(level_id)
on delete cascade,
foreign key(address_id) references Address(address_id)
on delete cascade
);


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
foreign key(address_id) references Address(address_id)
on delete cascade,
foreign key(school_id) references School(school_id)
on delete cascade,
foreign key(subject_id) references subjects(subject_id)
on delete cascade
);

create table Administrator(
a_id integer primary key auto_increment,
a_name varchar(50) not null,
a_lastName varchar(50) not null,
email varchar(100) not  null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
foreign key(address_id) references Address(address_id) on delete cascade
);


insert into Administrator (a_name, a_lastName, email, salt, passwordHash, address_id)
values ("Mirgeta","Gashi","mirgeta.gashi@admin.com","WRF/dgaooVSOOpRAWZVT02zXDu5L+BGXZTPsom/KGSo=",
"5752462f6467616f6f56534f4f705241575a565430327a584475354c2b4247585a5450736f6d2f4b47536f3d6e76aa12a6fb420a108006aece4d0962214f2ddf41b785a3fbf101cfe2bace1e"
,1);

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
school_name varchar(100) not null,
address_id integer not null,
foreign key(address_id)references Address(address_id)
on delete cascade
);



create table majors(
major_id integer primary key auto_increment,
major_name varchar(100)
);

create table school_major(
school_id integer,
major_id integer,
primary key (school_id, major_id),
foreign key(school_id) references School(school_id)
on delete cascade,
foreign key(major_id) references majors(major_id)
on delete cascade
);





create table student_teacher(
std_id integer ,
t_id integer,
subject_id integer,
level_id integer,
primary key(std_id, t_id, subject_id, level_id),
foreign key(std_id) references Students(std_id) on delete cascade,
foreign key(t_id) references Teachers(t_id) on delete cascade,
foreign key(subject_id) references Subjects(subject_id) on delete cascade,
foreign key(level_id) references Grade_level(level_id) on delete cascade
);

create table Grade_level(
level_id integer primary key auto_increment,
level_name varchar(100) not null
);

create table Period(
period_id integer primary key auto_increment,
period_name varchar(100) not null
);

CREATE TABLE Grades (
    grade_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    std_id INTEGER NOT NULL,
    subject_id INTEGER NOT NULL,
    t_id INTEGER NOT NULL,
    level_id INTEGER NOT NULL,
    period1_grade INTEGER NOT NULL,
    period2_grade INTEGER NOT NULL,
    final_grade INTEGER,
    FOREIGN KEY (level_id) REFERENCES Grade_level(level_id) on delete cascade,
    FOREIGN KEY (t_id) REFERENCES Teachers(t_id) on delete cascade,
    FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id) on delete cascade,
    FOREIGN KEY (std_id) REFERENCES Students(std_id) on delete cascade
);


DELIMITER $$
CREATE FUNCTION Calculate_final_grade(x INT, y INT)
RETURNS INT DETERMINISTIC
BEGIN
    RETURN ROUND((x + y) / 2);
END $$
$$
DELIMITER ;

SELECT Calculate_final_grade(5, 4) AS final_grade;



ALTER TABLE Grades
ADD CONSTRAINT check_grade_range CHECK (period1_grade >= 1 AND period1_grade <= 5);


ALTER TABLE Grades
ADD CONSTRAINT check_grade2_range CHECK (period2_grade >= 1 AND period2_grade <= 5);


SELECT m.major_name FROM majors m 
INNER JOIN school_major sm ON sm.major_id = m.major_id 
INNER JOIN School s ON s.school_id = sm.school_id 
WHERE s.school_name ="School 1"; 


               
               
CREATE VIEW School_info AS
SELECT 
    s.school_id,
    s.school_name,
    a.city AS address,
    COUNT(*) AS num_students
FROM 
    School s
INNER JOIN 
    Address a ON a.address_id = s.address_id 
INNER JOIN 
    Students st ON st.school_id = s.school_id
GROUP BY 
    s.school_id, s.school_name, a.city;

select * from School_info;

insert into subjects (subject_name)
values("Matematike"),
	  ("Gjuhe shqipe"),
      ("Biologji"),
      ("Kimi"),
      ("Fizike"),
      ("Gjeografi"),
      ("Histori"),
      ("Muzike"),
      ("Edukate fizike"),
      ("Teknologji"),
      ("Astronomi"),
      ("Psikologji"),
      ("Filozofi"),
      ("Anatomi"),
      ("Kontabilitet");

      insert into majors(major_name)
      values ("Gjimnaz natyror"),
      	   ("Gjimnaz shoqeror"),
             ("Ekonomi"),
             ("Mjekesi");
             insert into majors(major_name)
             values("Teknike");

insert into School (school_name, address_id)
values("Aleksander Xhuvani", 1),
	   ("Fan Noli", 1),
       ("Xhevdet Doda", 2),
       ("Sami Frasheri", 2),
       ("Remzi Ademaj", 3),
       ("Ymer Prizreni", 3),
       ("Mehmet Isai", 4),
       ("Zenel Hajdini", 4),
       ("Frang Bardhi", 5),
       ("Hasan Prishtina", 5);
       insert into school(school_name, address_id)
       values("Xheladin Deda", 5);

insert into school_major(school_id, major_id)
values(11, 4),
	  (10, 3),
      (9, 1),
      (9, 2),
      (8, 1),
      (8, 2),
      (7, 5),
      (6, 1),
      (6, 2),
      (5, 3),
      (4, 1),
      (4, 2),
      (3, 1),
      (3, 2),
      (2, 5),
      (1, 1),
      (1, 2);

      insert  into Grade_level(level_name)
      values ("Klasa e 10-te"),
      	   ("Klasa e 11-te"),
             ("Klasa e 12-te");

insert into Period(period_name)
values ("Perioda e pare"),
	   ("Perioda e dyte");

	   INSERT INTO Address (city)
       VALUES ('Podujeva'),
              ('Prishtina'),
              ('Prizren'),
              ('Gjilan'),
              ('Mitrovica');
              select * from address;


