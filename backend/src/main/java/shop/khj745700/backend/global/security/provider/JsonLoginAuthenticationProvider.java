package shop.khj745700.backend.global.security.provider;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.khj745700.backend.global.security.service.UserLoginContext;
import shop.khj745700.backend.global.security.token.JsonLoginAuthenticationToken;


@AllArgsConstructor
public class JsonLoginAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();


        //id 검증
        UserLoginContext userLoginContext = (UserLoginContext) userDetailsService.loadUserByUsername(loginId);

        //pw 검증
        if(!passwordEncoder.matches(password, userLoginContext.getPassword())){
            throw new UsernameNotFoundException("Invalid Password");
        }

        /* 여기서 추가 검증 절차 진행 가능 */

        // 인증 토큰 생성 반환 (Token은 Auth의 Child)
        return new JsonLoginAuthenticationToken( userLoginContext.getAuthorities(), userLoginContext, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JsonLoginAuthenticationToken.class);
    }

}