package zeyracakes.co.tz.Common.Utilities;

public class ApiResponse<T> {
    private boolean isSuccess;
    private String errorMessage;
    private T data;

    public ApiResponse(boolean isSuccess, String errorMessage, T data) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true,null,data);
    }

    public static <T> ApiResponse<T> failure(String errorMessage) {
        return new ApiResponse<>(false, errorMessage,null);
    }

    // Getters and Setters (or use Lombok if preferred)
    public boolean isSuccess() {
        return isSuccess;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return errorMessage;
    }
}
