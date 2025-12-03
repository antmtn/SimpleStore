source create_schema.sql

INSERT INTO Products (product_id, name, price, quantity, image) VALUES
    (1, "chocolate", 3.99, 5, "https://cdn11.bigcommerce.com/s-qbjojecpaq/images/stencil/1280x1280/products/442/1968/Untitled_design_-_2023-06-12T155310.493__67118.1686599665.png?c=1"),
    (2, "shirt", 4.99, 10, "https://m.media-amazon.com/images/I/81-pkxp9h-L._AC_UY1000_.jpg"),
    (3, "sweater", 13.99, 6, "https://cdni.llbean.net/is/image/wim/505183_1155_41?hei=1095&wid=950&resMode=sharp2&defaultImage=llbprod/505183_0_44"),
    (4, "shoes", 49.99, 2, "https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dw96d51de8/images/a_08/M9160_A_08X1.jpg?sw=406&strip=false"),
    (5, "water bottle", 10.99, 100, "https://target.scene7.com/is/image/Target/GUEST_5ad40e9f-b69c-45c5-9d3d-7b2704314d2d"),
    (6, "charger", 14.99, 20, "https://m.media-amazon.com/images/I/41MAlBJ6BhL._AC_UF1000,1000_QL80_.jpg"),
    (7, "backpack", 30.99, 5, "https://solo-ny.com/cdn/shop/files/UBN795-4_HO_887bba68-33a4-48d5-a1b6-b5947c6164ac.jpg?v=1695140976"),
    (8, "laptop", 499.99, 6, "https://i5.walmartimages.com/seo/HP-15-6-Touch-Screen-Laptop-Intel-Core-i3-8GB-Memory-256GB-SSD-Silver-Notebook-PC_435571a0-a644-4f27-9e39-19e6c16f76c0.74549be46a6830f4a58dcf9502abc982.jpeg"),
    (9, "table", 30.99, 5, "https://www.ikea.com/us/en/images/products/mittzon-conference-table-round-birch-veneer-black__1212761_pe910753_s5.jpg"),
    (10, "chiikawa", 14.99, 30, "https://cdn11.bigcommerce.com/s-89ffd/images/stencil/1280x1280/products/157207/639533/sun-arrow-chiikawa-potetama-plush-chiikawa__66691.1737445026.jpg?c=2?imbypass=on"),
    (11, "notebook", 4.99, 8, "https://i5.walmartimages.com/seo/Pen-Gear-Wide-Ruled-1-Subject-Notebook-8-x-10-5-Blue-70-Sheets_d212f980-d46b-4e15-b468-f735ba53eb82.9118a770c90e4c2860b4effcfa52399d.jpeg"),
    (12, "headphones", 19.99, 9, "https://i5.walmartimages.com/seo/VILINICE-Noise-Cancelling-Headphones-Wireless-Bluetooth-Over-Ear-Headphones-with-Microphone-Black-Q8_b994b99c-835f-42fc-8094-9f6be0f9273b.be59955399cdbd1c25011d4a4251ba9b.jpeg"),
    (13, "mug", 35.99, 4, "https://cdn11.bigcommerce.com/s-dbdycdvfge/images/stencil/1000x1000/products/1253/1579/079-00072-II-2__69127.1650472389.jpg?c=1"),
    (14, "pen", 2.99, 40, "https://www.pentel.com/cdn/shop/products/glidewrite-signature-ballpoint-pen-3-pkbx930abp3a-337406.jpg?v=1757189011&width=1946"),
    (15, "keychain", 8.99, 23, "https://omgkawaii.com/cdn/shop/files/omgkawaii-keychains-adorable-bunny-keychain-40751376302293.jpg?v=1701391085&width=900");

INSERT INTO Users (user_id, username, password) VALUES
    (1,  'mango',      'zest77'),
    (2,  'sky7',       'skypass'),
    (3,  'm1l0',       'm1l0pw'),
    (4,  'silverleaf', 'tinycloud'),
    (5,  'nightbyte',  'Passw0rd!'),
    (6,  'u0',         'p0'),
    (7,  'u1',         'p1'),
    (8,  'u2',         'p2'),
    (9,  'u3',         'p3'),
    (10, 'u4',         'p4'),
    (11, 'u5',         'p5'),
    (12, 'u6',         'p6'),
    (13, 'u7',         'p7'),
    (14, 'u8',         'p8'),
    (15, 'u9',         'p9');

INSERT INTO Carts (cart_id, user_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);

INSERT INTO CartItems (cart_id, product_id, quantity) VALUES
    (6,  1, 1),  -- chocolateId
    (6,  2, 2),  -- shirtId
    (6,  5, 4),  -- waterBottleId
    (6, 12, 1),  -- headphonesId
    (7,  3, 1),  -- sweaterId
    (7,  4, 1),  -- shoesId
    (7,  6, 1),  -- chargerId
    (7,  7, 1),  -- backpackId
    (7,  8, 1),  -- laptopId
    (7,  9, 1),  -- tableId
    (7, 10, 3),  -- chiikawaId
    (7, 15, 3),  -- keychainId
    (8, 11, 2),  -- notebookId
    (8, 14, 2),  -- penId
    (9, 13, 1);  -- mugId

INSERT INTO Orders (order_id, user_id) VALUES
    (1,  6),   -- u0Order1
    (2,  7),   -- u1Order1
    (3,  7),   -- u1Order2
    (4,  8),   -- u2Order1
    (5,  8),   -- u2Order2
    (6,  8),   -- u2Order3
    (7,  8),   -- u2Order4
    (8,  8),   -- u2Order5
    (9,  9),   -- u3Order1
    (10, 9),   -- u3Order2
    (11, 9),   -- u3Order3
    (12, 9),   -- u3Order4
    (13, 9),   -- u3Order5
    (14, 9),   -- u3Order6
    (15, 9);   -- u3Order7

INSERT INTO OrderItems (order_id, product_id, quantity) VALUES
    (1, 1, 1),      -- chocolateId, 1
    (2, 1, 5),      -- chocolateId, 5
    (2, 5, 1),      -- waterBottleId, 1
    (3, 6, 1),      -- chargerId, 1
    (4, 2, 1),      -- shirtId, 1
    (5, 8, 1),      -- laptopId, 1
    (6, 4, 1),      -- shoesId, 1
    (7, 7, 1),      -- backpackId, 1
    (8, 12, 1),     -- headphonesId, 1
    (9, 9, 1),      -- tableId, 1
    (10, 11, 3),    -- notebookId, 3
    (10, 14, 2),    -- penId, 2
    (11, 10, 10),   -- chiikawaId, 10
    (12, 15, 2),    -- keychainId, 2
    (13, 6, 1),     -- chargerId, 1
    (13, 8, 1),     -- laptopId, 1
    (14, 15, 6),    -- keychainId, 6
    (15, 1, 11);    -- chocolateId, 11
