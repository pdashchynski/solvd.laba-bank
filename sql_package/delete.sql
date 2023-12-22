DELETE FROM transactions 
WHERE type='Balance change';

DELETE FROM services
WHERE name='Silver deposit';

DELETE FROM staff
WHERE position='CEO';

DELETE FROM departments
WHERE iddepartments=3;

DELETE FROM clients
WHERE status='VIP';

DELETE FROM cards
WHERE type='Platinum';

DELETE FROM accounts
WHERE balance>2000;

DELETE FROM persons
WHERE gender='Male';

DELETE FROM addresses
WHERE country ='USA';

DELETE FROM passports
WHERE idpassports=6;