package com.global.system.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user",uniqueConstraints = @UniqueConstraint(columnNames = "userEmail"))
public class User {

    

    public User(String userName, String userEmail, String userPassword, String userAddress, String userMobile,
            Collection<Role> role) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userMobile = userMobile;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="user_name")
    private String userName;
    
    private String userEmail;
    
    @Column(name="user_password")
    private String userPassword;
    
    @Column(name="user_address")
    private String userAddress;
    
    @Column(name="user_mobile")
    private String userMobile;
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id"))
    private Collection<Role> role;

}
