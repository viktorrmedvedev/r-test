create table if not exists drug_records
(
    application_number TEXT not null primary key,
    manufacturer_name TEXT not null,
    brand_name TEXT not null,
    substance_name TEXT not null,
    product_numbers TEXT not null
);
