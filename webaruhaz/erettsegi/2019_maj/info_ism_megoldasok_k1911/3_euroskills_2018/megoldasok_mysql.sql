-- A feladatok megoldására elkészített SQL parancsokat illessze be a feladat sorszáma után!


-- 1. feladat:
CREATE DATABASE euroskills DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

-- 3. feladat:
SELECT
  count(id) AS ermek
FROM 
  versenyzo
WHERE
  pont >= 700;
  
  
-- 4. feladat:
SELECT
  DISTINCT orszagNev
FROM
  versenyzo INNER JOIN orszag
    ON versenyzo.orszagId=orszag.id
ORDER BY
  orszagNev ASC;


-- 5. feladat:
SELECT
  szakmaNev,
  count(*) AS 'versenyzok szama'
FROM
  versenyzo INNER JOIN szakma
    ON versenyzo.szakmaId = szakma.id
GROUP BY
  szakmaNev
ORDER BY 2 DESC;

-- 6. feladat:

SELECT
  nev,
  orszagNev,
  szakmaNev,
  pont
FROM
  versenyzo INNER JOIN szakma
    ON versenyzo.szakmaId = szakma.id
  INNER JOIN orszag
    ON versenyzo.orszagId = orszag.id
ORDER BY
  pont DESC,
  nev ASC
LIMIT 25;
