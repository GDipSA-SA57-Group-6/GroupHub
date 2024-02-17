package edu.nus.iss.gdipsa.grouphub.ServiceLayer.GroupHub;

import java.util.List;

import edu.nus.iss.gdipsa.grouphub.ModelLayer.User;
import edu.nus.iss.gdipsa.grouphub.RepositoryLayer.GroupHubRepository;
import edu.nus.iss.gdipsa.grouphub.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;
import jakarta.persistence.EntityNotFoundException;
import edu.nus.iss.gdipsa.grouphub.InterfaceLayer.ISubscriber;

@Service
public class GroupHubSubscriberService implements ISubscriber {
    @Autowired
    GroupHubRepository groupHubRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<GroupHub> searchByName(String name) {
        return null;
    }

    @Override
    public List<GroupHub> sortByPopularity() {
        return null;
    }

    @Override
    public List<GroupHub> sortByDistance(double latitude, double longitude) {
        return null;
    }

    @Override
    public boolean eventConfirm(Integer userId, long groupId) {
        // by default, when code reaches this line, both of the userId and groupId are valid.
        // which means one can't subscribe the event that he/she launches
        User user = userRepository.findById(userId).get();
        GroupHub groupHub = groupHubRepository.findById(groupId).get();

        if(groupHub.getHasUsers().contains(user)) return false;
        groupHub.getHasUsers().add(user);
        groupHub.setQuantity(groupHub.getQuantity()-1);
        groupHubRepository.save(groupHub);

        return true;
    }

    @Override
    public boolean eventCancel(Integer userId, long groupId) {
        User user = userRepository.findById(userId).get();
        GroupHub groupHub = groupHubRepository.findById(groupId).get();

        groupHub.getHasUsers().remove(user);
        groupHub.setQuantity(groupHub.getQuantity()+1);

        groupHubRepository.save(groupHub);
        return true;
    }

    @Override
    public void ec_Like(Integer userId, long groupId) {

    }

    /**
     * 对返回某用户所有拼团事件的实现
     * @param userId
     * @return
     */
    @Override
    public List<GroupHub> getSubscribedGroupHubs(Integer userId) {
        // 默认userId有效
        User user = userRepository.findById(userId).get();
        List<GroupHub> items = groupHubRepository.findAll();
        items = items.stream()
                .filter(item -> item.getHasUsers().contains(user))
                .toList();
        return items;
    }

    /**
     * 返回某个拼单所参与的用户
     * @param groupId
     * @return
     */
    @Override
    public List<User> getSubscribedUsersByGroupId(Long groupId) {
        // 默认GroupId有效
        GroupHub groupHub = groupHubRepository.findById(groupId).get();
        return groupHub.getHasUsers().stream().toList();
    }
}

