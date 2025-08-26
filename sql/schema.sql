-- 공통
CREATE TABLE content (
    id           BIGINT      PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT      NOT NULL,                                                          -- 생성자
    content_type VARCHAR(20) NOT NULL,                                                          -- 컨텐츠 종류(ARTICLE, COMMENT, EMOJI)
    created_at   DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at   DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시
    is_deleted   BOOLEAN     NOT NULL    DEFAULT FALSE,                                         -- 삭제여부

    CONSTRAINT fk_content_user FOREIGN KEY (user_id)        REFERENCES user(id),
    CONSTRAINT ck_content_type CHECK       (content_type)   IN ('ARTICLE', 'COMMENT', 'EMOJI')) -- 컨텐츠 종류 제약
);

-- 이용자
CREATE TABLE point (
    id           BIGINT       PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT       NOT NULL,                              -- 이용자
    content_id   BIGINT       NOT NULL,                              -- 참조 컨텐츠
    point_type   VARCHAR(20)  NOT NULL,                              -- 변경 종류 (ARTICLE, COMMENT, EMOJI)
    point_change INT          NOT NULL,                              -- 변동량
    description  VARCHAR(255),                                       -- 변동 상세설명
    created_at   DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP, -- 생성일시

    CONSTRAINT fk_point_user    FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_point_content FOREIGN KEY (content_id) REFERENCES content(id),
    CONSTRAINT ck_point_type    CHECK       (point_type  IN ('ARTICLE', 'COMMENT', 'EMOJI')) -- 변경 종류 제약
);

-- 게시판
CREATE TABLE board (
    id                BIGINT       PRIMARY KEY AUTO_INCREMENT,
    board_name        VARCHAR(50)  NOT NULL    UNIQUE,                                                -- 이름
    board_description VARCHAR(255) NOT NULL,                                                          -- 상세설명
    created_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시
    is_deleted        BOOLEAN      NOT NULL    DEFAULT FALSE                                          -- 폐쇄여부
);

CREATE TABLE article_category (
    id            BIGINT      PRIMARY KEY AUTO_INCREMENT,
    board_id      BIGINT      NOT NULL,                                                          -- 게시판
    role_id       BIGINT      NOT NULL,                                                          -- 열람 권한
    category_name VARCHAR(20) NOT NULL,                                                          -- 카테고리 이름
    sort_order    INT         NOT NULL    DEFAULT 0,                                             -- 카테고리 표시순서
    created_at    DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at    DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시
    is_deleted    BOOLEAN     NOT NULL    DEFAULT FALSE,                                         -- 삭제여부

    CONSTRAINT fk_article_category_board               FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT fk_article_category_role                FOREIGN KEY (role_id)  REFERENCES role(id),
    CONSTRAINT uk_article_category_board_category_name UNIQUE      (board_id, category_name)     -- 중복 카테고리 제약
);

CREATE TABLE board_icon (
    id         BIGINT       PRIMARY KEY AUTO_INCREMENT,
    board_id   BIGINT       NOT NULL,                              -- 게시판
    admin_id   BIGINT       NOT NULL,                              -- 생성한 관리자
    current    VARCHAR(255) NOT NULL,                              -- 현재 아이콘 URL
    previous   VARCHAR(255),                                       -- 이전 아이콘 URL
    created_at DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP, -- 생성일시

    CONSTRAINT fk_board_icon_board FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT fk_board_icon_admin FOREIGN KEY (admin_id) REFERENCES admin(id)
);

CREATE TABLE subscribe (
    id              BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id         BIGINT   NOT NULL,                                 -- 생성자
    board_id        BIGINT   NOT NULL,                                 -- 게시판
    subscribe_order INT      NOT NULL    DEFAULT 0,                    -- 표시순서
    notification    BOOLEAN  NOT NULL    DEFAULT TRUE,                 -- 알림여부
    created_at      DATETIME NOT NULL    DEFAULT CURRENT_TIMESTAMP,    -- 생성일시

    CONSTRAINT fk_subscribe_user       FOREIGN KEY (user_id)  REFERENCES user(id),
    CONSTRAINT fk_subscribe_board      FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT uk_subscribe_user_board UNIQUE      (user_id, board_id) -- 구독 중복 제약
);

-- 관리자

-- 게시글
CREATE TABLE article (
    id                  BIGINT      PRIMARY KEY,
    article_category_id BIGINT      NOT NULL,               -- 카테고리
    is_notice           BOOLEAN     NOT NULL DEFAULT FALSE, -- 공지여부
    title               VARCHAR(50) NOT NULL,               -- 제목
    content             TEXT        NOT NULL,               -- 내용
    image               VARCHAR(255),                       -- 썸네일
    views               INT         NOT NULL DEFAULT 0,     -- 조회수
    like_count          INT         NOT NULL DEFAULT 0,     -- 개추
    bad_count           INT         NOT NULL DEFAULT 0,     -- 비추

    CONSTRAINT fk_article_content    FOREIGN KEY (id)                  REFERENCES content(id),
    CONSTRAINT fk_article_category   FOREIGN KEY (article_category_id) REFERENCES article_category(id),
    CONSTRAINT ck_article_views      CHECK       (views >= 0),      -- 조회수 양수 확인
    CONSTRAINT ck_article_like_count CHECK       (like_count >= 0), -- 개추 양수 확인
    CONSTRAINT ck_article_bad_count  CHECK       (bad_count >= 0)   -- 비추 양수 확인
);

CREATE TABLE article_like (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT   NOT NULL,                           -- 생성자
    article_id BIGINT   NOT NULL,                           -- 게시글
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성일시
    is_bad     BOOLEAN  NOT NULL DEFAULT FALSE,             -- 비추여부

    CONSTRAINT fk_article_like_user         FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_article_like_article      FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT uk_article_like_user_article UNIQUE      (user_id, article_id) -- 비추 중복 제약
);

CREATE TABLE comment (
    id         BIGINT   PRIMARY KEY,
    article_id BIGINT   NOT NULL, -- 게시글
    parent_id  BIGINT,            -- 부모 댓글
    content    TINYTEXT NOT NULL, -- 내용

    CONSTRAINT fk_comment_content FOREIGN KEY (id)         REFERENCES content(id),
    CONSTRAINT fk_comment_article FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT fk_comment_parent  FOREIGN KEY (parent_id)  REFERENCES comment(id)
);

CREATE TABLE bookmark (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT   NOT NULL,                              -- 생성자
    article_id BIGINT   NOT NULL,                              -- 게시글
    created_at DATETIME NOT NULL    DEFAULT CURRENT_TIMESTAMP, -- 생성일자

    CONSTRAINT fk_bookmark_user         FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_bookmark_article      FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT uk_bookmark_user_article UNIQUE      (user_id, article_id) -- 북마크 중복 제약
);

CREATE TABLE report (
    id           BIGINT    PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT    NOT NULL,                                                          -- 생성자
    content_id   BIGINT    NOT NULL,                                                          -- 콘텐츠
    admin_id     BIGINT,                                                                      -- 관리자
    description  TEXT      NOT NULL,                                                          -- 사유
    image        VARCHAR(255),                                                                -- 이미지
    result       VARCHAR(255),                                                                -- 결과
    is_completed BOOLEAN   NOT NULL    DEFAULT FALSE,                                         -- 처리여부
    created_at   DATETIME  NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at   DATETIME  NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시

    CONSTRAINT fk_report_user    FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_report_content FOREIGN KEY (content_id) REFERENCES content(id),
    CONSTRAINT fk_report_admin   FOREIGN KEY (admin_id)   REFERENCES admin(id)
);

-- 이모지