-- ============================================
-- Vehicle Catalog Seed Data
-- ============================================

-- ---------- Brands ----------

INSERT INTO vehicle.brands (id, name, active, created_at, updated_at)
VALUES
    ('11111111-1111-1111-1111-111111111111',
     'BMW',
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('22222222-2222-2222-2222-222222222222',
     'Mercedes-Benz',
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP);


-- ---------- Models ----------

INSERT INTO vehicle.vehicle_models
    (id, brand_id, name, active, created_at, updated_at)
VALUES
    ('31111111-1111-1111-1111-111111111111',
     '11111111-1111-1111-1111-111111111111',
     '3 Series',
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('32222222-2222-2222-2222-222222222222',
     '11111111-1111-1111-1111-111111111111',
     '5 Series',
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('33333333-3333-3333-3333-333333333333',
     '22222222-2222-2222-2222-222222222222',
     'C-Class',
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP);


-- ---------- Variants ----------

INSERT INTO vehicle.vehicle_variants
    (id, model_id, name, body_type, fuel_type, transmission, price,
     active, created_at, updated_at)
VALUES
    ('41111111-1111-1111-1111-111111111111',
     '31111111-1111-1111-1111-111111111111',
     '330i',
     'Sedan',
     'Petrol',
     'Automatic',
     6500000.00,
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('42222222-2222-2222-2222-222222222222',
     '31111111-1111-1111-1111-111111111111',
     'M340i',
     'Sedan',
     'Petrol',
     'Automatic',
     8500000.00,
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('43333333-3333-3333-3333-333333333333',
     '32222222-2222-2222-2222-222222222222',
     '530i',
     'Sedan',
     'Petrol',
     'Automatic',
     7500000.00,
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    ('44444444-4444-4444-4444-444444444444',
     '33333333-3333-3333-3333-333333333333',
     'C300',
     'Sedan',
     'Petrol',
     'Automatic',
     7000000.00,
     true,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP);
     