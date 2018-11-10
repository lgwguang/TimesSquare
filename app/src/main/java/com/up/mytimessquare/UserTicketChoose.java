package com.up.mytimessquare;

import java.io.Serializable;
import java.util.Date;

/**
 * 此实体为用户进入预订机票前的一些选择项信息
 * 
 * @author hedongli-03
 * 
 */
public class UserTicketChoose implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public String SCity, SCityCode,ECity,ECityCode;
	public String SDetailDate,EDetailDate;
	public String SDate, SWeek, EDate, EWeek;
	public Date startDate,endDate;
	public String OrderType="Normal",AirCorpName,AirCorpCode,TelePhoneNum;
	private boolean isLimite=false, isRoundTrip; 
	public long OrderId=0;
	public double BunkPrice=0;

	private int CountLimited;
	
	public int getCountLimited() {
		return CountLimited;
	}

	public void setCountLimited(int countLimited) {
		CountLimited = countLimited;
	}

	public String getTelePhoneNum() {
		return TelePhoneNum;
	}

	public void setTelePhoneNum(String phoneNum) {
		TelePhoneNum = phoneNum;
	}

	public double getBunkPrice() {
		return BunkPrice;
	}

	public void setBunkPrice(double bunkPrice) {
		BunkPrice = bunkPrice;
	}

	public long getOrderId() {
		return OrderId;
	}

	public void setOrderId(long orderId) {
		OrderId = orderId;
	}

	public void setLimite(boolean isLimite) {
		this.isLimite = isLimite;
	}

	public void setRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public String getAirCorpName() {
		return AirCorpName;
	}

	public void setAirCorpName(String airCorpName) {
		AirCorpName = airCorpName;
	}

	public String getAirCorpCode() {
		return AirCorpCode;
	}

	public void setAirCorpCode(String airCorpCode) {
		AirCorpCode = airCorpCode;
	}

	public String getSCityCode() {
		return SCityCode;
	}

	public void setSCityCode(String sCityCode) {
		SCityCode = sCityCode; 
	}

	public String getECityCode() {
		return ECityCode;
	}

	public void setECityCode(String eCityCode) {
		ECityCode = eCityCode;
	}

	public String getSDetailDate() {
		return SDetailDate;
	}

	public void setSDetailDate(String sDetailDate) {
		SDetailDate = sDetailDate;
	}

	public String getEDetailDate() {
		return EDetailDate;
	}

	public void setEDetailDate(String eDetailDate) {
		EDetailDate = eDetailDate;
	}

	public String getSCity() {
		return SCity;
	}

	public void setSCity(String sCity) {
		SCity = sCity;
	}

	public String getECity() {
		return ECity;
	}

	public void setECity(String eCiyt) {
		ECity = eCiyt;
	}

	public String getSDate() {
		return SDate;
	}

	public void setSDate(String sDate) {
		SDate = sDate;
	}

	public String getSWeek() {
		return SWeek;
	}

	public void setSWeek(String sWeek) {
		SWeek = sWeek;
	}

	public String getEDate() {
		return EDate;
	}

	public void setEDate(String eDate) {
		EDate = eDate;
	}

	public String getEWeek() {
		return EWeek;
	}

	public void setEWeek(String eWork) {
		EWeek = eWork;
	}

	public boolean isLimite() {
		return isLimite;
	}

	public void setIsLimite(boolean isLimite) {
		this.isLimite = isLimite;
	}

	public boolean isRoundTrip() {
		return isRoundTrip;
	}

	public void setIsRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
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
}
