package com.pillgnal.backend.domain.pill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pillgnal.backend.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Pill extends BaseTimeEntity{
//db랑 똑같은 필드
	@Id
	@Column(name = "pid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pNum;
	private String pName;
	private String pCompany;
	private String pImage;
	private String pWritingfront;
	private String pWritingback;
	private String pShape;
	private String pColor;
	private String pManual;
	private String pClass;
	private String pType;
	
	@Builder
	public Pill(String pNum, String pName, String pCompany, String pImage, String pWritingfront, String pWritingback,
			String pShape, String pColor, String pManual, String pClass, String pType) {
		this.pNum = pNum;
		this.pName = pName;
		this.pCompany = pCompany;
		this.pImage = pImage;
		this.pWritingfront = pWritingfront;
		this.pWritingback = pWritingback;
		this.pShape = pShape;
		this.pColor = pColor;
		this.pManual = pManual;
		this.pClass = pClass;
		this.pType = pType;
	}
	
	
}

