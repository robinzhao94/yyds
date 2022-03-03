package icu.oo7.yyds.uaa.domain;

import icu.oo7.yyds.common.dto.UserDetailsDTO;
import icu.oo7.yyds.common.util.constant.AuthContants;
import icu.oo7.yyds.common.util.enums.UserStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/**
 * 登录用户信息
 * @author peng.zhao
 */
@Data
@NoArgsConstructor
public class User implements UserDetails{

    private static final long serialVersionUID = 8753870606989258100L;

    private Long id;

    private String username;

    private String password;

    private String phone;

    private Boolean enabled;

    private String clientId;

    private Collection<SimpleGrantedAuthority> authorities;

    public User(UserDetailsDTO userDetailsDTO) {
        this.id = userDetailsDTO.getId();
        this.username = userDetailsDTO.getUsername();
        this.password = AuthContants.BCRYPT + userDetailsDTO.getPassword();
        this.enabled = Objects.equals(UserStatusEnum.NORMAL.getValue(), userDetailsDTO.getStatus());
        this.phone = userDetailsDTO.getPhone();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
