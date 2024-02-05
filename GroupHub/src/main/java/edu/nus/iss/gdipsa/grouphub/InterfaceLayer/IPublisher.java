package edu.nus.iss.gdipsa.grouphub.InterfaceLayer;

import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;

public interface IPublisher {
    /**
     * 发布一个活动
     * 活动内部已经对用户进行了绑定
     * 所以不需要额外的user id
     * @param groupHub
     * @return
     */
    GroupHub publish(GroupHub groupHub);

    /**
     * 暂时不编写
     * @param groupHub
     * @return
     */
    GroupHub modify(GroupHub groupHub);

    /**
     * 暂时不编写
     * @param groupHubID
     */
    void deleteByGroupHubID(Long groupHubID);
}
