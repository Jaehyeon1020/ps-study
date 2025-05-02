SELECT
    child.ID
FROM
    ECOLI_DATA AS child
    JOIN
        ECOLI_DATA AS parent
    ON
        child.PARENT_ID = parent.ID
    JOIN
        ECOLI_DATA AS grandparent
    ON
        parent.PARENT_ID = grandparent.ID
WHERE
    grandparent.PARENT_ID IS NULL
ORDER BY
    child.ID ASC;