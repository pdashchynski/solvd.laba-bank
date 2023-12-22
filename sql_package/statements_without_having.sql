SELECT a.country, COUNT(p.idperson) count
FROM persons p
JOIN addresses a ON p.addresses_idaddresses=a.idaddresses
GROUP BY a.country;

SELECT SUM(p.age)
FROM persons p;

SELECT AVG(s.price)
FROM services s;

SELECT MIN(s.price)
FROM services s;

SELECT MAX(s.price)
FROM services s;

SELECT ad.country, MIN(a.balance)
FROM accounts a, persons p, addresses ad, clients c
WHERE p.idperson=c.person_idperson 
AND c.idClients=a.clients_idClients 
AND ad.idaddresses=p.addresses_idaddresses
GROUP BY ad.country;

SELECT d.iddepartments, COUNT(s.idstaff)
FROM departments d, staff s, persons p
WHERE p.idperson=s.person_idperson AND d.iddepartments=s.departments_iddepartments
GROUP BY d.iddepartments;