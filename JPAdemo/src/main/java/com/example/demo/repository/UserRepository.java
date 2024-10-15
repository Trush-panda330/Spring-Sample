package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

//Springのリポジトリとしてこのインターフェースをマーク
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 // Userエンティティに対するCRUD操作を提供
}