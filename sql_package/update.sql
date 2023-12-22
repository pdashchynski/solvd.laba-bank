UPDATE passports
SET to_date='2030-07-07'
WHERE to_date='2030-06-06';

UPDATE addresses
SET postal_code='220033'
WHERE postal_code='220022';

UPDATE persons
SET first_name='Anatoli', last_name='Kuzmich'
WHERE first_name='Platon' AND last_name='Kamarou';

UPDATE cards
SET type='Debit'
WHERE type='Credit';

UPDATE clients
SET status='VIP'
WHERE status='Corporate';

UPDATE staff
SET position='Manager', salary='2500', departments_iddepartments=2
WHERE departments_iddepartments=3;

UPDATE departments
SET schedule='9 to 19'
WHERE schedule='9 to 17';

UPDATE accounts
SET balance='3000'
WHERE balance='300';

UPDATE transactions
SET amount='150'
WHERE amount='100';

UPDATE services
SET price='50'
WHERE name='Wood deposit';