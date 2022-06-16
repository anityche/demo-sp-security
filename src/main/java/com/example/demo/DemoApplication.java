package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.User;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		autoEntityProc();
	}

	private static void autoEntityProc() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("helloJPA");    // hello 라는 별명을 가져와서
        // 공장에서 찍어내는 entityManager!!!
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        // code start
        EntityTransaction tx = entityManager.getTransaction();
        // 트랜잭션 내부에서 쿼리를 실행해야 한다.
        tx.begin();	// 트랜잭션 시작
        try{

            User user = new User();
            user.setId(1L);
            user.setUsername("유저1");
            entityManager.persist(user);

            tx.commit();        // 트랜잭션 실행
        }catch (Exception e){
            // 에러가 발생하면 롤백해줘야 합니다.
            tx.rollback();
        }finally {
            // 어찌되었던 자원 다 쓰면 매니저를 닫아주어야 합니다.
        	entityManager.close();
        	entityManagerFactory.close();
        }
        // code end

	}
}
