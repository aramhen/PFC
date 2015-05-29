/*
	METHODS TABLE
*/
INSERT INTO `methods` (`idMethods`,`name`) VALUES (4,'Gauss');
INSERT INTO `methods` (`idMethods`,`name`) VALUES (5,'Gauss-Jordan');
INSERT INTO `methods` (`idMethods`,`name`) VALUES (2,'Igualación');
INSERT INTO `methods` (`idMethods`,`name`) VALUES (3,'Reducción');
INSERT INTO `methods` (`idMethods`,`name`) VALUES (1,'Sustitución');

/*
	TEACHERS TABLE
*/
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (1,'julio@uma.es','Julio Castillo Corbarán','77777777B','ZBX42EID1SE');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (2,'ramon@uma.es','Ramón Estrella Zafra','61970773R','XOR34RBJ0OU');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (3,'francisca@uma.es','Francisca Cisneros Rial','15486806Q','QWM51TDK1EM');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (4,'maria@uma.es','Maria Espinola Olmo','60508122U','YTD66KVN4TY');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (5,'marta@uma.es','Marta Cañizares Garriga','42233706E','AWB43UUA3SR');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (6,'martin@uma.es','Martin Ferrando Amado','17470333P','DSF63NCN7MC');
INSERT INTO `teachers` (`idTeachers`,`email`,`name`,`numDoc`,`password`) VALUES (7,'juancarlos@uma.es','Juan Carlos Sedano Oñate','86920169L','PIX85EBZ2UX');

/*
	STUDENTS TABLE
*/
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (1,'jesus@uma.es','Jesus Melchor Perea','77777777B','UNC72FMN5ML8');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (2,'hugo@uma.es','Hugo Villar Gallardo','71003005D','UNC72FMN5ML0');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (3,'roberto@uma.es','Roberto Conejo Benavides','71003005F','ZES56IVU0TM');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (4,'teresa@uma.es','Teresa Camacho Romeu','49282619A','IEB14LIZ1UE');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (5,'monica@uma.es','Monica Rangel Busto','52305456H','UNC72FMN5ML');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (6,'rosa@uma.es','Rosa Alamo Félix','86745407O','BAM53BFG4TM');
INSERT INTO `students` (`idStudents`,`email`,`name`,`numDoc`,`password`) VALUES (7,'alberto.ramirez@uma.es','Alberto Ramírez Henares','82626660M','QLV17QBH7ZY');

/*
	PROBLEMS TABLE
*/
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (48,1,'Problema Gauss Mayo',4,2,'x=1+y|2*y + 23 = 3x','2015-05-28','2016-05-28',0,'X=2.5|Y=34');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (49,1,'Problema sustitución 2 variables',1,2,'x + 4*y = 22|2*x + 8*y = 44','2016-05-28','2015-06-30',1,NULL);
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (50,1,'Igualación hasta junio',2,3,'x + 4*y + 3*z= 22|y = 1 + x + z|z = 23','2016-05-29','2015-07-31',0,NULL);
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (51,1,'Sistema por Gauss-Jordan',5,3,'2*x + 3*y + 6*z= 4|x + 2*y + 4*z= 8|3*x + y + 10*z= 12','2015-05-28','2016-07-31',0,'X=3|Y=8|Z=5');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (52,1,'Reducción de la clase de hoy',3,2,'3*x + 2*y = 7|2*x + 3*y = 8','2015-05-31','2016-07-31',0,'X=1|Y=2');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (53,1,'Problema Opcional',4,2,'x + 4*y = 22|2*y + 23 = 3x','2015-06-03','2016-08-30',0,NULL);
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (54,1,'Para mañana en clase',5,2,'5*x + 2*y = 16|2*x + 8*y = 28','2015-05-27','2016-08-06',1,NULL);
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (55,1,'Obligatorio para evaluación',4,3,'x + 4*y + 7*z = 22|2*x + 8*y = 44|3*x + y + z = 23','2015-05-29','2016-08-20',0,'X=3.33|Y=2.5|Z=5.77');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (56,1,'+0.5 al primero',2,1,'3*x + 16 = 1','2015-05-28','2016-07-31',1,'X=-5');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (57,1,'Tecer problema Gauss',4,2,'x + y = 10|y + 24 = 3x','2015-05-29','2016-08-30',1,'X=17/2|Y=3/2');
INSERT INTO `problems` (`idProblems`,`teacher`,`title`,`method`,`numVariables`,`equations`,`initDate`,`endDate`,`uniqueAnswer`,`solution`) VALUES (58,1,'2º de reducción',3,2,'7*x + y = 25|3*x + 5*y = 29','2015-05-28','2016-08-12',1,'X=3|Y=4');

/*
	ANSWERS TABLE
*/
INSERT INTO `answers` (`idAnswers`,`problem`,`student`,`answerDate`,`solution`,`steps`) VALUES (1,48,7,'2015-05-28 15:19:56','X=19|Y=20','$\\begin{pmatrix} 1&amp;-1&amp;1\\\\-3&amp;2&amp;-23\\end{pmatrix}$<div style=\'margin: 3% auto; width: 15%; border: 1px solid #2e6da4;\'/>$\\begin{pmatrix} 1&amp;-1&amp;1\\\\0&amp;-1&amp;-20\\end{pmatrix}$<div style=\'margin: 3% auto; width: 15%; border: 1px solid #2e6da4;\'/>$\\begin{pmatrix} 1&amp;-1&amp;1\\\\0&amp;1&amp;20\\end{pmatrix}$<div style=\'margin: 3% auto; width: 15%; border: 1px solid #2e6da4;\'/>$\\begin{pmatrix} 1&amp;0&amp;19\\\\0&amp;1&amp;20\\end{pmatrix}$');
