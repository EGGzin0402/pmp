package com.fatec.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class JwtUserDetails extends User {

    private transient com.fatec.entity.User usuario;

    public JwtUserDetails(com.fatec.entity.User usuario) {
        super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRoles().stream().map(Enum::name).toList()));
    }

    public Long getId() {
        return this.usuario.getId();
    }

    public List<String> getRole() {
        return this.usuario.getRoles().stream().map(Enum::name).toList();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JwtUserDetails that = (JwtUserDetails) obj;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

}
