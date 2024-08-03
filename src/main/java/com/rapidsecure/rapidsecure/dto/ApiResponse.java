package com.rapidsecure.rapidsecure.dto;


public class ApiResponse<T> {
    private int code;      // Código de estado HTTP o código personalizado
    private String message; // Mensaje de la respuesta
    private boolean success; // Estado de éxito (true/false)
    private T data;         // Datos de la respuesta

    public ApiResponse() {}

    public ApiResponse(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    // Getters y setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}