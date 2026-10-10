INSERT INTO store_master (id, name)
VALUES (1 , 'テスト店舗')
ON CONFLICT DO NOTHING;
