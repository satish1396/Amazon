package com.example.amazon.customer_service.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
    private final HttpStatus status;
    private final String message;
    private final String code;
    private T body;

    public ApiResponse() {
        this.status = HttpStatus.OK;
        this.message = "Success";
        this.code = String.valueOf(HttpStatus.OK.value());
    }
    public ApiResponse(final HttpStatus status, final String message, final String code, final T body) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.body = body;
    }

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder();
    }
    @ToString
    public static class ApiResponseBuilder<T> {
        private HttpStatus status = HttpStatus.OK;
        private String message = "Success";
        private String code = String.valueOf(HttpStatus.OK.value());
        private T body;

        public ApiResponseBuilder<T> status(final HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> code(final String code) {
            this.code = code;
            return this;
        }

        public ApiResponseBuilder<T> body(final T body) {
            this.body = body;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse(this.status, this.message, this.code, this.body);
        }
    }
}
