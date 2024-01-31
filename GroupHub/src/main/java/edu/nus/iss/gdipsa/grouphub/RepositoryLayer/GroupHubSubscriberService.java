package edu.nus.iss.gdipsa.grouphub.RepositoryLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;
import jakarta.persistence.EntityNotFoundException;
import edu.nus.iss.gdipsa.grouphub.InterfaceLayer.ISubscriber;

@Service
public class GroupHubSubscriberService implements ISubscriber {

    private final GroupHubRepository groupHubRepository;

    @Autowired
    public GroupHubSubscriberService(GroupHubRepository groupHubRepository) {
        this.groupHubRepository = groupHubRepository;
    }

    @Override
    public List<GroupHub> searchByName(String name) {
        // 实现根据名称搜索GroupHub的逻辑
        return groupHubRepository.findByNameContaining(name);
    }

    @Override
    public List<GroupHub> sortByPopularity() {
        // 实现按热度排序的逻辑
        return groupHubRepository.findAllByOrderByLikesDesc();
    }

    @Override
    public List<GroupHub> sortByDistance() {
        // 实现按距离排序的逻辑，这可能需要更复杂的实现
        // 比如使用用户的位置信息进行排序
        return groupHubRepository.findAll(); // 简化示例
    }

    @Override
    public void eventConfirm(Long groupId) {
        // 实现确认事件的逻辑
        GroupHub groupHub = groupHubRepository.findById(groupId)
            .orElseThrow(() -> new EntityNotFoundException("GroupHub not found with id: " + groupId));
        groupHub.setConfirmed(true);
        groupHubRepository.save(groupHub);
    }

    @Override
    public void eventCancel(Long groupId) {
        // 实现取消事件的逻辑
        GroupHub groupHub = groupHubRepository.findById(groupId)
            .orElseThrow(() -> new EntityNotFoundException("GroupHub not found with id: " + groupId));
        groupHub.setCancelled(true);
        groupHubRepository.save(groupHub);
    }

    @Override
    public void likeEvent(Long groupId) {
        // 实现点赞事件的逻辑
        GroupHub groupHub = groupHubRepository.findById(groupId)
            .orElseThrow(() -> new EntityNotFoundException("GroupHub not found with id: " + groupId));
        groupHub.incrementLikes();
        groupHubRepository.save(groupHub);
    }

    @Override
    public void favouriteEvent(Long groupId) {
        // 实现收藏事件的逻辑
        // 此处逻辑取决于你如何定义“收藏”，可能需要更新用户的某个属性或关联表
    }

    // 其他方法的实现...
}

