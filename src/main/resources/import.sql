INSERT INTO roles (name) VALUES ("ADMINISTRADOR")
INSERT INTO roles (name) VALUES ("CLIENTE")

INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ("02605077330", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1)
INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ("97787817291", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1)
INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ("03234475385", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1)

INSERT INTO authorities VALUES (1, 1)
INSERT INTO authorities VALUES (1, 2)
INSERT INTO authorities VALUES (2, 3)