package com.kakao.moneythrowing.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReceivingApiModel
 */

public class ReceivingApiModel   {
  @JsonProperty("acquirer")
  private UUID acquirer;

  @JsonProperty("amount")
  private Integer amount;

  public ReceivingApiModel acquirer(UUID acquirer) {
    this.acquirer = acquirer;
    return this;
  }

  /**
   * Get acquirer
   * @return acquirer
  */
  @ApiModelProperty(value = "")

  @Valid

  public UUID getAcquirer() {
    return acquirer;
  }

  public void setAcquirer(UUID acquirer) {
    this.acquirer = acquirer;
  }

  public ReceivingApiModel amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @ApiModelProperty(value = "")


  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReceivingApiModel receiving = (ReceivingApiModel) o;
    return Objects.equals(this.acquirer, receiving.acquirer) &&
        Objects.equals(this.amount, receiving.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acquirer, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReceivingApiModel {\n");
    
    sb.append("    acquirer: ").append(toIndentedString(acquirer)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

