package dag.czc.czcmf.ui.app.bean.context;

import lombok.Data;

@Data
public class AuthInfo {

    // ユーザ名
    private String name;

    // メールアドレス
    private String emailAddress;

    // アクセストークン
    private String accessToken;

    // リフレッシュトークン
    private String refreshToken;
}
