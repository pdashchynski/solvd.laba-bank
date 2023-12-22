SELECT a.country, COUNT(p.idperson) count
FROM persons p
JOIN addresses a ON p.addresses_idaddresses=a.idaddresses
GROUP BY a.country
HAVING count>2;

SELECT ad.country, MIN(a.balance) min
FROM accounts a, persons p, addresses ad, clients c
WHERE p.idperson=c.person_idperson 
AND c.idClients=a.clients_idClients 
AND ad.idaddresses=p.addresses_idaddresses
GROUP BY ad.country
HAVING min>1000;

SELECT d.iddepartments, COUNT(s.idstaff) count
FROM departments d, staff s, persons p
WHERE p.idperson=s.person_idperson AND d.iddepartments=s.departments_iddepartments
GROUP BY d.iddepartments
HAVING count>1;

SELECT a.country, SUM(p.age) sum
FROM persons p, addresses a
WHERE p.addresses_idaddresses=a.idaddresses
GROUP BY a.country
HAVING sum>50;

SELECT p.gender, SUM(p.age) sum
FROM persons p
GROUP BY p.gender
HAVING sum>50;

SELECT c.idClients, COUNT(a.idaccounts) count
FROM accounts a, clients c
WHERE c.idClients=a.clients_idClients
GROUP BY c.idClients
HAVING count>0;

SELECT a.idaccounts, COUNT(idcards) count
FROM cards c, accounts a
WHERE c.accounts_idaccounts=a.idaccounts
GROUP BY a.idaccounts
HAVING count>1;