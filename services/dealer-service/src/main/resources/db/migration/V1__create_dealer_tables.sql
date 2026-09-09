CREATE TABLE dealers (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    address VARCHAR(255),
    phone VARCHAR(30),
    email VARCHAR(255),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_dealers_active ON dealers(active);
CREATE INDEX idx_dealers_city ON dealers(city);

CREATE TABLE dealer_inventory (
    id UUID PRIMARY KEY,
    dealer_id UUID NOT NULL,
    variant_id UUID NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_dealer_inventory_dealer
        FOREIGN KEY (dealer_id) REFERENCES dealers(id),

    CONSTRAINT uk_dealer_inventory_dealer_variant
        UNIQUE (dealer_id, variant_id)
);

CREATE INDEX idx_dealer_inventory_variant
    ON dealer_inventory(variant_id);

CREATE INDEX idx_dealer_inventory_available
    ON dealer_inventory(available);