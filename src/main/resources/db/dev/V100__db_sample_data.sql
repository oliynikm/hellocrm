alter sequence hibernate_sequence restart with 1000;
INSERT INTO client (id , first_name , last_name, email ) VALUES
   (1, 'john', 'doe',null),
   (2, 'john', 'doe jr.',null),
   (3, 'john', 'three','support@i.ua'),
   (4, 'jack', 'freeman',null);

INSERT INTO email_address (id, address, client_id) VALUES
    (1, 'ADMIN@test.com', 1),
    (2, 'USER@ggg.net', 2),
    (3, 'fun@join.org', 3);

INSERT INTO interaction (id,type , description , client_id, state, message ) VALUES
    (1,'EMAIL', 'test', 1,null,null),
    (2,'EMAIL', 'test2',null,null,null),
    (3,'EMAIL', 'test3',1,'New',null),
    (4,'EMAIL', 'test4',2,'New',null),
    (5,'EMAIL', 'test5',null,'New',null),
    (6,'EMAIL', 'test6',3,null,null),
    (7,'PHONE_CALL', 'test call',3,null,null),
    (11,'EMAIL', 'test22', 1,null,null),
    (12,'EMAIL', 'test2',null,null,null),
    (13,'EMAIL', 'test31',1,null,null),
    (14,'EMAIL', 'test4',null,null,null),
    (15,'EMAIL', 'test5',3,null,null),
    (16,'EMAIL', 'test6',3,null,null),
    (17,'EMAIL', 'testMail',3,null, '52656365697665643a2066726f6d2077656220627920737431342e6d69362e6b6965762e75612077697468206c6f63616c20284578696d20342e38302e31290d0a0928656e76656c6f70652d66726f6d203c6e6f7265706c7940692e75613e290d0a0969642031654c596a392d303030374a302d394b0d0a09666f72206a757374666f72736f6d65746573747340692e75613b2053756e2c2030332044656320323031372032303a31303a3535202b303230300d0a546f3a206a757374666f72736f6d657465737473206a757374666f72736f6d657465737473203c6a757374666f72736f6d65746573747340692e75613e0d0a5375626a6563743a203d3f77696e646f77732d313235313f423f784f3768384f3467372b376d344f7675347544792f4344743443424a4c6c564249513d3d3f3d0d0a46726f6d3a20737570706f727440692e75610d0a446174653a2053756e2c2030332044656320323031372032303a31303a3535202b303230300d0a4d494d452d56657273696f6e3a20312e300d0a436f6e74656e742d547970653a20746578742f706c61696e3b20636861727365743d77696e646f77732d313235310d0a436f6e74656e742d5472616e736665722d456e636f64696e673a20386269740d0a4d6573736167652d49643a203c4531654c596a392d303030374a302d394b40737431342e6d69362e6b6965762e75613e0d0a0d0a20202020c4eee1f0fbe920e4e5edfc2c206a757374666f72736f6d657465737473206a757374666f72736f6d6574657374732e0d0accfb20f0e0e4fb20eff0e8e2e5f2f1f2e2eee2e0f2fc20c2e0f120ede020edeee2eee920f3eaf0e0e8edf1eaeee920efeef7f2e520492e55412e20cde0e4e5e5ecf1ff2c20c2e0ec0d0aefeeedf0e0e2fff2f1ff20f3e4eee1edfbe920e8edf2e5f0f4e5e9f12c20f4f3edeaf6e8eeede0ebfcedeef1f2fc20e820ede0e4e5e6edeef1f2fc20efeef7f2eee2eee90d0af1e8f1f2e5ecfb2e0d0a0d0ac6e5ebe0e5ec20eff0e8fff2edeee3ee20e8f1efeeebfce7eee2e0ede8ff2e0d0a0d0a2d2d0d0ad120f3e2e0e6e5ede8e5ec2c20eaeeece0ede4e020492e55410d0a7777772e692e75610d0a');

