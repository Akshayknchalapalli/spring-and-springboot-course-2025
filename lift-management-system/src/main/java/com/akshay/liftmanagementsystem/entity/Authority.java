package com.akshay.liftmanagementsystem.entity;


import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String authority;

    // Constructors
    public Authority() {}

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String getAuthority() { return authority; }
    public void setAuthority(String authority) { this.authority = authority; }
}

