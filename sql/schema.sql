-- 이용자
CREATE TABLE user (
    id                BIGINT       AUTO_INCREMENT PRIMARY KEY,                          -- 고유ID
    email             VARCHAR(100) NOT NULL UNIQUE,                                     -- 이메일
    password          VARCHAR(256) NOT NULL,                                            -- 비밀번호
    nickname          VARCHAR(50)  NOT NULL,                                            -- 닉네임                        
    tag               VARCHAR(50)  NOT NULL,                                            -- 태그
    UNIQUE            (nickname, tag),                                                  -- 닉네임 + 태그 복합 유니크
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,                               -- 생성일
    updated_at        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,   -- 수정일
    point             INT DEFAULT 0,                                                    -- 포인트
    profile_image_url VARCHAR(256) NOT NULL,                                            -- 프로필 이미지[URL]
    is_deleted        DATETIME                                                          -- 삭제(탈퇴) 요청 날짜
);

CREATE TABLE user_block (
    id              BIGINT   AUTO_INCREMENT PRIMARY KEY,                                -- 고유ID   
    user_id         BIGINT   NOT NULL UNIQUE,                                           -- 이용자ID    
    blocked_user_id BIGINT   NOT NULL UNIQUE,                                           -- 차단당한 이용자ID    
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,                                 -- 생성일

    constraint fk_user_block_user         FOREIGN KEY (user_id)         REFERENCES user (id),
    constraint fk_user_block_blocked_user FOREIGN KEY (blocked_user_id) REFERENCES user (id)
);

-- 관리자
CREATE TABLE role (
    id        BIGINT      AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE admin (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,                              -- 고유ID
    user_id    BIGINT NOT NULL,                                                -- 관리자 이용자ID
    role_id    BIGINT NOT NULL,                                                -- 관리자 권한ID
    board_id   BIGINT,                                                         -- 관리 게시판ID [NULL이면 전체관리자] 
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,                             -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    deleted_at DATETIME,                                                       -- 해임일

    constraint fk_admin_user_id  FOREIGN KEY (user_id)  REFERENCES user  (id),
    constraint fk_admin_role_id  FOREIGN KEY (role_id)  REFERENCES role  (id),
    constraint fk_admin_board_id FOREIGN KEY (board_id) REFERENCES board (id)
);

CREATE TABLE admin_config (
    id              BIGINT  AUTO_INCREMENT PRIMARY KEY, -- 고유ID
    super_admin_id  BIGINT  NOT NULL,                   -- 담당관리자ID
    ban_days        BIGINT  DEFAULT 0,                  -- 최대차단가능일수
    ban_user        BIGINT  DEFAULT 0,                  -- 최대차단이용자수
    admin_add       Boolean DEFAULT FALSE,              -- 관리자 추가부여 가능 여부
    admin_delete    Boolean DEFAULT FALSE,              -- 관리자 해임 가능 여부
    category_perm   Boolean DEFAULT FALSE,              -- 카테고리 권한 변경 가능 여부
    category_create Boolean DEFAULT FALSE,              -- 카테고리 생성 권한 여부

    constraint fk_admin_config_super_admin_id FOREIGN KEY (super_admin_id) REFERENCES admin (id)
);

CREATE TABLE ban (
    id           BIGINT   AUTO_INCREMENT PRIMARY KEY, -- 고유ID
    admin_id     BIGINT   NOT NULL,                   -- 차단한 관리자ID
    board_id     BIGINT   NOT NULL,                   -- 차단이 일어난 게시판ID
    user_id      BIGINT   NOT NULL,                   -- 차단당한 이용자ID
    content      tinytext,                            -- 차단사유
    is_used      Boolean  DEFAULT TRUE,               -- 사용여부
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 차단 시작일자
    unblocked_at DATETIME NOT NULL,                   -- 차단 해제일자

    constraint fk_ban_admin_id FOREIGN KEY (admin_id) REFERENCES admin (id),
    constraint fk_ban_board_id FOREIGN KEY (board_id) REFERENCES board (id),
    constraint fk_ban_user_id  FOREIGN KEY (user_id)  REFERENCES user (id)
);

-- 이모지
CREATE TABLE emoji (
    id          BIGINT      AUTO_INCREMENT PRIMARY KEY, -- 고유ID
    user_id     BIGINT      NOT NULL,                   -- 이용자 고유ID
    name        VARCHAR(50) NOT NULL,                   -- 이모지 이름
    image       BLOB        NOT NULL,                   -- 이모지 이미지
    description tinytext,                               -- 설명
    point       INT         NOT NULL,                   -- 포인트[가격]
    is_visiable Boolean     DEFAULT TRUE,               -- 노출여부
    is_deleted  Boolean     DEFAULT FALSE,              -- 삭제여부
    created_at  DATETIME    DEFAULT CURRENT_TIMESTAMP,  -- 생성일

    constraint fk_emoji_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE emoji_user (
    id           BIGINT   AUTO_INCREMENT PRIMARY KEY, -- 고유ID
    emoji_id     BIGINT   NOT NULL,                   -- 이모지 고유ID
    user_id      BIGINT   NOT NULL,                   -- 이용자 고유ID
    purchased_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 구매일
    order        Int,                                 -- 정렬순서

    constraint fk_emoji_user_emoji_id FOREIGN KEY (emoji_id) REFERENCES emoji (id),
    constraint fk_emoji_user_user_id  FOREIGN KEY (user_id)  REFERENCES user (id)
);

CREATE TABLE emoji_folder (
    id          BIGINT      AUTO_INCREMENT PRIMARY KEY, -- 고유ID
    user_id     BIGINT      NOT NULL,                   -- 이용자 고유ID 
    folder_name VARCHAR(50) NOT NULL,                   -- 폴더명
    description tinytext,                               -- 설명 
    is_public   Boolean     DEFAULT FALSE,              -- 공개여부
    created_at  DATETIME    DEFAULT CURRENT_TIMESTAMP,  -- 생성일 
    order       INT,                                    -- 정렬순서

    constraint fk_emoji_folder_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE emoji_folder_content (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유ID
    emoji_id        BIGINT NOT NULL,                    -- 이모지 고유ID
    emoji_folder_id BIGINT NOT NULL,                    -- 이모지 폴더 고유ID

    constraint fk_emoji_folder_content_emoji_id         FOREIGN KEY (emoji_id)         REFERENCES emoji (id),
    constraint fk_emoji_folder_content_emoji_folder_id  FOREIGN KEY (emoji_folder_id)  REFERENCES emoji_folder (id)
);
