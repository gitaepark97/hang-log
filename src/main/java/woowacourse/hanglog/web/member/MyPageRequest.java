package woowacourse.hanglog.web.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record MyPageRequest(
    @NotBlank(message = "닉네임은 공백이 될 수 없습니다.")
    @Size(max = 15, message = "닉네임은 15자를 초과할 수 없습니다.")
    String nickname,

    @NotBlank(message = "프로필 사진을 필수입니다.")
    String imageUrl
) {

}

