CREATE TABLE email_details (
     id BIGSERIAL PRIMARY KEY,
     host VARCHAR(255),
     username VARCHAR(255),
     password VARCHAR(255),
     port INTEGER,
     auth BOOLEAN DEFAULT true,
     starttls BOOLEAN DEFAULT true,
     is_active BOOLEAN DEFAULT TRUE,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by BIGINT,
     updated_by BIGINT

);


CREATE TABLE email_logger (
     id BIGSERIAL PRIMARY KEY,
     to_mail VARCHAR(255),
     subject VARCHAR(255),
     others VARCHAR(255),
     body TEXT,
     error TEXT,
     is_active BOOLEAN DEFAULT TRUE,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by BIGINT,
     updated_by BIGINT

);

CREATE TABLE  email_templates(
     id BIGSERIAL PRIMARY KEY,
     subject VARCHAR(255),
     is_active BOOLEAN DEFAULT TRUE,
     event VARCHAR(255),
     content TEXT
);

CREATE TABLE otp_alerts(
    id BIGSERIAL PRIMARY KEY,
    otp VARCHAR(255),
    email VARCHAR(255),
    mobile_number VARCHAR(255),
    action_type VARCHAR(255),
    expiry_time TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT

);

