alter sequence hibernate_sequence restart with 1000;
INSERT INTO client (id , first_name , last_name, email ) VALUES
   (1, 'John', 'Doe','jdoe@test1111.com'),
   (2, 'Terry', 'Doe','tdoe@test1111.com'),
   (3, 'John', 'Three','j3@test1111.com'),
   (4, 'Rick', 'Freeman','freeman@test1111.com');


INSERT INTO interaction 
(id, type , created, direction , description , state,   message_text , message_text_type ,
 client_address, message_from , message_to, client_id ) 
 VALUES
        (1, 'EMAIL' , DATEADD('DAY', -52,CURRENT_TIMESTAMP), 'INBOUND' , 'Sample email' , 'Read', 'some sample text' , 'text/plain' ,
        'jdoe@test1111.com', 'jdoe@test1111.com' , 'justforsometests@i.ua', 1 ),
        (2, 'EMAIL' , DATEADD('DAY', -49,CURRENT_TIMESTAMP), 'OUTBOUND' , 'RE: sample' , 'Read', 'please send me more' , 'text/plain' ,
        'jdoe@test1111.com', 'justforsometests@i.ua', 'jdoe@test1111.com' , 1 ),
        (3, 'EMAIL' , DATEADD('DAY', -46,CURRENT_TIMESTAMP), 'INBOUND' , 'Another email' , 'New', 'another sample text' , 'text/plain' ,
        'jdoe@test1111.com', 'jdoe@test1111.com' , 'justforsometests@i.ua', 1 ),
        (4, 'EMAIL' , DATEADD('DAY', -46,CURRENT_TIMESTAMP), 'INBOUND' , 'Next sample' , 'Read', '<p>second sample text</p>' , 'text/html' ,
        'tdoe@test1111.com', 'tdoe@test1111.com', 'justforsometests@i.ua', 2 ),
        (5, 'EMAIL' , DATEADD('DAY', -44,CURRENT_TIMESTAMP), 'INBOUND' , 'Reminder' , 'New', '<p>Please send me an offer with discount</p>' , 'text/html' ,
        'tdoe@test1111.com', 'tdoe@test1111.com' , 'justforsometests@i.ua', 2 ),
        (6, 'EMAIL' , DATEADD('DAY', -43,CURRENT_TIMESTAMP), 'INBOUND' , 'Test1' , 'Read', '<p>Test</p>' , 'text/html' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null ),
        (7, 'EMAIL' , DATEADD('DAY', -43,CURRENT_TIMESTAMP), 'INBOUND' , 'Test2' , 'Read', '<p>Test</p><p>Test Some sample text</p>' , 'text/html' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null ),
        (8, 'EMAIL' , DATEADD('DAY', -33,CURRENT_TIMESTAMP), 'INBOUND' , 'Test3' , 'New', 'Some sample text' , 'text/plain' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null),
        (9, 'EMAIL' , DATEADD('DAY', -32,CURRENT_TIMESTAMP), 'OUTBOUND' , 'New information' , 'Read', '<p>We have some new information. If you need assistance or have questions, please contact me</p><p> <a href=http://hellocrm.ml>Learn more</a> </p>' , 'text/html' ,
        'j3@test1111.com', 'justforsometests@i.ua', 'j3@test1111.com' , 3 ),
        (10, 'EMAIL' , DATEADD('DAY', -21,CURRENT_TIMESTAMP), 'INBOUND' , 'Re: New information' , 'Read', '<p>I`m interested, please call me</p>' , 'text/html ' ,
        'j3@test1111.com', 'j3@test1111.com' , 'justforsometests@i.ua', 3 ),
        (11, 'EMAIL' , DATEADD('DAY', -21,CURRENT_TIMESTAMP), 'INBOUND' , 'Re: Re: New information', 'New',  'Following our conversation, please send me more information' , 'text/plain' ,
        'j3@test1111.com', 'j3@test1111.com' , 'justforsometests@i.ua', 3 ),
        (12, 'EMAIL' , DATEADD('DAY', -19,CURRENT_TIMESTAMP), 'INBOUND' , 'Test5' , 'New', '<p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p>' , 'text/html' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null),
        (13, 'EMAIL' , DATEADD('DAY', -15,CURRENT_TIMESTAMP), 'INBOUND' , 'Test6' , 'New', '<p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p>' , 'text/html' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null),
        (14, 'EMAIL' , DATEADD('DAY', -11,CURRENT_TIMESTAMP), 'INBOUND' , 'Test7' , 'New', '<p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p><p>Test</p>' , 'text/html' ,
        'test@test.com', 'test@test.com' , 'justforsometests@i.ua', null);

