package edu.nus.iss.gdipsa.grouphub.ModelLayer;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_hubs")
public class GroupHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private int likes;

    @Column(name = "is_depend_on_quantity")
    private boolean isDependOnQuantity;

    private double latitude;
    private double longitude;

    @Column(name = "is_depend_on_location")
    private boolean isDependOnLocation;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Column(name = "is_depend_on_time")
    private boolean isDependOnTime;

    // Constructors, getters, setters...

    public GroupHub() {
    }

    // Getters and setters for all the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ... other getters and setters
    public void incrementLikes() {
        this.likes++;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "GroupHub{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", likes=" + likes +
                ", isDependOnQuantity=" + isDependOnQuantity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isDependOnLocation=" + isDependOnLocation +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isDependOnTime=" + isDependOnTime +
                '}';
    }

    public void setConfirmed(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setConfirmed'");
    }

    public void setCancelled(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCancelled'");
    }
}
