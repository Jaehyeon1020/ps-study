SELECT
    s.ID,
    CASE
        WHEN s.R <= 0.25 THEN "CRITICAL"
        WHEN s.R <= 0.5 THEN "HIGH"
        WHEN s.R <= 0.75 THEN "MEDIUM"
        ELSE "LOW"
    END AS COLONY_NAME
FROM (
    SELECT
        ID,
        PERCENT_RANK() over (ORDER BY SIZE_OF_COLONY DESC) AS R
    FROM
        ECOLI_DATA
) AS s
ORDER BY
    s.ID ASC;