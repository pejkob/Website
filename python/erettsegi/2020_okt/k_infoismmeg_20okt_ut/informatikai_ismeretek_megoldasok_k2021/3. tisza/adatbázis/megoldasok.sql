-- A feladatok megoldására elkészített SQL parancsokat illessze be a feladat sorszáma után!

-- 10. feladat:
CREATE DATABASE tisza
  DEFAULT CHARACTER SET utf8 
  COLLATE utf8_hungarian_ci;

-- 12. feladat:
DELETE 
FROM
  meres
WHERE
  meres.nap='2020-03-27';

-- 13. feladat:
UPDATE
  vizmerce 
SET
  vizmerce.igId=2 
WHERE
  vizmerce.varos="Tokaj";

-- 14. feladat:
SELECT
 vizmerce.varos,
  vizmerce.nullPont
FROM 
  vizmerce
ORDER BY
  vizmerce.nullPont ASC
LIMIT 1;

-- 15. feladat:
SELECT
  vizmerce.varos,
  vizmerce.lnv-vizmerce.lkv AS ingadozas
FROM
  vizmerce
ORDER BY
  ingadozas DESC;
 
-- 16. feladat:
SELECT
  igazgatosag.nev,
  COUNT(vizmerce.id) AS merceszam
FROM
  igazgatosag INNER JOIN vizmerce
    ON vizmerce.igId=igazgatosag.id
GROUP BY
  igazgatosag.nev;
  
-- 17. feladat:
SELECT
  AVG(meres.vizAllas) AS atlag
FROM
  meres INNER JOIN vizmerce
    ON meres.vmId=vizmerce.id
WHERE
  MONTH(nap) = 4 AND vizmerce.varos='Szolnok';
