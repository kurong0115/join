package com.matrix.join.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ApiResponse
 * @Description 响应对象
 * @Author Administrator
 * @Date 2020/1/17 15:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = -3818996747837866200L;

	private Integer code;

	private String message;

	private T data;

	private Pagination pagination;

	public ApiResponseBuilder builder() {
		return new ApiResponseBuilder();
	}

	public static <T> ApiResponse<T> responseData(T t) {
		return new ApiResponse<T>().builder().code(200).message("ok").data(t).build();
	}

	public class ApiResponseBuilder {

		private Integer code;

		private String message;

		private T data;

        private Pagination pagination;

		public ApiResponseBuilder code(Integer code) {
			this.code = code;
			return this;
		}

		public ApiResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ApiResponseBuilder data(T data) {
			this.data = data;
			return this;
		}

		public ApiResponseBuilder pagination(Pagination pagination) {
		    this.pagination = pagination;
		    return this;
        }

		/**
		 * 构建响应对象
		 * @return
		 */
		public ApiResponse<T> build() {
			return new ApiResponse<>(code, message, data, pagination);
		}
	}
}
