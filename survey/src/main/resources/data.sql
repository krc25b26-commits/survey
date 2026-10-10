INSERT INTO store_master (id, name)
VALUES ('001', 'テスト店舗')
ON CONFLICT DO NOTHING;
