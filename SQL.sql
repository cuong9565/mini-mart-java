DROP DATABASE if EXISTS mini_mart_java;
CREATE DATABASE mini_mart_java;
USE mini_mart_java;

-- Tạo bảng Customer
CREATE TABLE Customer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    lastName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    firstName VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    address VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL,
    state VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL default 'Đang hoạt động', -- Đã bị Khóa || Hoạt động
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
    role VARCHAR(100) CHARACTER SET UTF8MB4,
    state VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL default 'Đang hoạt động', -- Đã bị khóa || Đang hoạt động
    gender VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL -- Nam || Nữ
);

-- Tạo bảng Offer
CREATE TABLE Offer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    startDate DATE,
    endDate DATE
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
    dateCreate DATE,
    price DOUBLE,
    state VARCHAR(100) CHARACTER SET UTF8MB4 default 'Chưa thanh toán', -- "Chưa thanh toán" or "Đã thanh toán"
    
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
    discount INT,
    total DOUBLE,
    unit VARCHAR(100) CHARACTER SET UTF8MB4,
	nameProduct VARCHAR(100) CHARACTER SET UTF8MB4,

    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
); 

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
 
INSERT INTO Customer (phone, lastName, firstName, address, gender) VALUES
('0905123456', 'Nguyễn', 'Anh', '123 Đường Láng, Đống Đa, Hà Nội', 'Nam'),
('0987654321', 'Trần Thị', 'Mai', '45 Nguyễn Huệ, Quận 1, TP.HCM', 'Nữ'),
('0912345678', 'Lê Văn', 'Tuấn', '78 Trần Phú, Nha Trang, Khánh Hòa', 'Nam'),
('0938765432', 'Phạm', 'Lan', '12 Lê Lợi, Huế, Thừa Thiên Huế', 'Nữ'),
('0971234567', 'Hoàng Minh', 'Hùng', '56 Hùng Vương, Đà Nẵng', 'Nam'),
('0945678901', 'Đỗ Thị', 'Hồng', '89 Nguyễn Văn Linh, Hải Phòng', 'Nữ'),
('0967890123', 'Bùi', 'Nam', '34 Kim Mã, Ba Đình, Hà Nội', 'Nam'),
('0923456789', 'Vũ Thị', 'Ngọc', '67 Pasteur, Quận 3, TP.HCM', 'Nữ'),
('0956789012', 'Ngô', 'Khang', '23 Bạch Đằng, Đà Lạt, Lâm Đồng', 'Nam'),
('0990123456', 'Trương Thị', 'Thu', '15 Trần Hưng Đạo, Cần Thơ', 'Nữ'),
('0909876543', 'Phan Văn', 'Long', '90 Lê Đại Hành, Vinh, Nghệ An', 'Nam'),
('0934567890', 'Đặng', 'Hà', '27 Nguyễn Trãi, Thanh Hóa', 'Nữ'),
('0916789012', 'Lý Quốc', 'Hùng', '56 Phạm Ngũ Lão, Quy Nhơn, Bình Định', 'Nam'),
('0989012345', 'Hà Thị', 'Linh', '78 Nguyễn Thị Minh Khai, Vũng Tàu', 'Nữ'),
('0941234567', 'Mai', 'Tâm', '34 Lê Văn Sỹ, Tân Bình, TP.HCM', 'Nam'),
('0963456789', 'Nguyễn Thị', 'Duyên', '12 Nguyễn Đình Chiểu, Hà Nội', 'Nữ'),
('0927890123', 'Trần', 'Đức', '67 Trần Quốc Toàn, Đà Nẵng', 'Nam'),
('0950123456', 'Lê Thị', 'Thảo', '23 Lý Thường Kiệt, Hải Phòng', 'Nữ'),
('0975678901', 'Phạm Văn', 'Hòa', '89 Hùng Vương, Cần Thơ', 'Nam'),
('0992345678', 'Hoàng', 'Yến', '45 Lê Hồng Phong, Vinh, Nghệ An', 'Nữ'),
('0906789012', 'Đỗ Minh', 'Quang', '56 Nguyễn Huệ, Huế', 'Nam'),
('0939012345', 'Bùi Thị', 'Hương', '78 Trần Phú, Nha Trang', 'Nữ'),
('0913456789', 'Vũ', 'Tài', '34 Nguyễn Văn Cừ, Đà Lạt', 'Nam'),
('0985678901', 'Ngô Thị', 'Bích', '12 Bà Triệu, Vũng Tàu', 'Nữ'),
('0947890123', 'Trương', 'Nhật', '67 Lê Lợi, Quy Nhơn', 'Nam'),
('0960123456', 'Phan Thị', 'Ánh', '23 Nguyễn Trãi, Thanh Hóa', 'Nữ'),
('0922345678', 'Đặng Văn', 'Phúc', '89 Kim Đồng, Hà Nội', 'Nam'),
('0954567890', 'Lý', 'Nhung', '45 Võ Thị Sáu, TP.HCM', 'Nữ'),
('0976789012', 'Hà Minh', 'Hoàng', '78 Nguyễn Văn Linh, Đà Nẵng', 'Nam'),
('0998901234', 'Mai Thị', 'Kim', '12 Lê Đại Hành, Hải Phòng', 'Nữ'),
('0901234567', 'Nguyễn Văn', 'Bình', '56 Hùng Vương, Cần Thơ', 'Nam'),
('0933456789', 'Trần', 'Oanh', '34 Nguyễn Thị Minh Khai, Vũng Tàu', 'Nữ'),
('0915678901', 'Lê Quốc', 'Bảo', '67 Trần Hưng Đạo, Quy Nhơn', 'Nam'),
('0987890123', 'Phạm Thị', 'Vân', '23 Lê Văn Tám, Đà Lạt', 'Nữ'),
('0949012345', 'Hoàng Văn', 'Dũng', '89 Bạch Đằng, Huế', 'Nam'),
('0961234567', 'Đỗ', 'Tuyết', '45 Nguyễn Huệ, Nha Trang', 'Nữ'),
('0924567890', 'Bùi Minh', 'Thắng', '12 Lê Hồng Phong, Vinh', 'Nam'),
('0956789012', 'Vũ Thị', 'Hạnh', '78 Nguyễn Trãi, Thanh Hóa', 'Nữ'),
('0978901234', 'Ngô', 'Kiên', '56 Kim Mã, Hà Nội', 'Nam'),
('0990123456', 'Trương Thị', 'Lệ', '34 Pasteur, TP.HCM', 'Nữ'),
('0902345678', 'Phan', 'Trí', '67 Trần Quốc Toàn, Đà Nẵng', 'Nam'),
('0934567890', 'Đặng Thị', 'Mỹ', '23 Lý Thường Kiệt, Hải Phòng', 'Nữ'),
('0916789012', 'Lý Văn', 'Sơn', '89 Hùng Vương, Cần Thơ', 'Nam'),
('0989012345', 'Hà', 'Phương', '45 Lê Lợi, Vũng Tàu', 'Nữ'),
('0941234567', 'Mai Minh', 'Vũ', '12 Trần Phú, Quy Nhơn', 'Nam'),
('0963456789', 'Nguyễn', 'Hoa', '78 Nguyễn Văn Cừ, Đà Lạt', 'Nữ'),
('0925678901', 'Trần Văn', 'Lâm', '56 Bạch Đằng, Huế', 'Nam'),
('0957890123', 'Lê Thị', 'Quyên', '34 Nguyễn Huệ, Nha Trang', 'Nữ'),
('0979012345', 'Phạm', 'Hải', '67 Lê Hồng Phong, Vinh', 'Nam'),
('0991234567', 'Hoàng Thị', 'Tâm', '23 Nguyễn Trãi, Thanh Hóa', 'Nữ');


