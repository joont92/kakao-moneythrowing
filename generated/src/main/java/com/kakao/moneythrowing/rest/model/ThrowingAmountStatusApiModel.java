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
 * ThrowingAmountStatusApiModel
 */

public class ThrowingAmountStatusApiModel   {
  @JsonProperty("total")
  private Integer total;

  @JsonProperty("completed")
  private Integer completed;

  @JsonProperty("remain")
  private Integer remain;

  public ThrowingAmountStatusApiModel total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  @ApiModelProperty(value = "")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public ThrowingAmountStatusApiModel completed(Integer completed) {
    this.completed = completed;
    return this;
  }

  /**
   * Get completed
   * @return completed
  */
  @ApiModelProperty(value = "")


  public Integer getCompleted() {
    return completed;
  }

  public void setCompleted(Integer completed) {
    this.completed = completed;
  }

  public ThrowingAmountStatusApiModel remain(Integer remain) {
    this.remain = remain;
    return this;
  }

  /**
   * Get remain
   * @return remain
  */
  @ApiModelProperty(value = "")


  public Integer getRemain() {
    return remain;
  }

  public void setRemain(Integer remain) {
    this.remain = remain;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThrowingAmountStatusApiModel throwingAmountStatus = (ThrowingAmountStatusApiModel) o;
    return Objects.equals(this.total, throwingAmountStatus.total) &&
        Objects.equals(this.completed, throwingAmountStatus.completed) &&
        Objects.equals(this.remain, throwingAmountStatus.remain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, completed, remain);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThrowingAmountStatusApiModel {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
    sb.append("    remain: ").append(toIndentedString(remain)).append("\n");
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

