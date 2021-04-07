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
	
	private String pnum;
	private String pname;
	private String pcompany;
	private String pimage;
	private String pwritingfront;
	private String pwritingback;
	private String pshape;
	private String pcolor;
	private String pmanual;
	private String pclass;
	private String ptype;
	
	@Builder
	public Pill(String pnum, String pname, String pcompany, String pimage, String pwritingfront,
				String pwritingback, String pshape, String pcolor, String pmanual, String pclass, String ptype) {
		this.pnum = pnum;
		this.pname = pname;
		this.pcompany = pcompany;
		this.pimage = pimage;
		this.pwritingfront = pwritingfront;
		this.pwritingback = pwritingback;
		this.pshape = pshape;
		this.pcolor = pcolor;
		this.pmanual = pmanual;
		this.pclass = pclass;
		this.ptype = ptype;
	}
}

