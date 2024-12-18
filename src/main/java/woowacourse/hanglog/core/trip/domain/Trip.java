package woowacourse.hanglog.core.trip.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Trip(
    Long id,
    Long memberId,
    String title,
    String imageName,
    LocalDate startDate,
    LocalDate endDate,
    String description,
    Boolean isPublish,
    Boolean isPrivate,
    Long createTime,
    Long updateTime,
    Long deleteTime
) {

    private static final String DEFAULT_IMAGE_NAME = "default-image.png";

    public static Trip of(Long memberId, String title, LocalDate startDate, LocalDate endDate, Long currentTime) {
        return Trip.builder()
            .memberId(memberId)
            .title(title)
            .imageName(DEFAULT_IMAGE_NAME)
            .startDate(startDate)
            .endDate(endDate)
            .isPublish(false)
            .isPrivate(true)
            .createTime(currentTime)
            .updateTime(currentTime)
            .build();
    }

}
