package tipview.toyproject.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    //@Email 이런결로 유형성 검사를 시해 @Valid로
    @NotEmpty(message = "이름은 null일 수 없다")
    private String nickName;

    private String password;
}

