CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    genre TEXT NOT NULL,
    document_id TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    email TEXT NOT NULL,
    created_date TIMESTAMP,
    update_date TIMESTAMP
);