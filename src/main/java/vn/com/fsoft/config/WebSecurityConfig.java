package vn.com.fsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
@Transactional
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] IGNORED_RESOURCE_LIST = new String[] {};
    private static final String[] PERMITALL_RESOURCE_LIST = new String[] {"/", "/auth/**", "/403" };
    private static final String[] ADMIN_RESOURCE_LIST = new String[] { "/admin/**" };
    private static final String[] USER_RESOURCE_LIST = new String[] { "/HocTap/**", "/KiemTra/**", "/XemLai/**", "/TimKiem/**", "/DienThongTin/**" };

    @Autowired
    private Securityhandler securityhandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(IGNORED_RESOURCE_LIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable csrf
        http.csrf().disable();

        // Config pages not require Login
        http.authorizeRequests().antMatchers(PERMITALL_RESOURCE_LIST).permitAll();

//        // Config pages for ROLE_ADMIN
//        http.authorizeRequests().antMatchers(ADMIN_RESOURCE_LIST).hasAuthority("ROLE_ADMIN");
//
//        // Config pages for ROLE_USER
//        http.authorizeRequests().antMatchers(USER_RESOURCE_LIST).hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");

        http.authorizeRequests().antMatchers("/").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
        http.authorizeRequests().antMatchers("/admin/Convert").hasAuthority("ROLE_CONVERT");
        http.authorizeRequests().antMatchers("/admin/QuanLyFile/**").hasAuthority("ROLE_FILE_MANAGEMENT");
        http.authorizeRequests().antMatchers("/admin/QuanLyUser/**").hasAuthority("ROLE_USER_MANAGEMENT");
        // If try to access pages without roles required
        // throw AccessDeniedException
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/auth/403");

        // Config login.
        http.authorizeRequests().and().formLogin()

            .loginProcessingUrl("/j_spring_security_check")
            .loginPage("/auth/DangNhap")
            .successHandler(securityhandler)
            .failureUrl("/auth/DangNhap?error=true")
            .usernameParameter("username")
            .passwordParameter("password")

             // Config logout
             .and().logout().logoutUrl("/auth/DangXuat").logoutSuccessUrl("/");
    }
}