INSERT INTO Offer (startDate, endDate) VALUES
('2025-04-01', '2025-04-15'),
('2025-04-05', '2025-04-20'),
('2025-04-10', '2025-04-25'),
('2025-04-15', '2025-04-30'),
('2025-04-20', '2025-05-05'),
('2025-02-01', '2025-02-28');

INSERT INTO OfferProduct (idOffer, discount) VALUES
(1, 20),
(1, 30),
(2, 15),
(2, 25),
(3, 10),
(3, 40),
(4, 50),
(4, 20),
(5, 30),
(5, 15),
(6, 35);

INSERT INTO OfferBill (discount, idOffer) VALUES
(10, 1),
(15, 2),
(20, 3),
(25, 4),
(30, 5),
(50, 6),
(5, 1),
(35, 3);

INSERT INTO ProductType (name) VALUES
('Thực phẩm đóng gói'), ('Đồ uống'), ('Gia vị'), ('Đồ ăn vặt'), ('Sữa và chế phẩm'),
('Thịt tươi'), ('Hải sản'), ('Rau củ'), ('Trái cây'), ('Đồ đông lạnh'),
('Bánh kẹo'), ('Dụng cụ vệ sinh'), ('Đồ dùng nhà bếp'), ('Hóa mỹ phẩm'), ('Chăm sóc cá nhân'),
('Thực phẩm khô'), ('Đồ hộp'), ('Nước giặt'), ('Giấy vệ sinh'), ('Khăn giấy');

