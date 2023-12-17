package com.mysql.smart.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_task")
public class ScheduledTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;

    @Column
    private LocalDateTime startTime;

    @Column
    private boolean active;

    public void setFurniture(Furniture furniture) {
    }

    public void setStartTime(LocalDateTime startTime) {
    }

    public void setActive(boolean b) {
    }

    // 省略其他代码
}
