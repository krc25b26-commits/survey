delete from store_master where id = 1;

INSERT INTO store_master (id, name)
VALUES (1 , '東京支点')
ON CONFLICT DO NOTHING;
INSERT INTO store_master (id, name)
VALUES (2, '大阪支店')
ON CONFLICT DO NOTHING;
INSERT INTO store_master (id, name)
VALUES (3 , '名古屋支店')
ON CONFLICT DO NOTHING;
INSERT INTO store_master (id, name)
VALUES (4 , '長崎支店')
ON CONFLICT DO NOTHING;

insert into purpose_master(id, name)
values(1, 'ランチ')
ON CONFLICT DO NOTHING;
insert into purpose_master(id, name)
values(2, 'ディナー')
ON CONFLICT DO NOTHING;
insert into purpose_master(id, name)
values(3, 'カフェ利用')
ON CONFLICT DO NOTHING;
insert into purpose_master(id, name)
values(4, 'テイクアウト')
ON CONFLICT DO NOTHING;
insert into purpose_master(id, name)
values(5, '記念日・お祝い')
ON CONFLICT DO NOTHING;

insert into age_master(id, label)
values(1, '10代')
ON CONFLICT DO NOTHING;
insert into age_master(id, label)
values(2, '20代')
ON CONFLICT DO NOTHING;
insert into age_master(id, label)
values(3, '30代')
ON CONFLICT DO NOTHING;
insert into age_master(id, label)
values(4, '40代')
ON CONFLICT DO NOTHING;
insert into age_master(id, label)
values(5, '50代')
ON CONFLICT DO NOTHING;
insert into age_master(id, label)
values(6, '60代以上')
ON CONFLICT DO NOTHING;

insert into frequency_master(id, label)
values(1, '初めて')
ON CONFLICT DO NOTHING;
insert into frequency_master(id, label)
values(2, '2〜3回目')
ON CONFLICT DO NOTHING;
insert into frequency_master(id, label)
values(3, '月1回程度')
ON CONFLICT DO NOTHING;
insert into frequency_master(id, label)
values(4, '週1回以上')
ON CONFLICT DO NOTHING;

