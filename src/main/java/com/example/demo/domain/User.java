package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
//DB의 테이블 명이 다를경우
@Table(name = "MEMBER")
@Data
public class User {

	@Id @Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// DB의 컬럼 명이 다를 경우
    // @Column(name = "MEMBER_NAME")
	private String username;
}
