SELECT
    CONCAT(
        '/home/grep/src/',
        BOARD.BOARD_ID,
        '/',
        FILE.FILE_ID,
        FILE.FILE_NAME,
        FILE.FILE_EXT
    ) AS FILE_PATH
FROM
    USED_GOODS_BOARD AS BOARD
    JOIN
        USED_GOODS_FILE AS FILE
        ON BOARD.BOARD_ID = FILE.BOARD_ID
WHERE
    BOARD.VIEWS = (
        SELECT
            MAX(VIEWS)
        FROM
            USED_GOODS_BOARD
    )
ORDER BY
    FILE.FILE_ID DESC;