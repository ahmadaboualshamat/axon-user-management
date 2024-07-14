DELETE FROM SYS_USER;

INSERT INTO SYS_USER(ID, username, password, status, roles,
                        CREATED_BY, CREATED_DATE,
                        MODIFIED_BY, MODIFIED_DATE)
VALUES (nextval('user_seq'), 'ahmad',
        '$2a$10$UeiPAtcR9IJN4SFKiqiLC.cMYBXSNd03nAAHtNPuvQm2Pu.gfT.7C', 'ACTIVE','["USER"]', 'SYSTEM', NOW(),
        'SYSTEM', NOW() );


INSERT INTO SYS_USER(ID, username, password, status, roles,
                     CREATED_BY, CREATED_DATE,
                     MODIFIED_BY, MODIFIED_DATE)
VALUES (nextval('user_seq'), 'ahmadaboualshamat',
        '$2a$10$UeiPAtcR9IJN4SFKiqiLC.cMYBXSNd03nAAHtNPuvQm2Pu.gfT.7C', 'ACTIVE','["USER"]', 'SYSTEM', NOW(),
        'SYSTEM', NOW() );
