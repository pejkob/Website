-- A feladatok megoldására elkészített SQL parancsokat illessze be a feladat sorszáma után!

-- 1. feladat:
CREATE DATABASE nagykonyv DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

-- 3. feladat:
SELECT
  DISTINCT nemzetiseg
FROM
  szerzo
WHERE
  nemzetiseg <> "magyar";
  
-- 4. feladat:
SELECT
  nev,
  2005-szulEV AS kor
FROM
  szerzo
WHERE
  halEv IS NULL;

-- 5. feladat:
SELECT
  szerzo.nev,
  MIN(helyezes) AS legjobb
FROM
  konyv INNER JOIN szerzo ON konyv.szerzoId=szerzo.id
WHERE
  szerzo.nemzetiseg = 'magyar'
GROUP BY
  szerzo.nev
ORDER BY
  legjobb ASC;

-- 6. feladat:
SELECT
  szerzo.nev,
  COUNT(konyv.id) AS konyvek
FROM
  konyv INNER JOIN szerzo ON konyv.szerzoId=szerzo.id
GROUP BY
  szerzo.nev
HAVING
  konyvek > 1
ORDER BY
  konyvek DESC,
  szerzo.nev ASC;
