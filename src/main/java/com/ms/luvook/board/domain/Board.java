package com.ms.luvook.board.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ms.luvook.common.domain.IsUse;
import com.ms.luvook.common.util.DateCalculator;
import com.ms.luvook.member.domain.MemberMaster;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Created by vivie on 2017-07-17.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "boardId")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;

    @Column(name = "member_id")
    private int memberId;

    @Lob
    private String contents;

    private int grade;

    @Enumerated(EnumType.STRING)
    private IsUse isUse;

    @ManyToOne(optional=false)
    @JoinColumn(name = "member_id", insertable=false, updatable=false)
    private MemberMaster member;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modDate;

    @Transient
    private int heartCount;
    
    @Transient
    private int commentCount;
    
    @Transient
    private Boolean isClickedHeart;

	public Board(int memberId, String contents, int grade) {
		this.memberId = memberId;
		this.contents = contents;
		this.grade = grade;
	}
	
	public String getRegDate(){
		return DateCalculator.calculateFromCurrent(regDate); 
	}
	
	public String getModDate(){
		return DateCalculator.calculateFromCurrent(modDate); 
	}

	public Date getRegDateObj(){
	    return regDate;
    }
}
