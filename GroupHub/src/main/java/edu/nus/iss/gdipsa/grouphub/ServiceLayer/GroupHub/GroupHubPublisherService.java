package edu.nus.iss.gdipsa.grouphub.ServiceLayer.GroupHub;

import edu.nus.iss.gdipsa.grouphub.InterfaceLayer.IPublisher;
import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;
import edu.nus.iss.gdipsa.grouphub.RepositoryLayer.GroupHubRepository;
import edu.nus.iss.gdipsa.grouphub.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupHubPublisherService implements IPublisher {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupHubRepository groupHubRepository;

    /**
     * 默认验证在controller里面，到达这一层的时候已经是绝对合法的GroupHub了。
     * @param groupHub
     * @return
     */
    @Override
    public GroupHub publish(GroupHub groupHub) {
        groupHubRepository.save(groupHub);

        return groupHub;
    }

    /**
     * 暂时不写
     * @param groupHub
     * @return
     */
    @Override
    public GroupHub modify(GroupHub groupHub) {
        return null;
    }

    /**
     * 暂时不写
     * @param groupHubID
     */
    @Override
    public void deleteByGroupHubID(Long groupHubID) {

    }
}
