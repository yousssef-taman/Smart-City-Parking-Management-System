-- Create Schema
CREATE SCHEMA IF NOT EXISTS SmartParking;
SET search_path TO SmartParking;

-- Create custom ENUM types
DO $$ BEGIN
    CREATE TYPE role_enum AS ENUM ('admin', 'driver', 'manager');
EXCEPTION WHEN duplicate_object THEN null;
END $$;

DO $$ BEGIN
    CREATE TYPE spot_status_enum AS ENUM ('occupied', 'available', 'reserved');
EXCEPTION WHEN duplicate_object THEN null;
END $$;

DO $$ BEGIN
    CREATE TYPE spot_type_enum AS ENUM ('regular', 'disabled', 'EV');
EXCEPTION WHEN duplicate_object THEN null;
END $$;

DO $$ BEGIN
    CREATE TYPE reservation_status_enum AS ENUM ('Accepted', 'Rejected', 'Pending');
EXCEPTION WHEN duplicate_object THEN null;
END $$;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role role_enum NOT NULL
);

-- Driver table
CREATE TABLE IF NOT EXISTS driver (
    license VARCHAR(20) NOT NULL,
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Parking Lot table
CREATE TABLE IF NOT EXISTS parking_lot (
    id SERIAL PRIMARY KEY,
    manager_id INT,
    lot_name VARCHAR(45) NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    pricing_structure INT NOT NULL,
    start_peek_time INT,
    end_peek_time INT,
    price_multiplier INT,
    FOREIGN KEY (manager_id) REFERENCES users(id)
);

-- Spot table
CREATE TABLE IF NOT EXISTS spot (
    spot_id SERIAL PRIMARY KEY,
    lot_id INT NOT NULL,
    status spot_status_enum NOT NULL,
    type spot_type_enum NOT NULL,
    FOREIGN KEY (lot_id) REFERENCES parking_lot(id)
);

-- Reservation table
CREATE TABLE IF NOT EXISTS reservation (
    id SERIAL PRIMARY KEY,
    spot_id INT NOT NULL,
    lot_id INT NOT NULL,
    driver_id INT NOT NULL,
    reservation_status reservation_status_enum NOT NULL,
    reservation_hours INT NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    FOREIGN KEY (spot_id) REFERENCES spot(spot_id),
    FOREIGN KEY (lot_id) REFERENCES parking_lot(id),
    FOREIGN KEY (driver_id) REFERENCES driver(id)
);

-- Penalties table
CREATE TABLE IF NOT EXISTS penalities (
    reservation_id INT PRIMARY KEY,
    penality_fee FLOAT,
    FOREIGN KEY (reservation_id) REFERENCES reservation(id)
);
