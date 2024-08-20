CREATE DATABASE University;

CREATE TABLE Students (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    DateOfBirth DATE,
    Major VARCHAR(100),
    EnrollmentYear INT
);

CREATE TABLE Departments (
    DepartmentID INT PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL
);

INSERT INTO Departments (DepartmentID, DepartmentName)
VALUES
(1, 'Computer Science'),
(2, 'Mathematics'),
(3, 'Physics'),
(4, 'Chemistry'),
(5, 'Biology');

DROP TABLE Students;

INSERT INTO Students (FirstName, LastName, DateOfBirth, Major, EnrollmentYear)
VALUES 
('John', 'Doe', '2000-05-15', 'Computer Science', 2018),
('Jane', 'Smith', '1999-11-22', 'Mathematics', 2017),
('Alice', 'Johnson', '2001-02-13', 'Physics', 2019),
('Bob', 'Williams', '1998-08-09', 'Chemistry', 2016),
('Charlie', 'Brown', '2002-01-01', 'Biology', 2020),
('Emily', 'Davis', '2001-09-18', 'Engineering', 2019),
('David', 'Wilson', '2000-12-30', 'Literature', 2018),
('Sophia', 'Taylor', '1999-03-07', 'History', 2017),
('Michael', 'Anderson', '2001-06-25', 'Economics', 2019),
('Olivia', 'Thomas', '2000-10-10', 'Economics', 2018);

SELECT * FROM Students;
SELECT * FROM Departments;

UPDATE Students
SET Major = 'Information Technology'
WHERE StudentID = 1;

DELETE FROM Students
WHERE StudentID > 0;

SELECT * FROM Students
LIMIT 5;

SELECT * FROM Students
ORDER BY EnrollmentYear DESC;

SELECT Major, COUNT(*) AS StudentCount
FROM Students
GROUP BY Major;

SELECT Major, COUNT(*) AS StudentCount
FROM Students
GROUP BY Major
HAVING COUNT(*) > 2;

select * from Students as a
inner join Departments as b on a.StudentID = b.DepartmentID;

select * from Students as a
left join Departments as b on a.StudentID = b.DepartmentID;

select * from Students as a
right join Departments as b on a.StudentID = b.DepartmentID;

select * from Students as a
left join Departments as b on a.StudentID = b.DepartmentID
union 
select * from Students as a
right join Departments as b on a.StudentID = b.DepartmentID;