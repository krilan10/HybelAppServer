/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author kristian
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
public class Username implements Serializable{
    @Id 
    String id;
    @Version
    Timestamp version;
    
    public Username (String id){
        this.id = id;
    }
    
}
