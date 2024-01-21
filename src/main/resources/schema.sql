CREATE TABLE IF NOT EXISTS Franchise (
    franchise_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Subsidiary (
    subsidiary_id INT AUTO_INCREMENT PRIMARY KEY,
    franchise_id INT,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES Franchise(franchise_id)
);

CREATE TABLE IF NOT EXISTS Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    subsidiary_id INT,
    name VARCHAR(255) NOT NULL,
    stock INT,
    FOREIGN KEY (subsidiary_id) REFERENCES Subsidiary(subsidiary_id)
);