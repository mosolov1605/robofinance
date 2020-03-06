CREATE SEQUENCE postgres_sequence START 101;
CREATE TABLE address (
    id bigint DEFAULT nextval('postgres_sequence') UNIQUE NOT NULL,
    country varchar(255),
    region varchar(255),
    city varchar(255),
    street varchar(255),
    house varchar(255),
    flat varchar(255),
    created timestamp,
    modified timestamp
);
CREATE UNIQUE INDEX un_1 on address (coalesce(country, ''), coalesce(region, ''),
                                     coalesce(city, ''), coalesce(street, ''),
                                     coalesce(house, ''), coalesce(flat, ''));

ALTER SEQUENCE postgres_sequence OWNED BY address.id;
CREATE TABLE customer (
    id bigint DEFAULT nextval('postgres_sequence') UNIQUE NOT NULL,
    registered_address_id bigint NOT NULL,
    actual_address_id bigint NOT NULL,
    first_name varchar(255) NULL,
    last_name varchar(255) NULL,
    middle_name varchar(255) NULL,
    sex varchar(6) NOT NULL,
    CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY ((ARRAY['male'::character varying, 'female'::character varying])::text[]))),
    CONSTRAINT fk_registered_address_id FOREIGN KEY (registered_address_id)
        REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id)
    REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
ALTER SEQUENCE postgres_sequence OWNED BY customer.id;