ALTER TABLE vendors ADD COLUMN address TEXT;
ALTER TABLE vendors ADD COLUMN credit_period  INTEGER;
ALTER TABLE vendors ADD COLUMN invoice_create INTEGER;
ALTER TABLE vendors ADD COLUMN agreement_attachment VARCHAR(255);

CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    url VARCHAR(255),
    address TEXT,
    agreement_sign VARCHAR(255),
    weekend_working VARCHAR(50),
    invoice_create INT,
    credit_period INT,
    gst_number VARCHAR(50) UNIQUE,
    billing_address TEXT,
    nationality VARCHAR(100),
    description TEXT,
    product BOOLEAN,
    paid_leaves BOOLEAN,
    client_request BOOLEAN,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT
);

CREATE TABLE client_contacts (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    contact_type VARCHAR(50),
    email VARCHAR(255),
    contact VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    CONSTRAINT fk_client_contact_client FOREIGN KEY (client_id) REFERENCES clients(id)
);