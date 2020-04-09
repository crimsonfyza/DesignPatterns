package sample.web.ui.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * security configuration for the different views in the application.
 *
 * @author  Mark van Dalen
 *
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    //set authorisation global items
                    .antMatchers("/resources/**","/fragments/navbar", "/static/**","/registration", "/home").permitAll()
                    //set authorisation  for classrooms
                    .antMatchers("/classrooms/**").hasAuthority("Examinator")
                    //set authorisation  for grades
                    .antMatchers("/grades/list").hasAnyAuthority("Student","Teacher")
                    .antMatchers("/grades/form").hasAuthority("Teacher")

                    //set authorisation  for exams
                    .antMatchers("/exams/list").hasAnyAuthority("Student","Teacher")
                    .antMatchers("/exams/formComputer","/exams/formOral","/exams/formWritten", "/exams/view").hasAuthority("Teacher")

                    //set authorisation for classroom REST API
//                    .antMatchers(HttpMethod.POST, "/api/v1/**").hasAnyAuthority("Examinator","Teacher")
//                    .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyAuthority("Examinator","Teacher")
//                    .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("Examinator","Teacher")
//                    .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyAuthority("Examinator","Teacher")

                    //.anyRequest().authenticated()
                    // set authorisation so objects van be changed in the rest API
                    .and().csrf().disable()
                //set authorisation for login
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .permitAll()
                    .and();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //authenticationManagerBean
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //authenticationEntryPoint
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        BasicAuthenticationEntryPoint entryPoint =
                new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("admin realm");
        return entryPoint;
    }
}
