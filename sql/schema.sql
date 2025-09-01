-- 공통
CREATE TABLE content (
     id           BIGINT      PRIMARY KEY AUTO_INCREMENT,
     user_id      BIGINT      NOT NULL,                                                           -- 생성자
     content_type VARCHAR(20) NOT NULL,                                                           -- 컨텐츠 종류(ARTICLE, COMMENT, EMOJI)
     created_at   DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP,                              -- 생성일시
     updated_at   DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일시
     deleted_at   DATETIME,                                                                       -- 삭제여부
     is_visible   BOOLEAN     NOT NULL    DEFAULT FALSE,                                          -- 숨김여부

     CONSTRAINT fk_content_user_id FOREIGN KEY (user_id)      REFERENCES user(id),
     CONSTRAINT ck_content_type    CHECK       (content_type) IN ('ARTICLE', 'COMMENT', 'EMOJI')) -- 컨텐츠 종류 제약
);

CREATE TABLE role (
    id        BIGINT      PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(20) NOT NULL    UNIQUE -- 권한명
);

-- 이용자
CREATE TABLE user (
    id                BIGINT       PRIMARY KEY AUTO_INCREMENT,                                        -- 고유ID
    email             VARCHAR(100) NOT NULL    UNIQUE,                                                -- 이메일
    password          VARCHAR(256) NOT NULL,                                                          -- 비밀번호
    nickname          VARCHAR(20)  NOT NULL,                                                          -- 닉네임
    tag               VARCHAR(20)  NOT NULL,                                                          -- 태그
    profile_image_url VARCHAR(256) NOT NULL,                                                          -- 프로필 이미지[URL]
    point             INT          NOT NULL    DEFAULT 0,                                             -- 포인트
    created_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일
    updated_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    deleted_at        DATETIME,                                                                       -- 삭제(탈퇴) 요청 날짜

    CONSTRAINT uk_user_nickname_tag UNIQUE (nickname, tag)                                            -- 닉네임 + 태그 복합 유니크
);

CREATE TABLE point (
    id           BIGINT       PRIMARY KEY AUTO_INCREMENT,
    content_id   BIGINT       NOT NULL,                              -- 참조 컨텐츠
    user_id      BIGINT       NOT NULL,                              -- 이용자
    point_type   VARCHAR(20)  NOT NULL,                              -- 변경 종류 (ARTICLE, COMMENT, EMOJI)
    point_change INT          NOT NULL,                              -- 변동량
    description  VARCHAR(255),                                       -- 변동 상세설명
    created_at   DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP, -- 생성일시

    CONSTRAINT fk_point_user_id    FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_point_content_id FOREIGN KEY (content_id) REFERENCES content(id),
    CONSTRAINT ck_point_type       CHECK       (point_type  IN ('ARTICLE', 'COMMENT', 'EMOJI')) -- 변경 종류 제약
);

CREATE TABLE user_block (
    id              BIGINT   PRIMARY KEY AUTO_INCREMENT ,   -- 고유ID
    user_id         BIGINT   NOT NULL,                      -- 이용자ID
    blocked_user_id BIGINT   NOT NULL,                      -- 차단당한 이용자ID
    created_at      DATETIME DEFAULT     CURRENT_TIMESTAMP, -- 생성일

    CONSTRAINT fk_user_block_user_id         FOREIGN KEY (user_id)         REFERENCES user(id),
    CONSTRAINT fk_user_block_blocked_user_id FOREIGN KEY (blocked_user_id) REFERENCES user(id),
    CONSTRAINT uk_user_block_user_id_blocked_user_id UNIQUE(user_id, blocked_user_id)
);

-- 게시판
CREATE TABLE board (
    id                BIGINT       PRIMARY KEY AUTO_INCREMENT,
    board_name        VARCHAR(50)  NOT NULL    UNIQUE,                                                -- 이름
    board_description VARCHAR(255) NOT NULL,                                                          -- 상세설명
    created_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at        DATETIME     NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시
    deleted_at        DATETIME,                                                                       -- 폐쇄여부
);

