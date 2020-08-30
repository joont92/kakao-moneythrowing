package com.kakao.moneythrowing.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.kakao.moneythrowing.rest.model.ReceiverApiModel;
import com.kakao.moneythrowing.rest.model.ThrowingAmountStatusApiModel;
import com.kakao.moneythrowing.rest.model.ThrowingTimeApiModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ThrowingApiModel
 */

public class ThrowingApiModel   {
  @JsonProperty("time")
  private ThrowingTimeApiModel time;

  @JsonProperty("amountStatus")
  private ThrowingAmountStatusApiModel amountStatus;

  @JsonProperty("receivers")
  @Valid
  private List<ReceiverApiModel> receivers = null;

  public ThrowingApiModel time(ThrowingTimeApiModel time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  */
  @ApiModelProperty(value = "")

  @Valid

  public ThrowingTimeApiModel getTime() {
    return time;
  }

  public void setTime(ThrowingTimeApiModel time) {
    this.time = time;
  }

  public ThrowingApiModel amountStatus(ThrowingAmountStatusApiModel amountStatus) {
    this.amountStatus = amountStatus;
    return this;
  }

  /**
   * Get amountStatus
   * @return amountStatus
  */
  @ApiModelProperty(value = "")

  @Valid

  public ThrowingAmountStatusApiModel getAmountStatus() {
    return amountStatus;
  }

  public void setAmountStatus(ThrowingAmountStatusApiModel amountStatus) {
    this.amountStatus = amountStatus;
  }

  public ThrowingApiModel receivers(List<ReceiverApiModel> receivers) {
    this.receivers = receivers;
    return this;
  }

  public ThrowingApiModel addReceiversItem(ReceiverApiModel receiversItem) {
    if (this.receivers == null) {
      this.receivers = new ArrayList<>();
    }
    this.receivers.add(receiversItem);
    return this;
  }

  /**
   * Get receivers
   * @return receivers
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<ReceiverApiModel> getReceivers() {
    return receivers;
  }

  public void setReceivers(List<ReceiverApiModel> receivers) {
    this.receivers = receivers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThrowingApiModel throwing = (ThrowingApiModel) o;
    return Objects.equals(this.time, throwing.time) &&
        Objects.equals(this.amountStatus, throwing.amountStatus) &&
        Objects.equals(this.receivers, throwing.receivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, amountStatus, receivers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThrowingApiModel {\n");
    
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    amountStatus: ").append(toIndentedString(amountStatus)).append("\n");
    sb.append("    receivers: ").append(toIndentedString(receivers)).append("\n");
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

