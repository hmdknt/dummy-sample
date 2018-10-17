package com.kento.dummy.domain.model;

import lombok.Data;

@Data
public class AuthInfo {

    // ユーザ名
    private String name;

    // メールアドレス
    private String emailAddress;

    // アクセストークン
    private String accessToken;
}
