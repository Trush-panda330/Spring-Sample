package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 /* JpaRepository<エンティティのクラス名, そのエンティティのIDの型> を指定する。
  * パッケージなどはどこにあるかは関係なく
  * JpaRepositoryで紐づけるクラスには＠Entityが付与されている必要がある
  * 
  * このインターフェースを継承することによりエンティティに対する基本的なCRUD処理を可能にできる
  * 
  * */
	
}