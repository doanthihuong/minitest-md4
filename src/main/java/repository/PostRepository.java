package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository extends JpaRepository <Post, Long>{
    List<Post> findAllByTitleContaining(String Title);

    @Query(value = "select * from post order by likes desc ",nativeQuery = true)
    List<Post> findTrendLikes() ;


    @Query (value =  "select * from post order by createAt desc limit 3", nativeQuery = true)
    List<Post> findTopByTime();
}
