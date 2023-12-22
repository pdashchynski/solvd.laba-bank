ALTER TABLE persons
ADD email varchar(45),
ADD favourite_food varchar(45);

ALTER TABLE persons
MODIFY COLUMN age smallint;

ALTER TABLE persons
RENAME COLUMN email to email_address;

ALTER TABLE persons
MODIFY COLUMN email_address varchar(55);

ALTER TABLE staff
ADD funny_level varchar(45);