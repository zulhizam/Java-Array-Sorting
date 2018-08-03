package com.jp.test;

import java.util.Date;


/**
 * @author Zulhizam
 * 
 * Instruction Class to represent each instruction executed. 
 *
 */
public class Instruction {

	// Enum for type
	public enum InstructionType {
		BUY, SELL
	}

	Entity entity;
	InstructionType type;
	double agreedFx;
	String currency;
	Date instructionDate;
	Date settlementDate;
	int units;
	double unitPrice;

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return the type
	 */
	public InstructionType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(InstructionType type) {
		this.type = type;
	}

	/**
	 * @return the agreedFx
	 */
	public double getAgreedFx() {
		return agreedFx;
	}

	/**
	 * @param agreedFx
	 *            the agreedFx to set
	 */
	public void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the instructionDate
	 */
	public Date getInstructionDate() {
		return instructionDate;
	}

	/**
	 * @param instructionDate
	 *            the instructionDate to set
	 */
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	/**
	 * @return the settlementDate
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}

	/**
	 * @param settlementDate
	 *            the settlementDate to set
	 */
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
