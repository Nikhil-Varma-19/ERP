CREATE TABLE api_logger (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(2048),
    method VARCHAR(10),
    body TEXT,
    ip_address VARCHAR(45),
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE error_logger (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(2048),
    method VARCHAR(10),
    body TEXT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT
);
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
,
    created_by BIGINT,
    updated_by BIGINT
);
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (user_id, role)
);
CREATE TABLE technology (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT
);
CREATE TABLE resources (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    personal_email VARCHAR(255) UNIQUE,
    company_email VARCHAR(255) UNIQUE,
    mobile_number VARCHAR(20) UNIQUE,
    resume VARCHAR(255),
    resume_type VARCHAR(50),
    reference VARCHAR(255),
    passing_year INT,
    joining_date DATE DEFAULT CURRENT_DATE,
    contract_end_date DATE,
    end_date DATE,
    pf BOOLEAN,
    reason TEXT,
    experience INT DEFAULT 0,
    position_update TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
,
    created_by BIGINT,
    updated_by BIGINT
);
CREATE TABLE resource_skill (
    id BIGSERIAL PRIMARY KEY,
    resource_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
,
    created_by BIGINT,
    updated_by BIGINT,
    CONSTRAINT fk_resource_skill_resource FOREIGN KEY (resource_id) REFERENCES resources(id),
    CONSTRAINT fk_resource_skill_technology FOREIGN KEY (technology_id) REFERENCES technology(id)
);