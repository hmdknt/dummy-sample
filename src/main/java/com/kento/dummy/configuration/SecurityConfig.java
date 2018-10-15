package com.kento.dummy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソースに対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers( "/auth").permitAll()
                    .anyRequest().authenticated()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/back"))
                    .logoutSuccessUrl("/auth")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll();
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                // アクセス権限の設定
//                // アクセス制限の無いURL
//                .antMatchers("/", "/login", "/error").permitAll()
//                // その他は認証済みであること
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                // ログイン画面
//                .loginPage("/login")
//// 認証処理
//                .loginProcessingUrl("/authenticate")
//// ログイン成功
//                .defaultSuccessUrl("/loginSuccess")
//// ログイン失敗
//                .failureUrl("/login")
//// usernameのパラメータ名
//                .usernameParameter("username")
//// passwordのパラメータ名
//                .passwordParameter("password")
//                .and()
//                .logout()
//// ログアウト処理
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//// ログアウト成功時の遷移先
//                .logoutSuccessUrl("/login")
//// ログアウト時に削除するクッキー名
//                .deleteCookies("JSESSIONID")
//// ログアウト時のセッション破棄を有効化
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .sessionManagement()
//// セッションが無効な時の遷移先
//                .invalidSessionUrl("/invalidSession");
    }

//    @Autowired
//    protected void config(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user1").password("user1").roles("USER");
//
//    }
}