-- ==============================================
-- BASE DE DATOS: db_crud_taskmanager_tm
-- ==============================================
CREATE SCHEMA `db_crud_taskmanager_tm` ;
 
-- ==============================================
-- INSERTAR ROLES
-- ==============================================
INSERT INTO roles (id, name)
VALUES 
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE name = VALUES(name);


-- ==============================================
-- INSERTAR USUARIOS
-- $2a$12$DhrHbtNR2yIDppGVAoYQQ.V11qUYr78ZpKcDTXeAp57NXNYWUcifq  -->  12345
-- ==============================================
-- Usuario Administrador
INSERT INTO users (id, username, password, enabled)
VALUES 
    (1, 'admin', '$2a$12$DhrHbtNR2yIDppGVAoYQQ.V11qUYr78ZpKcDTXeAp57NXNYWUcifq', true)
ON DUPLICATE KEY UPDATE username = VALUES(username);

-- Usuario Regular
INSERT INTO users (id, username, password, enabled)
VALUES 
    (2, 'user', '$2a$12$DhrHbtNR2yIDppGVAoYQQ.V11qUYr78ZpKcDTXeAp57NXNYWUcifq', true)
ON DUPLICATE KEY UPDATE username = VALUES(username);


-- ==============================================
-- ASIGNACIÓN DE ROLES A USUARIOS
-- ==============================================
-- Admin -> ROLE_USER y ROLE_ADMIN
INSERT INTO users_roles (user_id, role_id)
VALUES 
    (1, 1),
    (1, 2)
ON DUPLICATE KEY UPDATE user_id = VALUES(user_id);

-- User -> ROLE_USER únicamente
INSERT INTO users_roles (user_id, role_id)
VALUES 
    (2, 1)
ON DUPLICATE KEY UPDATE user_id = VALUES(user_id);


-- ==============================================
-- TAREAS DE EJEMPLO
-- ==============================================
INSERT INTO tasks (title, description, completed) VALUES
    ('Configurar Spring Security', 'Implementar JWT y filtros.', true),
    ('Diseñar la base de datos', 'Crear tablas de Task, User y Role.', false);

-- ==============================================

