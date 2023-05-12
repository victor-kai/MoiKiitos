package com.align.web.response;

import java.io.Serializable;
import java.util.Objects;

public class RestApiResponse<T> implements Serializable {
  private T dataObject;
  private String error;

  private String message;

  public RestApiResponse() {}

  public RestApiResponse(String error) {
    this.error = error;
  }

  public RestApiResponse(T dataObject) {
    this.dataObject = dataObject;
  }

  public RestApiResponse(T dataObject, String message) {
    this.dataObject = dataObject;
    this.message = message;
  }

  public RestApiResponse(T dataObject, String message, String error) {
    this.dataObject = dataObject;
    this.error = error;
    this.message = message;
  }

  public T getDataObject() {
    return dataObject;
  }

  public void setDataObject(T dataObject) {
    this.dataObject = dataObject;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RestApiResponse<?> response = (RestApiResponse<?>) o;
    return Objects.equals(dataObject, response.dataObject) && Objects.equals(error, response.error) && Objects.equals(message, response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObject, error, message);
  }
}
