CREATE DATABASE mini_mart_java;
USE mini_mart_java;

-- Tạo bảng Customer
CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    password VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    status VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL, -- Đóng || Mở
    gender VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL -- Nam || Nữ 
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
    gender VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL -- Nam || Nữ
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
    FOREIGN KEY (idProduct) REFERENCES Product(id)
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
 -- -------------------------------------- CUONG ---------------------------------------------------------
 INSERT INTO Provider (name, phone, address, email) VALUES
 ('Vinamilk', '02854155555', '10 Tân Trào, P. Tân Phú, Q. 7, TP. HCM', 'contact@vinamilk.com.vn'),
 ('Nestlé Việt Nam', '02873008345', '5 Đường 17A, P. Bình Trị Đông B, Q. Bình Tân, TP. HCM', 'support@vn.nestle.com'),
 ('TH True Milk', '02462691111', '166 Nguyễn Thái Học, Ba Đình, Hà Nội', 'info@thmilk.vn'),
 ('Coca-Cola Việt Nam', '02838920017', '485 Xa Lộ Hà Nội, P. Linh Trung, Thủ Đức, TP. HCM', 'contact@coca-cola.com.vn'),
 ('PepsiCo Việt Nam', '02838233545', '88 Hàm Nghi, Q.1, TP. HCM', 'support@pepsico.vn'),
 ('Masan Consumer', '02837578899', '12 Nguyễn Bỉnh Khiêm, P. Đa Kao, Q.1, TP. HCM', 'info@masanconsumer.com'),
 ('Unilever Việt Nam', '02838238338', '156 Nguyễn Lương Bằng, Q.7, TP. HCM', 'customer@unilever.com'),
 ('P&G Việt Nam', '02838219999', '128 Trần Hưng Đạo, Q.1, TP. HCM', 'contact@pg.com'),
 ('Vissan', '02838555310', '420 Nơ Trang Long, P.13, Bình Thạnh, TP. HCM', 'support@vissan.com.vn'),
 ('CP Việt Nam', '02513836300', 'KCN Biên Hòa 2, Đồng Nai', 'info@cpvietnam.com'),
 ('Ba Huân', '02837593939', '9/1 Lê Thị Hồng, P.17, Gò Vấp, TP. HCM', 'contact@bahuan.vn'),
 ('San Hà', '02838580000', '229 Nguyễn Văn Cừ, Q.5, TP. HCM', 'info@sanha.vn'),
 ('Acecook Việt Nam', '02838152822', 'KCN Tân Bình, P. Tây Thạnh, Q. Tân Phú, TP. HCM', 'support@acecookvietnam.vn'),
 ('Miwon Việt Nam', '02838372626', 'KCN Biên Hòa 1, Đồng Nai', 'info@miwon.vn'),
 ('Ajinomoto Việt Nam', '02838151515', 'KCN Long Thành, Đồng Nai', 'contact@ajinomoto.com.vn'),
 ('Kinh Đô', '02837401565', '26 VSIP, Thuận An, Bình Dương', 'info@kinhdo.vn'),
 ('Orion Việt Nam', '02838984111', 'KCN Mỹ Phước, Bến Cát, Bình Dương', 'support@orion.vn'),
 ('Perfetti Van Melle', '02838993388', 'Số 10, Đường số 3, KCN Sóng Thần, Bình Dương', 'info@perfettivanmelle.vn'),
 ('Hòa Phát', '02462848866', '39 Nguyễn Đình Chiểu, Hai Bà Trưng, Hà Nội', 'contact@hoaphat.com.vn'),
 ('Sanaky Việt Nam', '02838569999', '48/9B Xa Lộ Hà Nội, Q.9, TP. HCM', 'support@sanaky.vn');
 
 -- ---------------- SELECT -----------
 select * from Provider;
 
 -- ------------------------------------
 -- drop database mini_mart_java
     
 -- Staff
 INSERT INTO staff (phone, password, firstName, lastName, address, salary, type, status, gender)
 VALUES
 ('0955112233', 'securepass', 'Phu', 'Ng', 'Nha Trang', 19000000, 'Quản lý', 'Active', 'Nam'),
 ('0988776655', 'pass1234', 'An', 'Tran', 'Hà Nội', 15000000, 'Nhân viên', 'Active', 'Nữ'),
 ('0977665544', 'adminpass', 'Linh', 'Nguyen', 'Đà Nẵng', 21000000, 'Quản lý', 'Active', 'Nam'),
 ('0966554433', 'password1', 'Huy', 'Le', 'Hải Phòng', 18000000, 'Nhân viên', 'Inactive', 'Nam'),
 ('0955443322', 'mypassword', 'Trang', 'Pham', 'Cần Thơ', 16000000, 'Nhân viên', 'Active', 'Nữ'),
 ('0944332211', '123456', 'Binh', 'Do', 'Huế', 22000000, 'Quản lý', 'Active', 'Nam'),
 ('0933221100', 'nvpassword', 'Nga', 'Vo', 'Bình Dương', 14000000, 'Nhân viên', 'Active', 'Nữ'),
 ('0922110099', 'abcd1234', 'Duc', 'Dang', 'Vũng Tàu', 20000000, 'Quản lý', 'Inactive', 'Nam'),
 ('0911009988', 'staffpass', 'Hoa', 'Ly', 'Biên Hòa', 17000000, 'Nhân viên', 'Active', 'Nữ'),
 ('0900998877', 'newpass', 'Tuan', 'Ho', 'Quảng Ninh', 19500000, 'Quản lý', 'Active', 'Nam');

