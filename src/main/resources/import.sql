INSERT INTO roles (authority, created_at, updated_at) VALUES ("Sistema", utc_timestamp, utc_timestamp)
INSERT INTO roles (authority, created_at, updated_at) VALUES ("Administrador", utc_timestamp, utc_timestamp)
INSERT INTO roles (authority, created_at, updated_at) VALUES ("Cliente", utc_timestamp, utc_timestamp)

INSERT INTO users (username, password, enabled, created_at, updated_at, usualname, email) VALUES ("02605077330", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, utc_timestamp, utc_timestamp, "Thiago Pinheiro", "thiago@gmail.com")
INSERT INTO users (username, password, enabled, created_at, updated_at, usualname, email) VALUES ("97787817291", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, utc_timestamp, utc_timestamp, "Júnior Fernandes", "jr@gmail.com")
INSERT INTO users (username, password, enabled, created_at, updated_at, usualname, email) VALUES ("03234475385", "$2a$10$0tvcBCgR032yw7AlIrSgt.RKgu8wHRRYF.uOC.hSRuYm8mzVcWb6a", 1, utc_timestamp, utc_timestamp, "Susy Barros", "su@gmail.com")

INSERT INTO authorities VALUES (1, 1)
INSERT INTO authorities VALUES (2, 2)
INSERT INTO authorities VALUES (3, 3)