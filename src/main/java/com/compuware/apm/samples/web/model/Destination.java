package com.compuware.apm.samples.web.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Destination {
  private Long id;

  private String title;
  private List<Journey> dates;

  public Destination() {
  }

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  @Column(name="DESTINATION_ID")
  public Long getId() {
      return id;
  }

  private void setId(Long id) {
      this.id = id;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }
  
  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="OWNER_ID", referencedColumnName="DESTINATION_ID")
  public List<Journey> getDates() {
      return dates;
  }
  public void setDates(List<Journey> dates) {
      this.dates = dates;
  }
}
