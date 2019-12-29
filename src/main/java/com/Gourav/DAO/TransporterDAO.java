package com.Gourav.DAO;

import com.Gourav.models.Transporter;

public interface TransporterDAO {

	public void addTransporter(Transporter transporter);
	public boolean verifyTransporter(String email, String password);
	public Transporter getTransporterByEmail(String email);
	public void updateTransporter(Transporter transporter);
}
