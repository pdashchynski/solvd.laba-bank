SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE passports;
TRUNCATE TABLE addresses;
TRUNCATE TABLE persons;
TRUNCATE TABLE cards;
TRUNCATE TABLE clients;
TRUNCATE TABLE staff;
TRUNCATE TABLE departments;
TRUNCATE TABLE accounts;
TRUNCATE TABLE transactions;
TRUNCATE TABLE services;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO addresses (country, city, postal_code) VALUES ('USA', 'New York', '10001'),
														  ('UK', 'London', '50000'),
                                                          ('Belarus', 'Minsk', '220000'),
                                                          ('Belarus', 'Minsk', '220046'),
                                                          ('Belarus', 'Minsk', '220022');
INSERT INTO persons (first_name, last_name, age, date_of_birth, gender, addresses_id)
VALUES ('Andrew', 'Jenkins', '30', '1993-10-20', 'Male', 1),
		('Alex', 'Roberts', '27', '1996-10-29', 'Female', 2),
		('Alexander', 'Makarov', '56', '1967-06-15', 'Male', 3),
        ('Platon', 'Kamarou', '36', '1987-04-29', 'Male', 4),
		('Alesya', 'Begovich', '23', '2000-10-29', 'Female', 5),
		('Michael', 'Lane', '30', '1993-07-20', 'Male', 1);
INSERT INTO passports (from_date, to_date, persons_id) VALUES ('2000-01-01', '2025-01-01', 1),
																('2020-02-02', '2030-02-02', 2),
																('2011-03-03', '2024-03-03', 3),
																('2020-04-04', '2030-04-04', 4),
																('2020-05-05', '2030-05-05', 5),
																('2020-06-06', '2030-06-06', 6);
INSERT INTO clients (persons_id, status) VALUES ( 1, 'Regular'), (2, 'VIP'), (3, 'Corporate');
INSERT INTO accounts (from_date, to_date, balance, currency, clients_id, clients_persons_id) VALUES ('2020-11-30', '2025-11-30', '300', 'BYN', 1, 1),
																									('2015-03-03', '2025-03-30', '500000', 'USD', 2, 2),
																									('2018-06-11', '2033-06-30', '2000', 'EUR', 3, 3);
INSERT INTO cards (from_date, to_date, type, accounts_id, accounts_clients_id, accounts_clients_persons_id) VALUES ('2015-11-30', '2025-11-30', 'Platinum', 1, 1, 1),
																													('2010-03-03', '2025-03-30', 'Loan', 2, 2 ,2),
																													('2018-06-11', '2024-06-30', 'Credit', 3, 3, 3),
																													('2019-12-13', '2025-12-30', 'Credit', 3, 3, 3);
INSERT INTO departments (addresses_id, open_time, close_time) VALUES (3, '7:00:00', '19:00:00'), (4, '8:00:00', '20:00:00'), (5, '10:00:00', '22:00:00');
INSERT INTO staff (date_hired, position, salary, persons_id, departments_id) 
VALUES ('2023-09-09', 'Janitor', '1000', 6, 1),
		('2022-05-05', 'CEO', '100000', 4, 2),
		('2021-02-02', 'Cashier', '2000', 5, 3);
INSERT INTO services (name, price) VALUES ('Gold deposit', '5000'), ('Silver deposit', '1000'), ('Wood deposit', '10');
INSERT INTO transactions (type, amount, currency, date_time, staff_id, staff_persons_id, services_id) VALUES ('Loan payment', '100', 'BYN', '2023-10-10 18:45:05', 3, 5, 3),
																											('Balance change', '10000', 'USD', '2023-08-10 13:45:05', 3, 5, 1),
																											('Deposit', '500', 'EUR', '2023-05-10 10:45:05', 3, 5, 2);