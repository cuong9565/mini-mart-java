create database mini_mart_java;
use mini_mart_java;

-- Tạo bảng Customer
CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    password VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    status VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL, -- Đóng || Mở
    gender varchar(100) CHARACTER SET UTF8MB4 NOT NULL -- Nam || Nữ
);

-- Tạo bảng Staff
CREATE TABLE Staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    password VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    salary DOUBLE NOT NULL,
    type VARCHAR(100) CHARACTER SET UTF8MB4,
    status VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL, -- Đóng || Mở
    gender varchar(100) CHARACTER SET UTF8MB4 NOT NULL -- Nam || Nữ
);

-- Tạo bảng Offer
CREATE TABLE Offer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    startDate DATETIME,
    endDate DATETIME
);

-- Tạo bảng OfferProduct
CREATE TABLE OfferProduct (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idOffer INT,
    discount INT,
    
    FOREIGN KEY (idOffer) REFERENCES Offer(id)
);

-- Tạo bảng OfferBill
CREATE TABLE OfferBill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    discount INT,
    idOffer INT,
    
    FOREIGN KEY (idOffer) REFERENCES Offer(id)
);

-- Tạo bảng Provider
CREATE TABLE Provider (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    phone VARCHAR(100) CHARACTER SET UTF8MB4,
    address TEXT CHARACTER SET UTF8MB4,
    email VARCHAR(100) CHARACTER SET UTF8MB4
);

-- Tạo bảng ProductDetail
CREATE TABLE ProductDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detailInfo TEXT CHARACTER SET UTF8MB4
);

-- Tạo bảng ProductType
CREATE TABLE ProductType (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL
);

-- Tạo bảng Product
CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idProductType INT,
    idProductDetail INT,
    idOfferProduct INT,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    price DOUBLE,
    unit VARCHAR(100) CHARACTER SET UTF8MB4,
    quantity INT,
    
    FOREIGN KEY (idOfferProduct) REFERENCES OfferProduct(id),
    FOREIGN KEY (idProductDetail) REFERENCES ProductDetail(id),
    FOREIGN KEY (idProductType) REFERENCES ProductType(id)
);

-- Tạo bảng ImportOrder
CREATE TABLE ImportOrder (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idStaff INT,
    idProvider INT,
    dateCreate DATETIME NOT NULL,
    total DOUBLE,
    
    FOREIGN KEY (idStaff) REFERENCES Staff(id),
    FOREIGN KEY (idProvider) REFERENCES Provider(id)
);

-- Tạo bảng ImportOrderDetail
CREATE TABLE ImportOrderDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idProduct INT NOT NULL,
    quantity INT,
    price DOUBLE,
    unit VARCHAR(100) CHARACTER SET UTF8MB4,
    
    FOREIGN KEY (idProduct) REFERENCES product(id)
);

-- Tạo bảng Bill
CREATE TABLE Bill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idStaff INT,
    idOfferBill INT,
    idCustomer INT,
    dateCreate DATETIME,
    price DOUBLE,
    
    FOREIGN KEY (idStaff) REFERENCES Staff(id),
    FOREIGN KEY (idCustomer) REFERENCES Customer(id),
    FOREIGN KEY (idOfferBill) REFERENCES OfferBill(id)
);

-- Tạo bảng BillInfo
CREATE TABLE BillInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idBill INT,
    idProduct INT,
    price DOUBLE,
    quantity INT,
    unit VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    
    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
);