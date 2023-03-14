-- A feladatok megoldására elkészített SQL parancsokat illessze be a feladat sorszáma után!


-- 10. feladat:
CREATE DATABASE halozat
  DEFAULT CHARACTER SET utf8 
  COLLATE utf8_hungarian_ci;

-- 12. feladat:
INSERT INTO megallok
  VALUES (198, "Kőbányai garázs");

-- 13. feladat:
UPDATE
  jaratok
SET
  jaratok.elsoAjtos=false
WHERE
  jaratok.id=20; 


-- 14. feladat:
SELECT
  jaratok.jaratSzam
FROM
  jaratok
WHERE
  jaratok.elsoAjtos=true;
  
 
-- 15. feladat:
SELECT
  megallok.nev
FROM
  megallok
WHERE
   megallok.nev LIKE "%sétány"
ORDER BY
  megallok.nev ASC;

  
-- 16. feladat:
SELECT
  halozat.sorszam,
  megallok.nev AS megallo
FROM
  jaratok INNER JOIN halozat
    ON halozat.jarat=jaratok.id
  INNER JOIN megallok
    ON halozat.megallo=megallok.id
WHERE
  jaratok.jaratSzam="CITY"
  AND
  halozat.irany="A"
ORDER BY
  halozat.sorszam ASC;
  
  
-- 17. feladat:
SELECT
  megallok.nev AS megallo,
  COUNT(jaratok.id) AS jaratokSzama
FROM
  jaratok INNER JOIN halozat
    ON halozat.jarat=jaratok.id
  INNER JOIN megallok
    ON halozat.megallo=megallok.id
GROUP BY
  megallok.nev
HAVING
  COUNT(jaratok.id)>2;  
