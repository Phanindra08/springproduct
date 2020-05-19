package net.codejava;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Billing {
	//private Long id;
	@Id
	private String bcode;
	private String bname;
	private int bgst;
	private float bprice;
	private int bquantity;

	protected Billing() {
	}

	protected Billing(String bcode,String bname, int bgst, float bprice,int bquantity) {
		super();
		this.bname = bname;
		this.bcode = bcode;
		this.bgst = bgst;
		this.bprice = bprice;
		this.bquantity=bquantity;
	}

	public String getBcode() {
		return bcode;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	
	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getBgst() {
		return bgst;
	}

	public void setBgst(int bgst) {
		this.bgst = bgst;
	}

	public float getBprice() {
		return bprice;
	}

	public void setBprice(float bprice) {
		this.bprice = bprice;
	}
	
	public int getBquantity() {
		return bquantity;
	}

	public void setBquantity(int bquantity) {
		this.bquantity = bquantity;
	}
}