CREATE TABLE article_category (
    id            BIGINT      PRIMARY KEY AUTO_INCREMENT,
    board_id      BIGINT      NOT NULL,                                                          -- 게시판
    role_id       BIGINT      NOT NULL,                                                          -- 열람 권한
    category_name VARCHAR(20) NOT NULL,                                                          -- 카테고리 이름
    sort_order    INT         NOT NULL    DEFAULT 0,                                             -- 카테고리 표시순서
    created_at    DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at    DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시

    CONSTRAINT fk_article_category_board_id            FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT fk_article_category_role_id             FOREIGN KEY (role_id)  REFERENCES role(id),
    CONSTRAINT uk_article_category_board_category_name UNIQUE      (board_id, category_name)     -- 중복 카테고리 제약
);

CREATE TABLE board_icon (
    id                  BIGINT       PRIMARY KEY  AUTO_INCREMENT,
    admin_id            BIGINT       NOT NULL,                                                           -- 생성한 관리자
    current_icon_url    VARCHAR(255) NOT NULL,                                                           -- 현재 아이콘 URL
    previous_icon_url   VARCHAR(255),                                                                    -- 이전 아이콘 URL
    created_at          DATETIME     NOT NULL     DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at          DATETIME     NOT NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시

    CONSTRAINT fk_board_icon_admin_id FOREIGN KEY (admin_id) REFERENCES admin(id)
);

-- TODO
CREATE TABLE board_config (
    id                BIGINT   PRIMARY KEY AUTO_INCREMENT,
    board_id          BIGINT   NOT NULL    UNIQUE,
    board_icon_id     BIGINT   NOT NULL,
    pop_least_like    BIGINT   NOT NULL    DEFAULT 0,
    dislike_available BOOLEAN  NOT NULL    DEFAULT TRUE,
    dislike_influence BOOLEAN  NOT NULL    DEFAULT TRUE,
    updated_at        DATETIME NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_board_config_board_id      FOREIGN KEY (board_id)      REFERENCES board(id),
    CONSTRAINT fk_board_config_board_icon_id FOREIGN KEY (board_icon_id) REFERENCES board_config(id),
);

CREATE TABLE subscribe (
    id              BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id         BIGINT   NOT NULL,                                 -- 생성자
    board_id        BIGINT   NOT NULL,                                 -- 게시판
    subscribe_order INT      NOT NULL    DEFAULT 0,                    -- 표시순서
    notification    BOOLEAN  NOT NULL    DEFAULT TRUE,                 -- 알림여부
    created_at      DATETIME NOT NULL    DEFAULT CURRENT_TIMESTAMP,    -- 생성일시

    CONSTRAINT fk_subscribe_user_id    FOREIGN KEY (user_id)  REFERENCES user(id),
    CONSTRAINT fk_subscribe_board_id   FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT uk_subscribe_user_board UNIQUE      (user_id, board_id) -- 구독 중복 제약
);

-- 관리자
CREATE TABLE admin (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT,                            -- 고유ID
    user_id    BIGINT   NOT NULL,                                              -- 관리자 이용자ID
    role_id    BIGINT   NOT NULL,                                              -- 관리자 권한ID
    board_id   BIGINT,                                                         -- 관리 게시판ID [NULL이면 전체관리자]
    admin_config_id BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,                             -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    deleted_at DATETIME,                                                       -- 해임일

    CONSTRAINT fk_admin_user_id         FOREIGN KEY (user_id)         REFERENCES user(id),
    CONSTRAINT fk_admin_role_id         FOREIGN KEY (role_id)         REFERENCES role(id),
    CONSTRAINT fk_admin_board_id        FOREIGN KEY (board_id)        REFERENCES board(id),
    CONSTRAINT fk_admin_admin_config_id FOREIGN KEY (admin_config_id) REFERENCES admin_config(id)
);

