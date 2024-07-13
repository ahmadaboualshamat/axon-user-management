DELETE FROM SYS_USER;

INSERT INTO SYS_USER(ID, username, password, status, roles,
                        CREATED_BY, CREATED_DATE,
                        MODIFIED_BY, MODIFIED_DATE)
VALUES (nextval('user_seq'), 'ahmad',
        '$2a$10$LFr.Lz/P688icEFLKK3YFOjkIHq6QT/oC/aBM50iFQHqmzaX6//.q', 'ACTIVE','["USER"]', 'SYSTEM', NOW(),
        'SYSTEM', NOW() );
