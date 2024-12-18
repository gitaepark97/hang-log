package woowacourse.hanglog.core.trip.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;
import woowacourse.hanglog.core.trip.domain.Trip;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "trip")
@DynamicInsert
@DynamicUpdate
@SQLRestriction("delete_time IS NULL")
class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String description;

    @Column(nullable = false)
    private Boolean isPublish;

    @Column(nullable = false)
    private Boolean isPrivate;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long updateTime;

    private Long deleteTime;

    static TripEntity from(Trip trip) {
        return new TripEntity(
            trip.id(),
            trip.memberId(),
            trip.title(),
            trip.imageName(),
            trip.startDate(),
            trip.endDate(),
            trip.description(),
            trip.isPublish(),
            trip.isPrivate(),
            trip.createTime(),
            trip.updateTime(),
            trip.deleteTime()
        );
    }

    Trip toTrip() {
        return Trip.builder()
            .id(id)
            .memberId(memberId)
            .title(title)
            .imageName(imageName)
            .startDate(startDate)
            .endDate(endDate)
            .description(description)
            .isPublish(isPublish)
            .isPrivate(isPrivate)
            .createTime(createTime)
            .updateTime(updateTime)
            .deleteTime(deleteTime)
            .build();
    }

}
