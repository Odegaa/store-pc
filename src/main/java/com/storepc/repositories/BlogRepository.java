package com.storepc.repositories;

import com.storepc.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByTitle(String title);

    Blog findByAccount_Username(String username);

}