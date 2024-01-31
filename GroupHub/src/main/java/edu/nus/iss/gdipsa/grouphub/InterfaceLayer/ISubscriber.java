package edu.nus.iss.gdipsa.grouphub.InterfaceLayer;

import java.util.List;

import edu.nus.iss.gdipsa.grouphub.ModelLayer.GroupHub;

public interface ISubscriber {
    List<GroupHub> searchByName(String name);
    List<GroupHub> sortByPopularity();
    List<GroupHub> sortByDistance();
    void eventConfirm(Long groupId);
    void eventCancel(Long groupId);
    void likeEvent(Long groupId);
    void favouriteEvent(Long groupId);
}
