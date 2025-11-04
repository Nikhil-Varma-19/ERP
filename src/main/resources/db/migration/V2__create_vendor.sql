CREATE TABLE vendors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    contact_no VARCHAR(50),
    email VARCHAR(255),
    alter_contact VARCHAR(50),
    alter_email VARCHAR(255),
    gst_number VARCHAR(50) UNIQUE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT
);


ALTER TABLE resources
ADD COLUMN vendor_id BIGINT;

ALTER TABLE resources
ADD CONSTRAINT fk_resource_vendor
FOREIGN KEY (vendor_id) REFERENCES vendors(id);