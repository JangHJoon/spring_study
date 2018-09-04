DROP TABLE teacher;

CREATE TABLE teacher (
	
	id 			INT 			PRIMARY KEY				AUTO_INCREMENT,
	name 		VARCHAR(20)		NOT NULL,
	age 		INT 			NOT NULL,
	subject 	VARCHAR(20)		NOT NULL



);



INSERT INTO teacher(name, age, subject) VALUES
	('john', 45, 'math'),
	('jason', 51, 'english');
	
SELECT * FROM teacher;