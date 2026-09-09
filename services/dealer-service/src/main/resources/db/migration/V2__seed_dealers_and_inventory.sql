INSERT INTO dealer.dealers
    (id, name, city, state, address, phone, email, active, created_at, updated_at)
VALUES
    ('51111111-1111-1111-1111-111111111111',
     'BMW Chennai Motors',
     'Chennai',
     'Tamil Nadu',
     'Mount Road, Chennai',
     '044-40000001',
     'sales@bmwchennai.example',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('52222222-2222-2222-2222-222222222222',
     'BMW Bengaluru Motors',
     'Bengaluru',
     'Karnataka',
     'Whitefield, Bengaluru',
     '080-40000002',
     'sales@bmwbengaluru.example',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('53333333-3333-3333-3333-333333333333',
     'Mercedes Chennai Auto',
     'Chennai',
     'Tamil Nadu',
     'OMR, Chennai',
     '044-40000003',
     'sales@mercedeschennai.example',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO dealer.dealer_inventory
    (id, dealer_id, variant_id, available, created_at, updated_at)
VALUES
    ('61111111-1111-1111-1111-111111111111',
     '51111111-1111-1111-1111-111111111111',
     '41111111-1111-1111-1111-111111111111',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('62222222-2222-2222-2222-222222222222',
     '52222222-2222-2222-2222-222222222222',
     '41111111-1111-1111-1111-111111111111',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('63333333-3333-3333-3333-333333333333',
     '53333333-3333-3333-3333-333333333333',
     '44444444-4444-4444-4444-444444444444',
     true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);