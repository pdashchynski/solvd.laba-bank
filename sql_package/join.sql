SELECT *
FROM persons p
JOIN clients c
ON p.idperson=c.person_idperson;

SELECT *
FROM persons p
LEFT JOIN clients c
ON p.idperson=c.person_idperson;

SELECT *
FROM departments d
RIGHT JOIN staff s
ON d.iddepartments=s.departments_iddepartments AND d.iddepartments=1;

SELECT *
FROM accounts a
LEFT JOIN cards c
ON a.idaccounts=c.accounts_idaccounts AND c.accounts_idaccounts=4;

SELECT *
FROM clients c
RIGHT JOIN persons p
ON p.idperson=c.person_idperson;