CREATE TABLE admin_config (
    id                              BIGINT  PRIMARY KEY AUTO_INCREMENT, -- 고유ID
    owner_admin_id                  BIGINT,                             -- 담당관리자ID
    available_ban_days              BIGINT  DEFAULT 0,                  -- 최대차단가능일수
    available_band_user_count         BIGINT  DEFAULT 0,                  -- 최대차단이용자수
    available_admin_addition        Boolean DEFAULT FALSE,              -- 관리자 추가부여 가능 여부
    available_admin_dismissal       Boolean DEFAULT FALSE,              -- 관리자 해임 가능 여부
    available_category_modification Boolean DEFAULT FALSE,              -- 카테고리 권한 변경 가능 여부
    available_category_creation     Boolean DEFAULT FALSE,              -- 카테고리 생성 권한 여부

    CONSTRAINT fk_admin_config_owner_admin_id FOREIGN KEY (owner_admin_id) REFERENCES admin(id)
);

CREATE TABLE ban (
    id           BIGINT   PRIMARY KEY AUTO_INCREMENT, -- 고유ID
    admin_id     BIGINT   NOT NULL,                   -- 차단한 관리자ID
    board_id     BIGINT   NOT NULL,                   -- 차단이 일어난 게시판ID
    user_id      BIGINT   NOT NULL,                   -- 차단당한 이용자ID
    content      tinytext,                            -- 차단사유
    is_used      Boolean  DEFAULT TRUE,               -- 차단상태
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 차단 시작일자
    unblocked_at DATETIME NOT NULL,                   -- 차단 해제일자

    CONSTRAINT fk_ban_admin_id FOREIGN KEY (admin_id) REFERENCES admin(id),
    CONSTRAINT fk_ban_board_id FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT fk_ban_user_id  FOREIGN KEY (user_id)  REFERENCES user(id)
);

-- 게시글
CREATE TABLE article (
    id                      BIGINT      PRIMARY KEY,
    article_category_id     BIGINT      NOT NULL,               -- 카테고리
    role_id                 BIGINT      NOT NULL,               -- 권한
    is_notice               BOOLEAN     NOT NULL DEFAULT FALSE, -- 공지여부
    title                   VARCHAR(50) NOT NULL,               -- 제목
    content                 TEXT        NOT NULL,               -- 내용
    image_url               VARCHAR(255),                       -- 썸네일
    views                   INT         NOT NULL DEFAULT 0,     -- 조회수
    like_count              INT         NOT NULL DEFAULT 0,     -- 개추
    dislike_count           INT         NOT NULL DEFAULT 0,     -- 비추

    CONSTRAINT fk_article_content_id    FOREIGN KEY (id)                  REFERENCES content(id),
    CONSTRAINT fk_article_category_id   FOREIGN KEY (article_category_id) REFERENCES article_category(id),
    CONSTRAINT fk_article_role_id       FOREIGN KEY (role_id)             REFERENCES role(id),
    CONSTRAINT ck_article_views         CHECK       (views >= 0),        -- 조회수 양수 확인
    CONSTRAINT ck_article_like_count    CHECK       (like_count >= 0),   -- 개추 양수 확인
    CONSTRAINT ck_article_dislike_count CHECK       (dislike_count >= 0) -- 비추 양수 확인
);

CREATE TABLE article_like (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT   NOT NULL,                           -- 생성자
    article_id BIGINT   NOT NULL,                           -- 게시글
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성일시
    is_dislike BOOLEAN  NOT NULL DEFAULT FALSE,             -- 비추여부

    CONSTRAINT fk_article_like_user_id         FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_article_like_article_id      FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT uk_article_like_user_article    UNIQUE      (user_id, article_id) -- 비추 중복 제약
);

CREATE TABLE comment (
    id         BIGINT   PRIMARY KEY,
    article_id BIGINT   NOT NULL, -- 게시글
    parent_id  BIGINT,            -- 부모 댓글
    content    TINYTEXT NOT NULL, -- 내용

    CONSTRAINT fk_comment_content_id FOREIGN KEY (id)         REFERENCES content(id),
    CONSTRAINT fk_comment_article_id FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT fk_comment_parent_id  FOREIGN KEY (parent_id)  REFERENCES comment(id)
);

