package com.kakao.moneythrowing.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateThrowingRequest
 */

public class CreateThrowingRequest   {
  @JsonProperty("moneyAmount")
  private Integer moneyAmount;

  @JsonProperty("peopleCount")
  private Integer peopleCount;

  public CreateThrowingRequest moneyAmount(Integer moneyAmount) {
    this.moneyAmount = moneyAmount;
    return this;
  }

  /**
   * Get moneyAmount
   * @return moneyAmount
  */
  @ApiModelProperty(value = "")


  public Integer getMoneyAmount() {
    return moneyAmount;
  }

  public void setMoneyAmount(Integer moneyAmount) {
    this.moneyAmount = moneyAmount;
  }

  public CreateThrowingRequest peopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
    return this;
  }

  /**
   * Get peopleCount
   * @return peopleCount
  */
  @ApiModelProperty(value = "")


  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateThrowingRequest createThrowingRequest = (CreateThrowingRequest) o;
    return Objects.equals(this.moneyAmount, createThrowingRequest.moneyAmount) &&
        Objects.equals(this.peopleCount, createThrowingRequest.peopleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moneyAmount, peopleCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateThrowingRequest {\n");
    
    sb.append("    moneyAmount: ").append(toIndentedString(moneyAmount)).append("\n");
    sb.append("    peopleCount: ").append(toIndentedString(peopleCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

