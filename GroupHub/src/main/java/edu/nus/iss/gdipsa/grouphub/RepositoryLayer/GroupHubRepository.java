package edu.nus.iss.gdipsa.grouphub.RepositoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;

import java.util.List;

@Repository
public interface GroupHubRepository extends JpaRepository<GroupHub, Long> {
    
    // 通过名称查找GroupHub
    List<GroupHub> findByNameContaining(String name);

    // 按照点赞数量降序排列所有GroupHub
    List<GroupHub> findAllByOrderByLikesDesc();
}
