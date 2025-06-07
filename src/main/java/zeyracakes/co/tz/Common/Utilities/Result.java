package zeyracakes.co.tz.Common.Utilities;

public class Result<T> {
    private boolean isSuccess;
    private T data;
    private String error;

    public Result() {
    }


    public Result(boolean isSuccess, String error) {
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public Result(boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
