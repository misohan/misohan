UPDATE applicants
SET phone_number ='003670/223-7459'
WHERE id=7;

SELECT CONCAT(first_name,' ', last_name) AS full_name, phone_number
FROM applicants
WHERE phone_number='003670/223-7459';


