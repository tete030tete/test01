--2. 가게 정보가 담긴 테이블
create table store(
    store_no          number              primary key
    ,store_name           varchar2(200)         
    ,store_name2           varchar2(20)         
    ,store_cate1           varchar2(30)         
    ,store_cate2           varchar2(50)         
    ,store_cate3           varchar2(100)         
    ,store_dem           varchar2(100)
    ,store_adr1           varchar2(150)         
    ,store_adr2           varchar2(150)              
    ,x          number              
    ,y          number              
);
create sequence store_seq;



--4.인스타그램의 가게 정보가 담긴 테이블
create table istores(
    insta_id           varchar2(30)         primary key
    ,store_no          number              
    ,profile_img       varchar2(2000)  
    ,update_date        date    
    ,constraint istores_fk foreign key(store_no) references store(store_no)
);
create sequence istores_seq;



--5.가게 관련 포스트의 url 저장
create table istores_info(
    info_no         number                primary key
    ,insta_id       varchar2(30)          not null
    ,info_img       varchar2(2000)        not null
    ,constraint isotres_info_fk foreign key(insta_id) references istores(insta_id)
);
create sequence istores_info_seq;
