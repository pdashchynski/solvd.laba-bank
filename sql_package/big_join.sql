SELECT DISTINCT first_name, last_name 
FROM persons per
JOIN passports pas ON pas.idpassports = per.passports_idpassports
JOIN addresses ad ON ad.idaddresses = per.addresses_idaddresses 
JOIN clients cl ON cl.person_idperson = per.idperson
JOIN accounts acc ON acc.clients_idClients = cl.idClients
JOIN cards ca ON acc.idaccounts = ca.accounts_idaccounts
JOIN departments d ON d.addresses_idaddresses = per.addresses_idaddresses
JOIN staff st ON st.departments_iddepartments = d.iddepartments
JOIN services serv ON serv.price<1000
JOIN transactions tr ON tr.services_idservices = serv.idservices
