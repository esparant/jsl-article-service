package com.projects.jslarticle.p6spy;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6SpyOptions;

import java.sql.SQLException;

/**
 * @author 탁영복
 * @since 2025-08-25
 * @version 1.0
 * @description p6spy 의 이벤트 listener 를 추가했습니다.
 * listener 란? 어떤 일이 일어나면(버튼클릭, DB 접속 등), listener 가 그것을 듣고 반응함, 반응하면 리스너의 메소드가 자동으로 실행됨
 */
public class P6SpyEventListener extends JdbcEventListener {

    @Override
    public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6SpyFormatter.class.getName());
    }
}