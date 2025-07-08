package kdt.minone.domain.ai;

import jakarta.persistence.*;
import kdt.minone.global.common.entity.BaseEntity;
import kdt.minone.global.enums.AiLogType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "ai_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AiLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private AiLogType type;

    @Column(nullable = false)
    private Long fkId;

    @Column(nullable = false, columnDefinition = "text")
    private String input;

    @Column(nullable = false, columnDefinition = "text")
    private String output;

    public AiLog(String type, Long fkId, String input, String output) {
        this.type = AiLogType.valueOf(type);
        this.fkId = fkId;
        this.input = input;
        this.output = output;
    }
}
