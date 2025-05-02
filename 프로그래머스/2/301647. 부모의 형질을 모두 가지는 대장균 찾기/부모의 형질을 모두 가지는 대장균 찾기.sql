SELECT
    child.ID,
    child.GENOTYPE,
    parent.GENOTYPE AS PARENT_GENOTYPE
FROM
    ECOLI_DATA AS child JOIN ECOLI_DATA AS parent
    ON child.PARENT_ID = parent.ID
WHERE
    child.GENOTYPE & parent.GENOTYPE = parent.GENOTYPE
ORDER BY
    child.ID ASC;