INSERT INTO ProductDetail (detailInfo) VALUES
('Gói 500g, hạn sử dụng 12 tháng'), ('Chai 1L, không đường'), ('Hũ 200g, cay nhẹ'), ('Gói 100g, vị phô mai'), ('Hộp 1L, có đường'),
('Khay 300g, tươi mới'), ('Túi 500g, đông lạnh'), ('Túi 1kg, sạch'), ('Túi 500g, nhập khẩu'), ('Hộp 400g, đông lạnh'),
('Gói 200g, socola'), ('Cái, lông mềm'), ('Bộ 3 cái, thép không gỉ'), ('Tuýp 150ml, mùi hoa'), ('Chai 250ml, dưỡng ẩm'),
('Gói 1kg, nguyên hạt'), ('Hộp 300g, cá ngừ'), ('Can 2kg, mùi lavender'), ('Cuộn 10 tờ, 3 lớp'), ('Hộp 200 tờ, mềm mại'),
('Gói 400g, hạn sử dụng 6 tháng'), ('Chai 500ml, có ga'), ('Hũ 100g, mặn'), ('Gói 50g, vị bò'), ('Hộp 500ml, ít béo'),
('Khay 200g, thịt heo'), ('Túi 300g, tôm sú'), ('Túi 800g, cải xanh'), ('Túi 1kg, táo'), ('Hộp 500g, gà chiên'),
('Gói 150g, kẹo dẻo'), ('Cái, bàn chải cứng'), ('Cái, thớt nhựa'), ('Chai 500ml, dầu gội'), ('Tuýp 100g, kem đánh răng'),
('Gói 2kg, gạo thơm'), ('Hộp 200g, thịt bò'), ('Can 1.5kg, nước xả'), ('Cuộn 8 tờ, 2 lớp'), ('Hộp 150 tờ, ướt'),
('Gói 300g, mì ống'), ('Chai 330ml, nước trái cây'), ('Hũ 50g, tiêu đen'), ('Gói 80g, khoai chiên'), ('Hộp 800ml, sữa chua'),
('Khay 400g, cá tươi'), ('Túi 200g, mực'), ('Túi 600g, cà rốt'), ('Túi 700g, cam'), ('Hộp 600g, pizza đông lạnh');

