use project_knk;

create table Students(
std_id integer primary key auto_increment,
std_name varchar(50) not null,
std_lastName varchar(50) not null,
email varchar(100) not  null unique,
username varchar(60) not null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
foreign key(address_id) references Address(address_id)
);

create table Teachers(
t_id integer primary key auto_increment,
t_name  varchar(50) not null,
t_lastName varchar(50) not null,
email varchar(100) not  null unique,
username varchar(60) not null unique,
salt varchar(100) not null,
passwordHash varchar(256) not null,
address_id integer not null,
foreign key(address_id) references Address(address_id)
);

create table Administrator(
a_id integer primary key auto_increment,
a_name varchar(50) not null,
a_lastName varchar(50) not null,
email varchar(100) not  null unique,
username varchar(60) not null unique,
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
city varchar(100) not null,
street varchar(100)
);

create table student_teacher(
std_id integer,
t_id integer,
subject_id integer,
period_id integer,
primary key(std_id, t_id, subject_id, period_id),
foreign key(std_id) references Students(std_id),
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id),
foreign key(period_id) references Period(period_id)
);

create table Period(
period_id integer primary key auto_increment,
period_name varchar(100) not null
);


create table Grades(
grade_id integer primary key auto_increment,
period_id integer,
std_id integer,
subject_id integer not null,
t_id integer not null,
grade integer not null,
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id),
foreign key(std_id) references Students(std_id),
foreign key(period_id) references Period(period_id)
);

create table teacher_subject(
t_id integer,
subject_id integer,
period_id integer,
primary key(t_id, subject_id, period_id),
foreign key(t_id) references Teachers(t_id),
foreign key(subject_id) references Subjects(subject_id),
foreign key(period_id) references Period(period_id)
);


ALTER TABLE Grades
ADD CONSTRAINT check_grade_range CHECK (grade >= 1 AND grade <= 5);