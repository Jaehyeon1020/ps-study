SELECT
    FH.FLAVOR
FROM
    FIRST_HALF AS FH
    JOIN
        (
            SELECT
                FLAVOR,
                SUM(TOTAL_ORDER) AS TORDER
            FROM
                JULY
            GROUP BY
                FLAVOR
        ) AS JO
        ON
            FH.FLAVOR = JO.FLAVOR
ORDER BY
    (FH.TOTAL_ORDER + JO.TORDER) DESC
LIMIT 3;