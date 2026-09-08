-- ============================================
-- Vehicle Service - Vehicle Catalog
-- ============================================

CREATE TABLE brands (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT uk_brands_name UNIQUE (name)
);

CREATE TABLE vehicle_models (
    id UUID PRIMARY KEY,
    brand_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_vehicle_models_brand
        FOREIGN KEY (brand_id)
        REFERENCES brands(id),

    CONSTRAINT uk_vehicle_models_brand_name
        UNIQUE (brand_id, name)
);

CREATE TABLE vehicle_variants (
    id UUID PRIMARY KEY,
    model_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    body_type VARCHAR(50),
    fuel_type VARCHAR(50),
    transmission VARCHAR(50),
    price NUMERIC(15, 2),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_vehicle_variants_model
        FOREIGN KEY (model_id)
        REFERENCES vehicle_models(id),

    CONSTRAINT uk_vehicle_variants_model_name
        UNIQUE (model_id, name)
);

CREATE INDEX idx_vehicle_models_brand_id
    ON vehicle_models (brand_id);

CREATE INDEX idx_vehicle_variants_model_id
    ON vehicle_variants (model_id);

CREATE INDEX idx_brands_active
    ON brands (active);

CREATE INDEX idx_vehicle_models_active
    ON vehicle_models (active);

CREATE INDEX idx_vehicle_variants_active
    ON vehicle_variants (active);