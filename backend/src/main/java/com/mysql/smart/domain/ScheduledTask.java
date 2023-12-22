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
    public class ScheduleTaskRequest {
        private Long furnitureId;
        private LocalDateTime startTime;

        public Long getFurnitureId() {
            return furnitureId;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        // 省略构造函数和其他方法，可以使用工具生成
        // 注意：确保提供了适当的 getter 和 setter 方法
    }

    // 省略其他代码
}