INSERT INTO Product (idProductType, idProductDetail, idOfferProduct, name, price, unit, quantity) VALUES
(1, 1, NULL, 'Mì gói Hảo Hảo', 5000, 'Gói', 100),
(2, 2, 3, 'Nước ngọt Pepsi', 12000, 'Chai', 50),
(3, 3, 7, 'Nước mắm Nam Ngư', 35000, 'Chai', 30),
(4, 4, NULL, 'Snack Oishi', 10000, 'Gói', 80),
(5, 5, 1, 'Sữa tươi Vinamilk', 25000, 'Hộp', 40),
(6, 6, 9, 'Thịt ba chỉ', 60000, 'Khay', 20),
(7, 7, NULL, 'Tôm sú đông lạnh', 150000, 'Túi', 15),
(8, 8, 4, 'Rau muống', 10000, 'Túi', 60),
(9, 9, 10, 'Táo Mỹ', 45000, 'Túi', 25),
(10, 10, NULL, 'Gà rán đông lạnh', 80000, 'Hộp', 10),
(11, 11, 2, 'Kẹo socola KitKat', 15000, 'Gói', 70),
(12, 12, NULL, 'Bàn chải đánh răng', 20000, 'Cái', 50),
(13, 13, 6, 'Bộ dao nhà bếp', 90000, 'Bộ', 15),
(14, 14, 8, 'Sữa tắm Lifebuoy', 65000, 'Chai', 30),
(15, 15, NULL, 'Kem dưỡng Nivea', 55000, 'Tuýp', 25),
(16, 16, 5, 'Gạo ST25', 30000, 'Kg', 40),
(17, 17, NULL, 'Cá ngừ đóng hộp', 25000, 'Hộp', 60),
(18, 18, 3, 'Nước giặt OMO', 120000, 'Can', 20),
(19, 19, NULL, 'Giấy vệ sinh Pulppy', 45000, 'Cuộn', 50),
(20, 20, 7, 'Khăn giấy ướt', 30000, 'Hộp', 35),
(1, 21, 1, 'Mì Omachi', 8000, 'Gói', 90),
(2, 22, NULL, 'Coca-Cola', 12000, 'Chai', 45),
(3, 23, 9, 'Muối ớt Chinsu', 15000, 'Hũ', 40),
(4, 24, NULL, 'Bò khô Jack', 35000, 'Gói', 30),
(5, 25, 4, 'Sữa chua Vinamilk', 20000, 'Hộp', 50),
(6, 26, NULL, 'Thịt heo xay', 55000, 'Khay', 25),
(7, 27, 2, 'Mực ống đông lạnh', 120000, 'Túi', 10),
(8, 28, NULL, 'Cải xanh', 12000, 'Túi', 55),
(9, 29, 6, 'Cam sành', 30000, 'Túi', 30),
(10, 30, NULL, 'Pizza đông lạnh', 90000, 'Hộp', 15),
(11, 31, 8, 'Kẹo dẻo Haribo', 25000, 'Gói', 60),
(12, 32, NULL, 'Bàn chải tre', 15000, 'Cái', 40),
(13, 33, 5, 'Thớt nhựa', 35000, 'Cái', 20),
(14, 34, NULL, 'Dầu gội Head & Shoulders', 85000, 'Chai', 25),
(15, 35, 10, 'Kem đánh răng Colgate', 30000, 'Tuýp', 50),
(16, 36, NULL, 'Gạo tám thơm', 28000, 'Kg', 35),
(17, 37, 3, 'Thịt bò hộp', 35000, 'Hộp', 45),
(18, 38, NULL, 'Nước xả Downy', 100000, 'Can', 15),
(19, 39, 7, 'Giấy vệ sinh Bless You', 40000, 'Cuộn', 60),
(20, 40, NULL, 'Khăn giấy lụa', 25000, 'Hộp', 40),
(1, 41, 11, 'Mì ống Barilla', 45000, 'Gói', 20),
(2, 42, NULL, 'Nước ép C2', 10000, 'Chai', 70),
(3, 43, 4, 'Tiêu đen Visaco', 20000, 'Hũ', 35),
(4, 44, NULL, 'Khoai chiên Lay’s', 15000, 'Gói', 80),
(5, 45, 11, 'Sữa chua uống Yakult', 30000, 'Hộp', 50),
(6, 46, NULL, 'Cá basa tươi', 70000, 'Khay', 15),
(7, 47, 2, 'Mực lá đông lạnh', 130000, 'Túi', 10),
(8, 48, NULL, 'Cà rốt', 15000, 'Túi', 40),
(9, 49, 6, 'Táo Fuji', 50000, 'Túi', 20),
(10, 50, NULL, 'Chả cá đông lạnh', 60000, 'Hộp', 25);

