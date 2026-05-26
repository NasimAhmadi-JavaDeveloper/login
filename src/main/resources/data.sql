MERGE INTO category (id, name, parent_id, created_at, updated_at, version)
    VALUES (1, 'Electronics', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (2, 'Clothing', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (3, 'Books', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (4, 'Home & Garden', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (5, 'Sports', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (6, 'Toys & Games', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (7, 'Beauty & Health', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (8, 'Automotive', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

MERGE INTO category (id, name, parent_id, created_at, updated_at, version)
    VALUES (9, 'Smartphones', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (10, 'Laptops', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (11, 'Headphones', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (12, 'Cameras', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (13, 'Tablets', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (14, 'TVs & Monitors', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (15, 'Men', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (16, 'Women', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (17, 'Kids', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (18, 'Accessories', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (19, 'Shoes', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (20, 'Fiction', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (21, 'Non-Fiction', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (22, 'Educational', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (23, 'Children Books', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (24, 'Furniture', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (25, 'Kitchen', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (26, 'Garden Tools', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (27, 'Decor', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (28, 'Football', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (29, 'Basketball', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (30, 'Fitness', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (31, 'Running', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (32, 'Swimming', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

MERGE INTO category (id, name, parent_id, created_at, updated_at, version)
    VALUES (33, 'Apple', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (34, 'Samsung', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (35, 'Xiaomi', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (36, 'Google Pixel', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (37, 'OnePlus', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (38, 'Dell', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (39, 'HP', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (40, 'Lenovo', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (41, 'Apple MacBook', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (42, 'Asus', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (43, 'Shirts', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (44, 'Pants', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (45, 'Jackets', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (46, 'Jeans', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (47, 'Suits', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (48, 'Dresses', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (49, 'Tops', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (50, 'Skirts', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (51, 'Handbags', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    (52, 'Science Fiction', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (53, 'Mystery', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (54, 'Romance', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (55, 'Thriller', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (56, 'Fantasy', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
----
MERGE INTO inventory (id, address, stock_quantity, created_at, updated_at, version)
    VALUES
    (1, 'Tehran, Iran, Main Warehouse, Unit 101', 1000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (2, 'Tehran, Iran, North Branch, Unit 45', 500, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (3, 'Tehran, Iran, South Branch, Unit 78', 750, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (4, 'Isfahan, Iran, Central Warehouse, Block C', 400, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (5, 'Mashhad, Iran, East Warehouse, Suite 200', 350, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (6, 'Shiraz, Iran, South Central Warehouse, Door 15', 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (7, 'Tabriz, Iran, Northwest Warehouse, Floor 2', 250, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (8, 'Ahvaz, Iran, Southwest Warehouse, Unit 33', 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (9, 'Qom, Iran, Central Depot, Storage A', 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (10,'Karaj, Iran, West Satellite Warehouse, Lot 12', 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

----
MERGE INTO product (id, name, price, inventory_id, category_id, created_at, updated_at, version)
    VALUES
    -- Smartphones (Electronics > Smartphones)
    (1, 'iPhone 15 Pro', 1200.00, 1, 33, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (2, 'Samsung Galaxy S24 Ultra', 1199.00, 1, 34, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (3, 'Xiaomi 14 Pro', 899.00, 2, 35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (4, 'Google Pixel 8 Pro', 999.00, 3, 36, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (5, 'OnePlus 12', 799.00, 2, 37, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Laptops (Electronics > Laptops)
    (6, 'Dell XPS 15', 1899.00, 1, 38, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (7, 'HP Spectre x360', 1699.00, 2, 39, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (8, 'Lenovo ThinkPad X1 Carbon', 1799.00, 3, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (9, 'MacBook Pro 16', 2499.00, 1, 41, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (10, 'ASUS ROG Zephyrus', 1999.00, 2, 42, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Headphones (Electronics > Headphones)
    (11, 'Sony WH-1000XM5', 399.00, 4, 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (12, 'Apple AirPods Pro 2', 249.00, 5, 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (13, 'Bose QuietComfort Ultra', 429.00, 6, 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Cameras (Electronics > Cameras)
    (14, 'Canon EOS R5', 3899.00, 7, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (15, 'Sony A7 IV', 2499.00, 8, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (16, 'Nikon Z8', 3999.00, 9, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Men's Clothing
    (17, 'Men Casual Shirt', 49.99, 1, 43, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (18, 'Men Slim Fit Jeans', 79.99, 2, 46, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (19, 'Men Leather Jacket', 199.99, 3, 45, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (20, 'Men Business Suit', 499.99, 1, 47, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Women's Clothing
    (21, 'Women Summer Dress', 89.99, 4, 48, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (22, 'Women Silk Blouse', 69.99, 5, 49, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (23, 'Women Leather Handbag', 159.99, 6, 51, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Books
    (24, 'Dune: Science Fiction Classic', 24.99, 7, 52, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (25, 'The Silent Patient (Mystery)', 18.99, 8, 53, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (26, 'The Love Hypothesis (Romance)', 15.99, 9, 54, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (27, 'The Thursday Murder Club (Thriller)', 16.99, 10, 55, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (28, 'The Name of the Wind (Fantasy)', 22.99, 1, 56, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Sports Equipment
    (29, 'Nike Football', 29.99, 2, 28, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (30, 'Spalding Basketball', 34.99, 3, 29, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (31, 'Yoga Mat', 24.99, 4, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (32, 'Running Shoes', 129.99, 5, 31, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- Home & Garden
    (33, 'Modern Sofa', 899.99, 6, 24, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (34, 'Non-Stick Cookware Set', 199.99, 7, 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (35, 'Electric Lawn Mower', 349.99, 8, 26, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (36, 'Decorative Wall Art', 49.99, 9, 27, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
------
MERGE INTO post (id, user_id, caption, visit_count, tag, created_at, updated_at, version)
    VALUES
    -- User 1 (admin) posts
    (1, 1, 'Welcome to my blog! Excited to share my thoughts with you all', 150, 'welcome,introduction,blog', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (2, 1, 'Spring Boot best practices for 2024', 320, 'springboot,java,backend', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (3, 1, 'Understanding microservices architecture', 280, 'microservices,architecture,cloud', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),

    -- User 2 (john_doe) posts
    (4, 2, 'My new iPhone 15 Pro review after 1 month', 450, 'iphone,review,tech', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (5, 2, 'Top 10 programming books you must read', 230, 'books,programming,learning', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    (6, 2, 'How I learned Spring Boot in 30 days', 510, 'springboot,learning,journey', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

--     -- User 3 (jane_smith) posts
--     (7, 3, 'Beautiful spring fashion collection 2024', 890, 'fashion,spring,style', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (8, 3, 'Makeup tutorial: Natural everyday look', 1200, 'makeup,beauty,tutorial', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (9, 3, 'Summer vacation outfit ideas', 670, 'summer,fashion,vacation', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 4 (mike_wilson) posts
--     (10, 4, 'Best budget smartphones under $500', 340, 'smartphones,budget,tech', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (11, 4, 'Gaming PC build guide 2024', 560, 'gaming,pcbuild,hardware', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 5 (sarah_johnson) posts
--     (12, 5, 'Healthy meal prep ideas for the week', 780, 'healthy,mealprep,cooking', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (13, 5, 'My fitness journey: 6 months transformation', 1250, 'fitness,transformation,gym', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 6 (robert_brown) posts
--     (14, 6, 'Travel guide: Best places in Europe', 920, 'travel,europe,guide', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (15, 6, 'Budget travel tips for students', 430, 'travel,budget,students', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 7 (emily_davis) posts
--     (16, 7, 'React vs Vue vs Angular 2024 comparison', 1120, 'react,vue,angular,frontend', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (17, 7, 'Building a REST API with Spring Boot', 890, 'springboot,api,rest', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 8 (chris_white) posts
--     (18, 8, 'Photography tips for beginners', 340, 'photography,tips,beginner', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (19, 8, 'Best cameras for travel photography', 230, 'cameras,photography,travel', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 9 (moderator) posts
--     (20, 9, 'Community guidelines update', 560, 'community,rules,update', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (21, 9, 'How to report inappropriate content', 210, 'report,safety,community', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 10 (tech_guru) posts
--     (22, 10, 'Advanced Java programming techniques', 670, 'java,advanced,programming', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (23, 10, 'Debugging tips every developer should know', 890, 'debugging,developer,tips', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 11 (travel_lover) posts
--     (24, 11, 'Hidden gems in Southeast Asia', 1230, 'travel,southeastasia,hidden', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (25, 11, 'Packing guide for 2-week trips', 560, 'packing,travel,hacks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 12 (fitness_coach) posts
--     (26, 12, '10-minute morning workout routine', 1890, 'workout,fitness,morning', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (27, 12, 'Nutrition guide for muscle gain', 760, 'nutrition,gym,muscle', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- User 13 (locked_user) posts
--     (28, 13, 'Why I love this platform', 45, 'appreciation,community', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

---------
-- MERGE INTO comment (id, post_id, user_id, comment_text, emoji, created_at, updated_at, version)
--     VALUES
--     -- Comments on Post 1 (iPhone 15 Pro Review)
--     (1, 1, 2, 'Great review! I love my iPhone 15 Pro too!', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (2, 1, 3, 'How is the battery life?', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (3, 1, 4, 'The camera quality is amazing!', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 2 (Spring Collection)
--     (4, 2, 5, 'Love the new collection! When will it be available?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (5, 2, 6, 'The quality looks amazing', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 3 (Travel Tips)
--     (6, 3, 7, 'Great tips! Will use them on my next trip', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (7, 3, 8, 'Have you been to Japan?', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (8, 3, 2, 'Thanks for sharing!', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 4 (Coding Tutorial)
--     (9, 4, 9, 'This helped me a lot! Thank you', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (10, 4, 10, 'Can you make a video about Spring Boot?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (11, 4, 1, 'Great tutorial as always!', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 5 (Fitness Journey)
--     (12, 5, 3, 'Keep going! You are doing great', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (13, 5, 4, 'What is your diet plan?', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (14, 5, 5, 'Inspiring! I started my journey too', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 6 (Movie Review)
--     (15, 6, 6, 'I agree, best movie of the year!', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (16, 6, 7, 'Have you watched the sequel?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 7 (React Tutorial)
--     (17, 7, 8, 'React is amazing! Thanks for the tutorial', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (18, 7, 9, 'Can you cover Redux next?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 8 (Food Review)
--     (19, 8, 10, 'This looks delicious!', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (20, 8, 2, 'What is the recipe?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (21, 8, 3, 'I need to try this place', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 9 (Fashion Tips)
--     (22, 9, 4, 'Love your style!', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (23, 9, 5, 'Where did you buy that jacket?', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Comments on Post 10 (Investment Advice)
--     (24, 10, 6, 'Very helpful advice, thank you!', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (25, 10, 7, 'What do you think about crypto?', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (26, 10, 8, 'Long term investing is key', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

----
-- MERGE INTO follow (id, from_id, to_id, created_at)
--     VALUES
--     -- User 1 (admin) followers
--     (1, 2, 1, CURRENT_TIMESTAMP), -- User 2 follows User 1
--     (2, 3, 1, CURRENT_TIMESTAMP), -- User 3 follows User 1
--     (3, 4, 1, CURRENT_TIMESTAMP), -- User 4 follows User 1
--     (4, 5, 1, CURRENT_TIMESTAMP), -- User 5 follows User 1
--     (5, 6, 1, CURRENT_TIMESTAMP), -- User 6 follows User 1
--
-- -- User 2 (john_doe) followers and following
--     (6, 1, 2, CURRENT_TIMESTAMP), -- User 1 follows User 2
--     (7, 3, 2, CURRENT_TIMESTAMP), -- User 3 follows User 2
--     (8, 7, 2, CURRENT_TIMESTAMP), -- User 7 follows User 2
--     (9, 8, 2, CURRENT_TIMESTAMP), -- User 8 follows User 2
--
-- -- User 3 (jane_smith) followers
--     (10, 1, 3, CURRENT_TIMESTAMP), -- User 1 follows User 3
--     (11, 2, 3, CURRENT_TIMESTAMP), -- User 2 follows User 3
--     (12, 4, 3, CURRENT_TIMESTAMP), -- User 4 follows User 3
--     (13, 5, 3, CURRENT_TIMESTAMP), -- User 5 follows User 3
--     (14, 6, 3, CURRENT_TIMESTAMP), -- User 6 follows User 3
--     (15, 7, 3, CURRENT_TIMESTAMP), -- User 7 follows User 3
--     (16, 8, 3, CURRENT_TIMESTAMP), -- User 8 follows User 3
--     (17, 9, 3, CURRENT_TIMESTAMP), -- User 9 follows User 3
--     (18, 10, 3, CURRENT_TIMESTAMP), -- User 10 follows User 3
--     (19, 11, 3, CURRENT_TIMESTAMP), -- User 11 follows User 3
--     (20, 12, 3, CURRENT_TIMESTAMP), -- User 12 follows User 3
--
-- -- User 4 (mike_wilson) followers
--     (21, 1, 4, CURRENT_TIMESTAMP), -- User 1 follows User 4
--     (22, 2, 4, CURRENT_TIMESTAMP), -- User 2 follows User 4
--     (23, 5, 4, CURRENT_TIMESTAMP), -- User 5 follows User 4
--
-- -- User 5 (sarah_johnson) followers
--     (24, 1, 5, CURRENT_TIMESTAMP), -- User 1 follows User 5
--     (25, 2, 5, CURRENT_TIMESTAMP), -- User 2 follows User 5
--     (26, 3, 5, CURRENT_TIMESTAMP), -- User 3 follows User 5
--     (27, 4, 5, CURRENT_TIMESTAMP), -- User 4 follows User 5
--     (28, 6, 5, CURRENT_TIMESTAMP), -- User 6 follows User 5
--     (29, 7, 5, CURRENT_TIMESTAMP), -- User 7 follows User 5
--     (30, 8, 5, CURRENT_TIMESTAMP), -- User 8 follows User 5
--
-- -- User 6 (robert_brown) followers
--     (31, 2, 6, CURRENT_TIMESTAMP), -- User 2 follows User 6
--     (32, 3, 6, CURRENT_TIMESTAMP), -- User 3 follows User 6
--     (33, 5, 6, CURRENT_TIMESTAMP), -- User 5 follows User 6
--
-- -- User 7 (emily_davis) followers
--     (34, 1, 7, CURRENT_TIMESTAMP), -- User 1 follows User 7
--     (35, 2, 7, CURRENT_TIMESTAMP), -- User 2 follows User 7
--     (36, 3, 7, CURRENT_TIMESTAMP), -- User 3 follows User 7
--     (37, 5, 7, CURRENT_TIMESTAMP), -- User 5 follows User 7
--     (38, 8, 7, CURRENT_TIMESTAMP), -- User 8 follows User 7
--     (39, 10, 7, CURRENT_TIMESTAMP), -- User 10 follows User 7
--     (40, 12, 7, CURRENT_TIMESTAMP), -- User 12 follows User 7
--
-- -- User 8 (chris_white) followers
--     (41, 1, 8, CURRENT_TIMESTAMP), -- User 1 follows User 8
--     (42, 3, 8, CURRENT_TIMESTAMP), -- User 3 follows User 8
--     (43, 5, 8, CURRENT_TIMESTAMP), -- User 5 follows User 8
--     (44, 7, 8, CURRENT_TIMESTAMP), -- User 7 follows User 8
--
-- -- User 9 (moderator) followers
--     (45, 1, 9, CURRENT_TIMESTAMP), -- User 1 follows User 9
--     (46, 2, 9, CURRENT_TIMESTAMP), -- User 2 follows User 9
--     (47, 3, 9, CURRENT_TIMESTAMP), -- User 3 follows User 9
--     (48, 5, 9, CURRENT_TIMESTAMP), -- User 5 follows User 9
--
-- -- User 10 (tech_guru) followers
--     (49, 1, 10, CURRENT_TIMESTAMP), -- User 1 follows User 10
--     (50, 2, 10, CURRENT_TIMESTAMP), -- User 2 follows User 10
--     (51, 3, 10, CURRENT_TIMESTAMP), -- User 3 follows User 10
--     (52, 5, 10, CURRENT_TIMESTAMP), -- User 5 follows User 10
--     (53, 7, 10, CURRENT_TIMESTAMP), -- User 7 follows User 10
--     (54, 8, 10, CURRENT_TIMESTAMP), -- User 8 follows User 10
--     (55, 12, 10, CURRENT_TIMESTAMP);-- User 12 follows User 10

--------
-- MERGE INTO otp (id, otp_code, email, expiration_time, failed_otp_attempts, lock_time_duration, otp_request_count, new_password, created_at, updated_at, version)
--     VALUES
--     -- Active OTPs (not expired)
--     (1, '123456', 'john@example.com', DATEADD('MINUTE', 5, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$NewEncryptedPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (2, '789012', 'jane@example.com', DATEADD('MINUTE', 3, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$AnotherEncryptedPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (3, '345678', 'admin@example.com', DATEADD('MINUTE', 10, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$AdminNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- OTPs with failed attempts
--     (4, '901234', 'mike@example.com', DATEADD('MINUTE', 2, CURRENT_TIMESTAMP), 2, NULL, 3, '$2a$10$MikeNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (5, '567890', 'sarah@example.com', DATEADD('MINUTE', 4, CURRENT_TIMESTAMP), 1, NULL, 2, '$2a$10$SarahNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- OTP that is locked (too many failed attempts)
--     (6, '123789', 'robert@example.com', DATEADD('MINUTE', -30, CURRENT_TIMESTAMP), 5, DATEADD('MINUTE', 30, CURRENT_TIMESTAMP), 5, '$2a$10$RobertNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- Expired OTPs (for testing)
--     (7, '456123', 'emily@example.com', DATEADD('MINUTE', -10, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$EmilyNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (8, '789456', 'chris@example.com', DATEADD('MINUTE', -20, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$ChrisNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (9, '321654', 'moderator@example.com', DATEADD('MINUTE', -15, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$ModNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--
--     -- OTPs with 4-digit codes
--     (10, '9876', 'tech@example.com', DATEADD('MINUTE', 5, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$TechNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (11, '5432', 'travel@example.com', DATEADD('MINUTE', 7, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$TravelNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
--     (12, '1098', 'fitness@example.com', DATEADD('MINUTE', 3, CURRENT_TIMESTAMP), 0, NULL, 1, '$2a$10$FitnessNewPasswordHash', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
