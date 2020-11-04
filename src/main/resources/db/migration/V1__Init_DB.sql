create table character (
                           id uuid primary key not null,
                           description varchar,
                           hero_name varchar,
                           human_name varchar,
                           image varchar
);

create table character_comic_book (
                                      id_comic_book uuid not null,
                                      id_character uuid not null
);

create table comic_book (
                            id uuid primary key not null,
                            description varchar,
                            image varchar,
                            name varchar,
                            writer varchar
);

alter table if exists character_comic_book
    add constraint fk_id_character
        foreign key (id_character) references character;

alter table if exists character_comic_book
    add constraint fk_id_comic_book
        foreign key (id_comic_book) references comic_book;