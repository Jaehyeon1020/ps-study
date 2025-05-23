SELECT
    RI.FOOD_TYPE,
    RI.REST_ID,
    RI.REST_NAME,
    RI.FAVORITES
FROM
    REST_INFO AS RI,
    (
        SELECT FOOD_TYPE, MAX(FAVORITES) AS COUNT
        FROM REST_INFO
        GROUP BY FOOD_TYPE
    ) AS MF
WHERE
    RI.FOOD_TYPE = MF.FOOD_TYPE AND
    RI.FAVORITES = MF.COUNT
GROUP BY
    FOOD_TYPE
ORDER BY
    FOOD_TYPE DESC;