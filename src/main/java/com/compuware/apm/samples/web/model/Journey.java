package com.compuware.apm.samples.web.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Journey {
  private Long id;
  
  private Date startDate;
  private Date endDate;
  private BigDecimal price;
  
  public Journey(Date start, Date end) {
      this.startDate = start;
      this.endDate = end;
  }

  // hibernate constructor
  private Journey() {
      
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
      return id;
  }
  
  private void setId(Long id) {
      this.id = id;
  }
  public Date getStartDate() {
      return startDate;
  }
  public void setStartDate(Date startDate) {
      this.startDate = startDate;
  }
  public Date getEndDate() {
      return endDate;
  }
  public void setEndDate(Date endDate) {
      this.endDate = endDate;
  }
  
  public BigDecimal getPrice() {
      return price;
  }
  public void setPrice(BigDecimal price) {
      this.price = price;
  }

}