CREATE TABLE bookmark (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT   NOT NULL,                              -- 생성자
    article_id BIGINT   NOT NULL,                              -- 게시글
    created_at DATETIME NOT NULL    DEFAULT CURRENT_TIMESTAMP, -- 생성일자

    CONSTRAINT fk_bookmark_user_id         FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_bookmark_article_id      FOREIGN KEY (article_id) REFERENCES article(id),
    CONSTRAINT uk_bookmark_user_article    UNIQUE      (user_id, article_id) -- 북마크 중복 제약
);

CREATE TABLE report (
    id           BIGINT    PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT    NOT NULL,                                                          -- 생성자
    content_id   BIGINT    NOT NULL,                                                          -- 콘텐츠
    admin_id     BIGINT,                                                                      -- 관리자
    description  TEXT      NOT NULL,                                                          -- 사유
    image_url    VARCHAR(255),                                                                -- 이미지
    result       VARCHAR(255),                                                                -- 결과
    is_processed BOOLEAN   NOT NULL    DEFAULT FALSE,                                         -- 처리여부
    created_at   DATETIME  NOT NULL    DEFAULT CURRENT_TIMESTAMP,                             -- 생성일시
    updated_at   DATETIME  NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일시

    CONSTRAINT fk_report_user_id    FOREIGN KEY (user_id)    REFERENCES user(id),
    CONSTRAINT fk_report_content_id FOREIGN KEY (content_id) REFERENCES content(id),
    CONSTRAINT fk_report_admin_id   FOREIGN KEY (admin_id)   REFERENCES admin(id)
);

-- 이모지
CREATE TABLE emoji (
    id          BIGINT      PRIMARY KEY,                -- 고유ID
    name        VARCHAR(50) NOT NULL,                   -- 이모지 이름
    image       BLOB        NOT NULL,                   -- 이모지 이미지
    description tinytext,                               -- 설명
    point       INT         NOT NULL,                   -- 포인트[가격]

    CONSTRAINT fk_emoji_content FOREIGN KEY (id) REFERENCES content(id)
);

CREATE TABLE emoji_user (
    id         BIGINT   PRIMARY KEY AUTO_INCREMENT, -- 고유ID
    emoji_id   BIGINT   NOT NULL,                   -- 이모지 고유ID
    user_id    BIGINT   NOT NULL,                   -- 이용자 고유ID
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 구매일
    order      INT,                                 -- 정렬순서

    CONSTRAINT fk_emoji_user_emoji_id FOREIGN KEY (emoji_id) REFERENCES emoji(id),
    CONSTRAINT fk_emoji_user_user_id  FOREIGN KEY (user_id)  REFERENCES user(id)
);

CREATE TABLE emoji_folder (
    id          BIGINT      PRIMARY KEY AUTO_INCREMENT, -- 고유ID
    user_id     BIGINT      NOT NULL,                   -- 이용자 고유ID
    folder_name VARCHAR(50) NOT NULL,                   -- 폴더명
    description TINYTEXT,                               -- 설명
    is_public   BOOLEAN     DEFAULT FALSE,              -- 공개여부
    created_at  DATETIME    DEFAULT CURRENT_TIMESTAMP,  -- 생성일
    order       INT,                                    -- 정렬순서

    CONSTRAINT fk_emoji_folder_user_id FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE emoji_folder_content (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유ID
    emoji_id        BIGINT NOT NULL,                    -- 이모지 고유ID
    emoji_folder_id BIGINT NOT NULL,                    -- 이모지 폴더 고유ID

    CONSTRAINT fk_emoji_folder_content_emoji_id         FOREIGN KEY (emoji_id)         REFERENCES emoji(id),
    CONSTRAINT fk_emoji_folder_content_emoji_folder_id  FOREIGN KEY (emoji_folder_id)  REFERENCES emoji_folder(id)
);