package nl.minjak.bpmn.camunda.demo;

import java.io.Serializable;

public class GarageOnderhoudsProces implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String typeOnderhoud;
	boolean indAPK;
	boolean indBetalingContant;
	boolean indBetalingVoldaan;
	boolean indHerinneringVerstuurd;
	boolean indBetalingOntvangen;

	public boolean isIndBetalingOntvangen() {
		return indBetalingOntvangen;
	}

	public void setIndBetalingOntvangen(boolean indBetalingOntvangen) {
		this.indBetalingOntvangen = indBetalingOntvangen;
	}

	String intaker;

	public String getIntaker() {
		return intaker;
	}

	public void setIntaker(String intaker) {
		this.intaker = intaker;
	}

	public boolean isIndHerinneringVerstuurd() {
		return indHerinneringVerstuurd;
	}

	public void setIndHerinneringVerstuurd(boolean indHerinneringVerstuurd) {
		this.indHerinneringVerstuurd = indHerinneringVerstuurd;
	}

	public String getDurationUntilAppointment() {
		return durationUntilAppointment;
	}

	public void setDurationUntilAppointment(String durationUntilAppointment) {
		this.durationUntilAppointment = durationUntilAppointment;
	}

	String durationUntilAppointment;

	public void init() {

		switch (new Double(Math.random() * 3).intValue()) {
		case 1:
			typeOnderhoud = "kleine beurt";
			switch (new Double(Math.random() * 3).intValue()) {
			case 1:
				indAPK = true;
				break;
			default:
				indAPK = false;
			}
			break;
		case 2:
			typeOnderhoud = "grote beurt";
			switch (new Double(Math.random() * 3).intValue()) {
			case 1:
				indAPK = true;
				break;
			default:
				indAPK = false;
			}
			break;
		default:
			typeOnderhoud = "pechgeval";
		}

		switch (new Double(Math.random() * 1.5).intValue()) {
		case 1:
			indBetalingContant = true;
			break;
		default:
			indBetalingContant = false;
		}

		switch (new Double(Math.random() * 2).intValue()) {
		case 1:
			indBetalingVoldaan = true;
			break;
		default:
			indBetalingVoldaan = false;
		}

		switch (new Double(Math.random() * 3).intValue()) {
		case 1:
			indHerinneringVerstuurd = true;
			break;
		default:
			indHerinneringVerstuurd = false;
		}

		if (typeOnderhoud != "pechgeval") {
			durationUntilAppointment = "PT" + new Double(Math.random() * 60).intValue() + "S";
		} else {
			durationUntilAppointment = "PT0S";
		}

		switch (new Double(Math.random() * 2).intValue()) {
		case 1:
			intaker = "directeur";
			break;
		default:
			intaker = "boekhouding";
		}
		indBetalingOntvangen = false;
	}

	public String getTypeOnderhoud() {
		return typeOnderhoud;
	}

	public void setTypeOnderhoud(String typeOnderhoud) {
		this.typeOnderhoud = typeOnderhoud;
	}

	public boolean isIndAPK() {
		return indAPK;
	}

	public void setIndAPK(boolean indAPK) {
		this.indAPK = indAPK;
	}

	public boolean isIndBetalingContant() {
		return indBetalingContant;
	}

	public void setIndBetalingContant(boolean indBetalingContant) {
		this.indBetalingContant = indBetalingContant;
	}

	public boolean isIndBetalingVoldaan() {
		return indBetalingVoldaan;
	}

	public void setIndBetalingVoldaan(boolean indBetalingVoldaan) {
		this.indBetalingVoldaan = indBetalingVoldaan;
	}

}
