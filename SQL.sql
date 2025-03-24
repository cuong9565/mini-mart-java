create database mini_mart_java;
use mini_mart_java;

-- Tạo bảng Customer
CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    password VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL
);

-- Tạo bảng Account
CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    salary DOUBLE,
    type VARCHAR(100) CHARACTER SET UTF8MB4,
    idCustomer INT,
    FOREIGN KEY (idCustomer) REFERENCES Customer(id)
);

-- Tạo bảng Provider
CREATE TABLE Provider (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    phone VARCHAR(100) CHARACTER SET UTF8MB4,
    address TEXT CHARACTER SET UTF8MB4
);

-- Tạo bảng ImportOrder
CREATE TABLE ImportOrder (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    price DOUBLE,
    dateCreate DATETIME,
    unit VARCHAR(100) CHARACTER SET UTF8MB4,
    idAccount INT,
    idProvider INT,
    FOREIGN KEY (idAccount) REFERENCES Account(id),
    FOREIGN KEY (idProvider) REFERENCES Provider(id)
);

-- Tạo bảng ProductType
CREATE TABLE ProductType (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL
);

-- Tạo bảng Product
CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    price DOUBLE,
    unit VARCHAR(100) CHARACTER SET UTF8MB4,
    quantity INT,
    idProductType INT,
    FOREIGN KEY (idProductType) REFERENCES ProductType(id)
);

-- Tạo bảng ProductDetail
CREATE TABLE ProductDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detailInfo TEXT CHARACTER SET UTF8MB4,
    idImportOrder INT,
    idProduct INT,
    FOREIGN KEY (idImportOrder) REFERENCES ImportOrder(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
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
    discount INT,
    idOffer INT,
    idProduct INT,
    FOREIGN KEY (idOffer) REFERENCES Offer(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
);

-- Tạo bảng Bill
CREATE TABLE Bill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateCreate DATETIME,
    price DOUBLE,
    idAccount INT,
    FOREIGN KEY (idAccount) REFERENCES Account(id)
);

-- Tạo bảng BillInfo
CREATE TABLE BillInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateCreate DATETIME,
    price DOUBLE,
    idBill INT,
    idProduct INT,
    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
);

-- Tạo bảng OfferBill
CREATE TABLE OfferBill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    discount INT,
    idOffer INT,
    idBill INT,
    FOREIGN KEY (idOffer) REFERENCES Offer(id),
    FOREIGN KEY (idBill) REFERENCES Bill(id)
);

-- Insert dữ liệu cho bảng Customer (10 bản ghi)
INSERT INTO Customer (phone, password, firstName, lastName, address) VALUES
('0905123456', 'P@ssw0rd123', 'Nguyễn Văn', 'Hùng', '123 Đường Láng, Hà Nội'),
('0987654321', 'Secure789!', 'Trần Thị', 'Mai', '45 Lê Lợi, TP. HCM'),
('0912345678', 'Abcd1234', 'Lê Minh', 'Tuấn', '78 Hùng Vương, Đà Nẵng'),
('0938765432', 'Password99$', 'Phạm Hồng', 'Nhung', '12 Trần Phú, Nha Trang'),
('0971234567', 'Xyz7890#', 'Hoàng', 'Anh', '56 Nguyễn Huệ, Huế'),
('0945678901', 'Qwerty2023', 'Đỗ Thị', 'Lan', '89 Kim Mã, Hà Nội'),
('0923456789', 'StrongPass88', 'Vũ Quang', 'Huy', '34 Nguyễn Trãi, Thanh Hóa'),
('0967891234', '12345Pass!', 'Bùi', 'Ngọc', '67 Phạm Văn Đồng, Cần Thơ'),
('0954321876', 'HelloWorld99', 'Đặng Minh', 'Khôi', '23 Lý Thường Kiệt, Vinh'),
('0998765432', 'Secret007$', 'Lý Thanh', 'Tâm', '90 Cách Mạng Tháng 8, Đà Lạt');

-- Insert dữ liệu mẫu cho các bảng khác
-- Bảng Account
INSERT INTO Account (password, firstName, lastName, address, salary, type, idCustomer) VALUES
('AccPass123!', 'Nguyễn', 'Hùng', '123 Đường Láng, Hà Nội', 15000000, 'Admin', 1),
('AccPass456!', 'Trần', 'Mai', '45 Lê Lợi, TP. HCM', 12000000, 'Staff', 2);

-- Bảng Provider
INSERT INTO Provider (name, phone, address) VALUES
('Công ty ABC', '0912345678', '123 Nguyễn Trãi, Hà Nội'),
('Công ty XYZ', '0987654321', '456 Lê Lợi, TP. HCM');

-- Bảng ProductType
INSERT INTO ProductType (name) VALUES
('Điện tử'),
('Thời trang');

-- Bảng Product
INSERT INTO Product (name, price, unit, quantity, idProductType) VALUES
('Điện thoại Samsung', 15000000, 'Cái', 100, 1),
('Áo thun nam', 200000, 'Cái', 500, 2);

-- Bảng ImportOrder
INSERT INTO ImportOrder (quantity, price, dateCreate, unit, idAccount, idProvider) VALUES
(50, 750000000, '2025-03-24 10:00:00', 'Cái', 1, 1),
(200, 40000000, '2025-03-24 11:00:00', 'Cái', 2, 2);

-- Bảng ProductDetail
INSERT INTO ProductDetail (detailInfo, idImportOrder, idProduct) VALUES
('Điện thoại Samsung Galaxy S23, màu đen', 1, 1),
('Áo thun nam, size M, màu trắng', 2, 2);

-- Bảng Offer
INSERT INTO Offer (startDate, endDate) VALUES
('2025-03-24 00:00:00', '2025-04-24 23:59:59'),
('2025-03-25 00:00:00', '2025-04-25 23:59:59');

-- Bảng OfferProduct
INSERT INTO OfferProduct (discount, idOffer, idProduct) VALUES
(10, 1, 1),
(20, 2, 2);

-- Bảng Bill
INSERT INTO Bill (dateCreate, price, idAccount) VALUES
('2025-03-24 14:00:00', 15000000, 1),
('2025-03-24 15:00:00', 400000, 2);

-- Bảng BillInfo
INSERT INTO BillInfo (dateCreate, price, idBill, idProduct) VALUES
('2025-03-24 14:00:00', 15000000, 1, 1),
('2025-03-24 15:00:00', 200000, 2, 2);

-- Bảng OfferBill
INSERT INTO OfferBill (discount, idOffer, idBill) VALUES
(5, 1, 1),
(10, 2, 2);

select * from provider;