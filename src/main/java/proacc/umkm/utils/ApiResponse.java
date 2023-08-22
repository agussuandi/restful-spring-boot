package proacc.umkm.utils;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean status;

    private final T data;

    public ApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }
}
