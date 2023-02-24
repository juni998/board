package com.board.board.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * {
 *     "code" : "400",
 *     "message" : "잘못된 요청입니다.",
 *     "Validation" : {
 *         "title" : "값을 입력해주세요"
 *     }
 * }
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String code;
    private String message;
    private Validation validation;

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public void addValidation(String fieldName, String errorMessage) {
        Validation build = Validation.builder()
                .fieldName(fieldName)
                .errorMessage(errorMessage)
                .build();
        this.validation = build;
    }

}


