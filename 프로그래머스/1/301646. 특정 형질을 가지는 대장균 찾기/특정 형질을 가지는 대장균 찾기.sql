SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE GENOTYPE & 5 != 0 AND GENOTYPE & 2 = 0