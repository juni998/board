package com.board.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter @Getter
@ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.") //검증
    private String title;
    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private String content;

    protected PostCreate() {

    }
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 빌더의 장점
    //  - 가독성이 좋다
    //  - 값 생성에 대한 유연함
    //  - 필요한 값만 받을 수 있다 (오버로딩 가능한 조건 찾아보세요)
    //  - 객체의 불변성 보장


}
