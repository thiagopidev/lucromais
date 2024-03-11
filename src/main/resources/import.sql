INSERT INTO roles (authority, created_at, updated_at) VALUES ("SISTEMA", utc_timestamp, utc_timestamp)
INSERT INTO roles (authority, created_at, updated_at) VALUES ("ADMINISTRADOR", utc_timestamp, utc_timestamp)
INSERT INTO roles (authority, created_at, updated_at) VALUES ("CLIENTE", utc_timestamp, utc_timestamp)

INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, created_at, updated_at, usualname, email) VALUES ("02605077330", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1, utc_timestamp, utc_timestamp, "Thiago Pinheiro", "thiago@gmail.com")
INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, created_at, updated_at, usualname, email) VALUES ("97787817291", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1, utc_timestamp, utc_timestamp, "Júnior Fernandes", "jr@gmail.com")
INSERT INTO users (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, created_at, updated_at, usualname, email) VALUES ("03234475385", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, 1, 1, 1, utc_timestamp, utc_timestamp, "Susy Barros", "su@gmail.com")

INSERT INTO authorities VALUES (1, 1)
INSERT INTO authorities VALUES (2, 2)
INSERT INTO authorities VALUES (3, 3)