DELETE FROM applicants WHERE application_code='54823';

INSERT INTO applicants values(DEFAULT, 'Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823);

SELECT * FROM applicants
WHERE application_code='54823';
