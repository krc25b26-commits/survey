INSERT INTO store_master (id, name)
VALUES (1 , 'テスト店舗')
ON CONFLICT DO NOTHING;

insert into purpose_master(id, name)
values(1, 'ランチ')
ON CONFLICT DO NOTHING;

insert into age_master(id, label)
values(1, '10代')
ON CONFLICT DO NOTHING;
