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
                             dateCreate DATE NOT NULL,
                             total DOUBLE,
                             state VARCHAR(100) CHARACTER SET UTF8MB4 default 'Chưa thanh toán', -- "Chưa thanh toán" or "Đã thanh toán"

                             FOREIGN KEY (idStaff) REFERENCES Staff(id),
                             FOREIGN KEY (idProvider) REFERENCES Provider(id)
);

-- Tạo bảng ImportOrderDetail
CREATE TABLE ImportOrderDetail (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   idProduct INT NOT NULL,
                                   idImportOrder INT NOT NULL,

                                   quantity INT,
                                   price DOUBLE,
                                   unit VARCHAR(100) CHARACTER SET UTF8MB4,
                                   nameProduct VARCHAR(100) CHARACTER SET UTF8MB4,
                                   FOREIGN KEY (idProduct) REFERENCES Product(id),
                                   FOREIGN KEY (idImportOrder) REFERENCES ImportOrder(id)
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

INSERT INTO Bill (idStaff, idOfferBill, idCustomer, dateCreate, price, state) VALUES
                                                                                  (12, NULL, 45, '2024-03-15', 135000, 'Đã thanh toán'),
                                                                                  (25, 3, NULL, '2024-06-22', 245000, 'Đã thanh toán'),
                                                                                  (47, 7, 22, '2024-09-10', 78000, 'Đã thanh toán'),
                                                                                  (33, NULL, 18, '2024-11-05', 320000, 'Đã thanh toán'),
                                                                                  (9, 1, NULL, '2024-12-20', 165000, 'Đã thanh toán'),
                                                                                  (41, 4, 37, '2025-01-12', 210000, 'Đã thanh toán'),
                                                                                  (15, NULL, 29, '2024-04-18', 95000, 'Đã thanh toán'),
                                                                                  (28, 6, NULL, '2024-07-30', 400000, 'Đã thanh toán'),
                                                                                  (36, 2, 14, '2024-10-25', 180000, 'Đã thanh toán'),
                                                                                  (4, NULL, 41, '2025-02-28', 275000, 'Đã thanh toán'),
                                                                                  (50, 8, NULL, '2024-05-09', 110000, 'Đã thanh toán'),
                                                                                  (22, 5, 33, '2024-08-14', 350000, 'Đã thanh toán'),
                                                                                  (17, NULL, 8, '2024-02-27', 145000, 'Đã thanh toán'),
                                                                                  (31, 3, NULL, '2024-12-01', 220000, 'Đã thanh toán'),
                                                                                  (44, 7, 26, '2025-03-10', 185000, 'Đã thanh toán'),
                                                                                  (6, NULL, 49, '2024-01-30', 300000, 'Đã thanh toán'),
                                                                                  (29, 4, NULL, '2024-06-05', 90000, 'Đã thanh toán'),
                                                                                  (13, 1, 15, '2024-09-19', 260000, 'Đã thanh toán'),
                                                                                  (38, NULL, 42, '2025-04-01', 155000, 'Đã thanh toán'),
                                                                                  (20, 6, NULL, '2024-11-15', 330000, 'Đã thanh toán');

-- Thêm chi tiết hóa đơn vào bảng BillInfo
INSERT INTO BillInfo (idBill, idProduct, price, quantity, discount, total, unit, nameProduct) VALUES
-- Hóa đơn 1
(1, 1, 5000, 5, 0, 25000, 'Gói', 'Mì gói Hảo Hảo'),
(1, 2, 12000, 5, 10, 54000, 'Chai', 'Nước ngọt Pepsi'),
(1, 4, 10000, 6, 5, 57000, 'Gói', 'Snack Oishi'),
-- Hóa đơn 2
(2, 5, 25000, 4, 0, 100000, 'Hộp', 'Sữa tươi Vinamilk'),
(2, 9, 45000, 3, 10, 121500, 'Túi', 'Táo Mỹ'),
(2, 11, 15000, 2, 0, 30000, 'Gói', 'Kẹo socola KitKat'),
-- Hóa đơn 3
(3, 8, 10000, 3, 0, 30000, 'Túi', 'Rau muống'),
(3, 17, 25000, 2, 10, 45000, 'Hộp', 'Cá ngừ đóng hộp'),
-- Hóa đơn 4
(4, 6, 60000, 3, 5, 171000, 'Khay', 'Thịt ba chỉ'),
(4, 14, 65000, 2, 0, 130000, 'Chai', 'Sữa tắm Lifebuoy'),
(4, 19, 45000, 1, 0, 45000, 'Cuộn', 'Giấy vệ sinh Pulppy'),
-- Hóa đơn 5
(5, 10, 80000, 2, 10, 144000, 'Hộp', 'Gà rán đông lạnh'),
(5, 12, 20000, 1, 5, 19000, 'Cái', 'Bàn chải đánh răng'),
(5, 21, 8000, 3, 0, 24000, 'Gói', 'Mì Omachi'),
-- Hóa đơn 6
(6, 7, 150000, 1, 0, 150000, 'Túi', 'Tôm sú đông lạnh'),
(6, 22, 12000, 5, 0, 60000, 'Chai', 'Coca-Cola'),
-- Hóa đơn 7
(7, 4, 10000, 5, 10, 45000, 'Gói', 'Snack Oishi'),
(7, 23, 15000, 4, 0, 60000, 'Hũ', 'Muối ớt Chinsu'),
-- Hóa đơn 8
(8, 13, 90000, 2, 5, 171000, 'Bộ', 'Bộ dao nhà bếp'),
(8, 18, 120000, 2, 0, 240000, 'Can', 'Nước giặt OMO'),
-- Hóa đơn 9
(9, 25, 20000, 5, 0, 100000, 'Hộp', 'Sữa chua Vinamilk'),
(9, 29, 30000, 3, 10, 81000, 'Túi', 'Cam sành'),
-- Hóa đơn 10
(10, 16, 30000, 5, 0, 150000, 'Kg', 'Gạo ST25'),
(10, 20, 30000, 5, 0, 150000, 'Hộp', 'Khăn giấy ướt'),
-- Hóa đơn 11
(11, 31, 25000, 2, 0, 50000, 'Gói', 'Kẹo dẻo Haribo'),
(11, 35, 30000, 2, 0, 60000, 'Tuýp', 'Kem đánh răng Colgate'),
-- Hóa đơn 12
(12, 27, 120000, 2, 5, 228000, 'Túi', 'Mực ống đông lạnh'),
(12, 34, 85000, 1, 0, 85000, 'Chai', 'Dầu gội Head & Shoulders'),
-- Hóa đơn 13
(13, 24, 35000, 3, 0, 105000, 'Gói', 'Bò khô Jack'),
(13, 28, 12000, 4, 0, 48000, 'Túi', 'Cải xanh'),
-- Hóa đơn 14
(14, 30, 90000, 2, 0, 180000, 'Hộp', 'Pizza đông lạnh'),
(14, 44, 15000, 3, 10, 40500, 'Gói', 'Khoai chiên Lay’s'),
-- Hóa đơn 15
(15, 9, 45000, 3, 5, 128250, 'Túi', 'Táo Mỹ'),
(15, 47, 130000, 1, 10, 117000, 'Túi', 'Mực lá đông lạnh'),
-- Hóa đơn 16
(16, 46, 70000, 3, 0, 210000, 'Khay', 'Cá basa tươi'),
(16, 49, 50000, 2, 0, 100000, 'Túi', 'Táo Fuji'),
-- Hóa đơn 17
(17, 2, 12000, 5, 0, 60000, 'Chai', 'Nước ngọt Pepsi'),
(17, 21, 8000, 4, 0, 32000, 'Gói', 'Mì Omachi'),
-- Hóa đơn 18
(18, 5, 25000, 4, 0, 100000, 'Hộp', 'Sữa tươi Vinamilk'),
(18, 14, 65000, 2, 0, 130000, 'Chai', 'Sữa tắm Lifebuoy'),
(18, 33, 35000, 1, 0, 35000, 'Cái', 'Thớt nhựa'),
-- Hóa đơn 19
(19, 11, 15000, 5, 10, 67500, 'Gói', 'Kẹo socola KitKat'),
(19, 39, 40000, 2, 0, 80000, 'Cuộn', 'Giấy vệ sinh Bless You'),
-- Hóa đơn 20
(20, 18, 120000, 2, 0, 240000, 'Can', 'Nước giặt OMO'),
(20, 45, 30000, 3, 0, 90000, 'Hộp', 'Sữa chua uống Yakult');

INSERT INTO ImportOrder (idStaff, idProvider, dateCreate, total, state) VALUES
                                                                            (1, 1, '2024-03-15', 0, 'Đã thanh toán'),
                                                                            (5, 3, '2024-06-22', 0, 'Đã thanh toán'),
                                                                            (10, 7, '2024-09-10', 0, 'Đã thanh toán'),
                                                                            (15, 2, '2024-11-05', 0, 'Đã thanh toán'),
                                                                            (20, 5, '2025-01-12', 0, 'Đã thanh toán'),
                                                                            (25, 9, '2024-02-28', 0, 'Đã thanh toán'),
                                                                            (30, 12, '2024-07-19', 0, 'Đã thanh toán'),
                                                                            (35, 15, '2024-10-03', 0, 'Đã thanh toán'),
                                                                            (40, 18, '2025-03-01', 0, 'Đã thanh toán'),
                                                                            (45, 20, '2024-04-17', 0, 'Đã thanh toán'),
                                                                            (2, 4, '2024-08-11', 0, 'Đã thanh toán'),
                                                                            (7, 6, '2024-12-25', 0, 'Đã thanh toán'),
                                                                            (12, 8, '2025-02-14', 0, 'Đã thanh toán'),
                                                                            (17, 10, '2024-05-30', 0, 'Đã thanh toán'),
                                                                            (22, 13, '2024-09-27', 0, 'Đã thanh toán'),
                                                                            (27, 16, '2024-01-20', 0, 'Đã thanh toán'),
                                                                            (32, 19, '2024-06-08', 0, 'Đã thanh toán'),
                                                                            (37, 1, '2024-11-15', 0, 'Đã thanh toán'),
                                                                            (42, 3, '2025-04-10', 0, 'Đã thanh toán'),
                                                                            (47, 5, '2024-03-02', 0, 'Đã thanh toán'),
                                                                            (3, 7, '2024-07-04', 0, 'Đã thanh toán'),
                                                                            (8, 9, '2024-10-20', 0, 'Đã thanh toán'),
                                                                            (13, 11, '2025-01-08', 0, 'Đã thanh toán'),
                                                                            (18, 14, '2024-04-25', 0, 'Đã thanh toán'),
                                                                            (23, 17, '2024-08-16', 0, 'Đã thanh toán'),
                                                                            (28, 20, '2024-12-10', 0, 'Đã thanh toán'),
                                                                            (33, 2, '2025-03-15', 0, 'Đã thanh toán'),
                                                                            (38, 4, '2024-02-10', 0, 'Đã thanh toán'),
                                                                            (43, 6, '2024-06-28', 0, 'Đã thanh toán'),
                                                                            (48, 8, '2024-09-05', 0, 'Đã thanh toán'),
                                                                            (4, 10, '2024-11-22', 0, 'Đã thanh toán'),
                                                                            (9, 12, '2025-02-20', 0, 'Đã thanh toán'),
                                                                            (14, 15, '2024-05-12', 0, 'Đã thanh toán'),
                                                                            (19, 18, '2024-08-03', 0, 'Đã thanh toán'),
                                                                            (24, 1, '2024-12-28', 0, 'Đã thanh toán'),
                                                                            (29, 3, '2025-04-05', 0, 'Đã thanh toán'),
                                                                            (34, 5, '2024-03-25', 0, 'Đã thanh toán'),
                                                                            (39, 7, '2024-07-15', 0, 'Đã thanh toán'),
                                                                            (44, 9, '2024-10-08', 0, 'Đã thanh toán'),
                                                                            (49, 11, '2025-01-25', 0, 'Đã thanh toán'),
                                                                            (6, 13, '2024-04-10', 0, 'Đã thanh toán'),
                                                                            (11, 16, '2024-08-22', 0, 'Đã thanh toán'),
                                                                            (16, 19, '2024-11-10', 0, 'Đã thanh toán'),
                                                                            (21, 2, '2025-03-08', 0, 'Đã thanh toán'),
                                                                            (26, 4, '2024-02-15', 0, 'Đã thanh toán'),
                                                                            (31, 6, '2024-06-05', 0, 'Đã thanh toán'),
                                                                            (36, 8, '2024-09-18', 0, 'Đã thanh toán'),
                                                                            (41, 10, '2024-12-15', 0, 'Đã thanh toán'),
                                                                            (46, 12, '2025-02-05', 0, 'Đã thanh toán'),
                                                                            (50, 14, '2024-05-20', 0, 'Đã thanh toán');

-- Insert ImportOrderDetail records with nameProduct
INSERT INTO ImportOrderDetail (idProduct, idImportOrder, quantity, price, unit, nameProduct) VALUES
-- Order 1
(5, 1, 50, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(1, 1, 100, 5500, 'Gói', 'Mì gói Hảo Hảo'),
(11, 1, 30, 16500, 'Gói', 'Kẹo socola KitKat'),
-- Order 2
(2, 2, 40, 13200, 'Chai', 'Nước ngọt Pepsi'),
(25, 2, 60, 22000, 'Hộp', 'Sữa chua Vinamilk'),
-- Order 3
(14, 3, 20, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(18, 3, 15, 132000, 'Can', 'Nước giặt OMO'),
(39, 3, 50, 44000, 'Cuộn', 'Giấy vệ sinh Bless You'),
-- Order 4
(9, 4, 25, 49500, 'Túi', 'Táo Mỹ'),
(29, 4, 30, 33000, 'Túi', 'Cam sành'),
-- Order 5
(6, 5, 10, 66000, 'Khay', 'Thịt ba chỉ'),
(26, 5, 15, 60500, 'Khay', 'Thịt heo xay'),
(46, 5, 20, 77000, 'Khay', 'Cá basa tươi'),
-- Order 6
(4, 6, 80, 11000, 'Gói', 'Snack Oishi'),
(44, 6, 60, 16500, 'Gói', 'Khoai chiên Lay’s'),
-- Order 7
(17, 7, 50, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(37, 7, 40, 38500, 'Hộp', 'Thịt bò hộp'),
-- Order 8
(13, 8, 10, 99000, 'Bộ', 'Bộ dao nhà bếp'),
(33, 8, 15, 38500, 'Cái', 'Thớt nhựa'),
-- Order 9
(19, 9, 60, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(20, 9, 40, 33000, 'Hộp', 'Khăn giấy ướt'),
-- Order 10
(8, 10, 70, 11000, 'Túi', 'Rau muống'),
(28, 10, 50, 13200, 'Túi', 'Cải xanh'),
(48, 10, 30, 16500, 'Túi', 'Cà rốt'),
-- Order 11
(3, 11, 20, 38500, 'Chai', 'Nước mắm Nam Ngư'),
(23, 11, 30, 16500, 'Hũ', 'Muối ớt Chinsu'),
-- Order 12
(7, 12, 10, 165000, 'Túi', 'Tôm sú đông lạnh'),
(27, 12, 15, 132000, 'Túi', 'Mực ống đông lạnh'),
(47, 12, 10, 143000, 'Túi', 'Mực lá đông lạnh'),
-- Order 13
(15, 13, 25, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(35, 13, 40, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
-- Order 14
(10, 14, 15, 88000, 'Hộp', 'Gà rán đông lạnh'),
(30, 14, 10, 99000, 'Hộp', 'Pizza đông lạnh'),
-- Order 15
(16, 15, 50, 33000, 'Kg', 'Gạo ST25'),
(36, 15, 40, 38000, 'Kg', 'Gạo tám thơm'),
-- Order 16
(21, 16, 90, 8800, 'Gói', 'Mì Omachi'),
(41, 16, 20, 49500, 'Gói', 'Mì ống Barilla'),
-- Order 17
(24, 17, 30, 38500, 'Gói', 'Bò khô Jack'),
(44, 17, 50, 16500, 'Gói', 'Khoai chiên Lay’s'),
-- Order 18
(5, 18, 40, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(45, 18, 50, 33000, 'Hộp', 'Sữa chua uống Yakult'),
-- Order 19
(2, 19, 60, 13200, 'Chai', 'Nước ngọt Pepsi'),
(22, 19, 50, 13200, 'Chai', 'Coca-Cola'),
(42, 19, 70, 11000, 'Chai', 'Nước ép C2'),
-- Order 20
(9, 20, 20, 49500, 'Túi', 'Táo Mỹ'),
(49, 20, 15, 55000, 'Túi', 'Táo Fuji'),
-- Order 21
(14, 21, 30, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(34, 21, 20, 93500, 'Chai', 'Dầu gội Head & Shoulders'),
-- Order 22
(6, 22, 12, 66000, 'Khay', 'Thịt ba chỉ'),
(26, 22, 18, 60500, 'Khay', 'Thịt heo xay'),
-- Order 23
(11, 23, 50, 16500, 'Gói', 'Kẹo socola KitKat'),
(31, 23, 40, 27500, 'Gói', 'Kẹo dẻo Haribo'),
-- Order 24
(8, 24, 60, 11000, 'Túi', 'Rau muống'),
(28, 24, 50, 13200, 'Túi', 'Cải xanh'),
-- Order 25
(17, 25, 40, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(37, 25, 30, 38500, 'Hộp', 'Thịt bò hộp'),
-- Order 26
(19, 26, 50, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(39, 26, 60, 44000, 'Cuộn', 'Giấy vệ sinh Bless You'),
-- Order 27
(4, 27, 70, 11000, 'Gói', 'Snack Oishi'),
(24, 27, 20, 38500, 'Gói', 'Bò khô Jack'),
-- Order 28
(13, 28, 12, 99000, 'Bộ', 'Bộ dao nhà bếp'),
(33, 28, 18, 38500, 'Cái', 'Thớt nhựa'),
-- Order 29
(5, 29, 50, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(25, 29, 40, 22000, 'Hộp', 'Sữa chua Vinamilk'),
-- Order 30
(2, 30, 50, 13200, 'Chai', 'Nước ngọt Pepsi'),
(22, 30, 40, 13200, 'Chai', 'Coca-Cola'),
-- Order 31
(7, 31, 12, 165000, 'Túi', 'Tôm sú đông lạnh'),
(27, 31, 10, 132000, 'Túi', 'Mực ống đông lạnh'),
-- Order 32
(15, 32, 30, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(35, 32, 50, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
-- Order 33
(10, 33, 20, 88000, 'Hộp', 'Gà rán đông lạnh'),
(50, 33, 25, 66000, 'Hộp', 'Chả cá đông lạnh'),
-- Order 34
(16, 34, 60, 33000, 'Kg', 'Gạo ST25'),
(36, 34, 50, 30800, 'Kg', 'Gạo tám thơm'),
-- Order 35
(21, 35, 100, 8800, 'Gói', 'Mì Omachi'),
(41, 35, 25, 49500, 'Gói', 'Mì ống Barilla'),
-- Order 36
(3, 36, 25, 38500, 'Chai', 'Nước mắm Nam Ngư'),
(23, 36, 35, 16500, 'Hũ', 'Muối ớt Chinsu'),
-- Order 37
(9, 37, 30, 49500, 'Túi', 'Táo Mỹ'),
(29, 37, 40, 33000, 'Túi', 'Cam sành'),
-- Order 38
(14, 38, 25, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(34, 38, 15, 93500, 'Chai', 'Dầu gội Head & Shoulders'),
-- Order 39
(6, 39, 15, 66000, 'Khay', 'Thịt ba chỉ'),
(46, 39, 20, 77000, 'Khay', 'Cá basa tươi'),
-- Order 40
(11, 40, 60, 16500, 'Gói', 'Kẹo socola KitKat'),
(31, 40, 50, 27500, 'Gói', 'Kẹo dẻo Haribo'),
-- Order 41
(8, 41, 80, 11000, 'Túi', 'Rau muống'),
(48, 41, 40, 16500, 'Túi', 'Cà rốt'),
-- Order 42
(17, 42, 50, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(37, 42, 40, 38500, 'Hộp', 'Thịt bò hộp'),
-- Order 43
(19, 43, 70, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(20, 43, 50, 33000, 'Hộp', 'Khăn giấy ướt'),
-- Order 44
(4, 44, 90, 11000, 'Gói', 'Snack Oishi'),
(44, 44, 60, 16500, 'Gói', 'Khoai chiên Lay’s'),
-- Order 45
(5, 45, 60, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(45, 45, 50, 33000, 'Hộp', 'Sữa chua uống Yakult'),
-- Order 46
(2, 46, 70, 13200, 'Chai', 'Nước ngọt Pepsi'),
(42, 46, 80, 11000, 'Chai', 'Nước ép C2'),
-- Order 47
(7, 47, 15, 165000, 'Túi', 'Tôm sú đông lạnh'),
(47, 47, 10, 143000, 'Túi', 'Mực lá đông lạnh'),
-- Order 48
(15, 48, 35, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(35, 48, 60, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
-- Order 49
(10, 49, 25, 88000, 'Hộp', 'Gà rán đông lạnh'),
(30, 49, 15, 99000, 'Hộp', 'Pizza đông lạnh'),
-- Order 50
(16, 50, 70, 33000, 'Kg', 'Gạo ST25'),
(36, 50, 60, 30800, 'Kg', 'Gạo tám thơm');

-- Update total for each ImportOrder
UPDATE ImportOrder io
SET total = (
    SELECT SUM(iod.quantity * iod.price)
    FROM ImportOrderDetail iod
    WHERE iod.idImportOrder = io.id
)
WHERE id BETWEEN 1 AND 50;

--  -- ---------------- SELECT -----------
-- select * from provider;
-- select * from customer;
-- select * from staff;
-- select * from bill;
-- select * from billinfo;
-- select * from product;
-- select * from productdetail;
-- select * from producttype;
-- select * from offer;
-- select * from offerproduct;
-- select * from offerbill;
-- select * from productdetail;
--  -- ------------------------------------
--
-- select * from offer o, offerproduct op
-- where o.id = op.idOffer;

-- select distinct discount
-- from offerproduct
-- order by discount;

-- select product.*, producttype.*, productdetail.*, offerproduct.*, offer.*
-- from product
-- join producttype on product.idProductType = producttype.id
-- join productdetail on product.idProductDetail = productdetail.id
-- left join offerproduct on product.idOfferProduct = offerproduct.id
-- left join offer on offerproduct.idOffer = offer.id
-- order by product.id asc;
--
-- select bill.*, staff.*, offerbill.*, offer.*, customer.*
-- from bill
-- join staff on bill.idStaff = staff.id
-- left join offerbill on bill.idOfferBill = offerbill.id
-- left join offer on offerbill.idOffer = offer.id
-- left join customer on bill.idCustomer = customer.id;
--
-- select offerproduct.*, offer.*
-- from offerproduct, offer
-- where offerproduct.idOffer = offer.id
-- order by offerproduct.id;
--
-- select offerbill.*, offer.*
-- from offerbill, offer
-- where offerbill.idOffer = offer.id
-- order by offerbill.id;

-- select * from importorder;
--
-- select sum(importorder.total) as result
-- from importorder;
--
-- select sum(bill.price) as result
-- from bill;
--
-- select sum(total) as result
-- from importorder
-- where (month(dateCreate) between 1 and 3) and (dateCreate between now() and now())