INSERT INTO Staff (phone, password, firstName, lastName, address, salary, role, gender) VALUES
('0905123456', 'matkhau123', 'Hùng', 'Nguyễn', '123 Lê Lợi, TP. Huế', 7000000, 'Nhân viên bán hàng', 'Nam'),
('0912345678', 'abc123', 'Lan', 'Trần Thị', '45 Nguyễn Huệ, Hà Nội', 8000000, 'Thu ngân', 'Nữ'),
('0935678901', 'pass456', 'Minh', 'Phạm Văn', '78 Trần Phú, TP. HCM', 6500000, 'Nhân viên kho', 'Nam'),
('0987654321', 'xyz789', 'Hương', 'Lê Thị', '12 Hùng Vương, Đà Nẵng', 7500000, 'Thu ngân', 'Nữ'),
('0941234567', '123456', 'Tuấn', 'Đỗ Anh', '56 Phạm Ngũ Lão, Nha Trang', 9000000, 'Quản lý', 'Nam'),
('0978765432', 'password1', 'Mai', 'Ngô Thị', '89 Nguyễn Trãi, Cần Thơ', 7200000, 'Nhân viên bán hàng', 'Nữ'),
('0923456789', 'abcxyz', 'Dũng', 'Hoàng', '34 Lê Đại Hành, Vinh', 6800000, 'Nhân viên kho', 'Nam'),
('0967890123', 'mk12345', 'Thảo', 'Vũ Thị', '67 Trần Hưng Đạo, Hải Phòng', 7800000, 'Thu ngân', 'Nữ'),
('0918765432', 'pass789', 'Kiên', 'Trương', '23 Nguyễn Văn Cừ, Quy Nhơn', 8500000, 'Quản lý', 'Nam'),
('0932145678', '123abc', 'Linh', 'Bùi Thị', '90 Phạm Văn Đồng, Đà Lạt', 7000000, 'Nhân viên bán hàng', 'Nữ'),
('0909876543', 'xyz123', 'Nam', 'Đặng Văn', '15 Lê Thánh Tôn, Vũng Tàu', 6700000, 'Nhân viên kho', 'Nam'),
('0945678901', 'pass321', 'Ngọc', 'Phan Thị', '48 Hùng Vương, Buôn Ma Thuột', 7600000, 'Thu ngân', 'Nữ'),
('0971234567', 'abc456', 'Phong', 'Lương', '72 Trần Phú, Pleiku', 8200000, 'Quản lý', 'Nam'),
('0926789012', 'mk45678', 'Yến', 'Hà Thị', '19 Nguyễn Đình Chiểu, Hà Tĩnh', 6900000, 'Nhân viên bán hàng', 'Nữ'),
('0965432109', '123xyz', 'Trung', 'Nguyễn Văn', '33 Lê Lai, Thanh Hóa', 6600000, 'Nhân viên kho', 'Nam'),
('0913456789', 'pass654', 'Hạnh', 'Trần Thị', '57 Nguyễn Thị Minh Khai, TP. HCM', 7700000, 'Thu ngân', 'Nữ'),
('0989012345', 'abc789', 'Bình', 'Phạm', '81 Lê Duẩn, Hà Nội', 8800000, 'Quản lý', 'Nam'),
('0934567890', 'xyz456', 'Thủy', 'Lê Thị', '24 Trần Quốc Toản, Đà Nẵng', 7100000, 'Nhân viên bán hàng', 'Nữ'),
('0906789012', 'pass987', 'Hải', 'Hoàng Văn', '68 Nguyễn Huệ, Nha Trang', 6400000, 'Nhân viên kho', 'Nam'),
('0943210987', '123def', 'Trang', 'Ngô Thị', '11 Phạm Hồng Thái, Cần Thơ', 7900000, 'Thu ngân', 'Nữ'),
('0978901234', 'abc321', 'Việt', 'Đỗ', '35 Lê Văn Sỹ, TP. HCM', 8300000, 'Quản lý', 'Nam'),
('0921098765', 'xyz654', 'Hoa', 'Vũ Thị', '59 Trần Đại Nghĩa, Hà Nội', 7300000, 'Nhân viên bán hàng', 'Nữ'),
('0964321098', 'pass123', 'Long', 'Trương Văn', '83 Hùng Vương, Hải Phòng', 6700000, 'Nhân viên kho', 'Nam'),
('0917890123', 'abc654', 'Thu', 'Bùi Thị', '27 Nguyễn Văn Linh, Đà Nẵng', 7800000, 'Thu ngân', 'Nữ'),
('0980123456', 'xyz987', 'Quang', 'Phan', '51 Lê Lợi, Vinh', 8600000, 'Quản lý', 'Nam'),
('0938901234', '123ghi', 'Tâm', 'Hà Thị', '75 Trần Phú, Quy Nhơn', 7000000, 'Nhân viên bán hàng', 'Nữ'),
('0902345678', 'pass456', 'Khang', 'Nguyễn', '99 Nguyễn Trãi, TP. HCM', 6800000, 'Nhân viên kho', 'Nam'),
('0946789012', 'abc987', 'Oanh', 'Trần Thị', '14 Lê Đại Hành, Hà Nội', 7600000, 'Thu ngân', 'Nữ'),
('0973456789', 'xyz321', 'Đạt', 'Phạm Văn', '38 Hùng Vương, Đà Lạt', 8400000, 'Quản lý', 'Nam'),
('0929012345', 'pass789', 'Hiền', 'Lê Thị', '62 Nguyễn Huệ, Nha Trang', 7200000, 'Nhân viên bán hàng', 'Nữ'),
('0961234567', '123jkl', 'Sơn', 'Hoàng', '86 Trần Hưng Đạo, Cần Thơ', 6500000, 'Nhân viên kho', 'Nam'),
('0915678901', 'abc123', 'Phương', 'Ngô Thị', '20 Phạm Ngũ Lão, Hải Phòng', 7700000, 'Thu ngân', 'Nữ'),
('0984321098', 'xyz456', 'Thắng', 'Đỗ Văn', '44 Lê Lai, TP. HCM', 8700000, 'Quản lý', 'Nam'),
('0931234567', 'pass321', 'Nhi', 'Vũ Thị', '68 Nguyễn Đình Chiểu, Hà Nội', 7100000, 'Nhân viên bán hàng', 'Nữ'),
('0908901234', 'abc789', 'Hòa', 'Trương', '92 Trần Quốc Toản, Đà Nẵng', 6900000, 'Nhân viên kho', 'Nam'),
('0944567890', 'xyz987', 'Vy', 'Bùi Thị', '16 Lê Thánh Tôn, Vũng Tàu', 7800000, 'Thu ngân', 'Nữ'),
('0970123456', '123mno', 'Khoa', 'Phan Văn', '40 Nguyễn Văn Cừ, Quy Nhơn', 8500000, 'Quản lý', 'Nam'),
('0926789012', 'pass654', 'Tuyết', 'Hà Thị', '64 Phạm Văn Đồng, Đà Lạt', 7300000, 'Nhân viên bán hàng', 'Nữ'),
('0963456789', 'abc321', 'Cường', 'Nguyễn', '88 Lê Duẩn, TP. HCM', 6700000, 'Nhân viên kho', 'Nam'),
('0919012345', 'xyz654', 'Đào', 'Trần Thị', '22 Nguyễn Thị Minh Khai, Hà Nội', 7600000, 'Thu ngân', 'Nữ'),
('0985678901', 'pass987', 'Tín', 'Phạm', '46 Hùng Vương, Nha Trang', 8800000, 'Quản lý', 'Nam'),
('0932345678', '123pqr', 'Nhung', 'Lê Thị', '70 Trần Phú, Cần Thơ', 7000000, 'Nhân viên bán hàng', 'Nữ'),
('0901234567', 'abc456', 'Hiếu', 'Hoàng Văn', '94 Nguyễn Huệ, Hải Phòng', 6800000, 'Nhân viên kho', 'Nam'),
('0947890123', 'xyz789', 'Bích', 'Ngô Thị', '18 Lê Lợi, Vinh', 7700000, 'Thu ngân', 'Nữ'),
('0974567890', 'pass123', 'Duy', 'Đỗ', '42 Trần Hưng Đạo, TP. HCM', 8600000, 'Quản lý', 'Nam'),
('0920123456', 'abc987', 'Thúy', 'Vũ Thị', '66 Nguyễn Trãi, Hà Nội', 7200000, 'Nhân viên bán hàng', 'Nữ'),
('0968901234', 'xyz321', 'Lộc', 'Trương Văn', '90 Phạm Ngũ Lão, Đà Nẵng', 6500000, 'Nhân viên kho', 'Nam'),
('0916789012', 'pass456', 'Hồng', 'Bùi Thị', '14 Nguyễn Văn Linh, Nha Trang', 7800000, 'Thu ngân', 'Nữ'),
('0983456789', '123stu', 'Vũ', 'Phan', '38 Lê Đại Hành, Cần Thơ', 8700000, 'Quản lý', 'Nam'),
('0939012345', 'abc654', 'Ánh', 'Hà Thị', '62 Trần Quốc Toản, Hải Phòng', 7100000, 'Nhân viên bán hàng', 'Nữ'),
('0397969307', 'admin', 'Cường', 'Lê Mạnh', 'Huỳnh Thị Na, Hóc Môn, TP. HCM', 20000000, 'Quản trị viên', 'Nam');


--  -- ---------------- SELECT -----------
-- select * from provider;
-- select * from customer;
-- select * from staff;
select * from bill;
select * from billinfo;
-- select * from product;
-- select * from productdetail;
-- select * from producttype;
-- select * from offer;
-- select * from productdetail;
--  -- ------------------------------------
--  
-- select * from offer o, offerproduct op
-- where o.id = op.idOffer; 

-- select distinct discount
-- from offerproduct
-- order by discount;

-- select pd.*, pdtype.name as type, pddetail.detailInfo as detail, pdoffer.discount as discount, o.id as idOffer, o.startDate as startDate, o.endDate as endDate
-- from product pd
-- join producttype pdtype on pd.idProductType = pdtype.id
-- join productdetail pddetail on pd.idProductDetail = pddetail.id
-- left join offerproduct pdoffer on pd.idOfferProduct = pdoffer.id
-- left join offer o on pdoffer.idOffer = o.id
-- order by pd.id asc;

-- delete from bill where id = 2;