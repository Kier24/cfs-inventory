package com.cfs.inventory.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportService implements ReportService {

	@Autowired
	private DataSource dataSource;

	@Override
	public void generateReport(LocalDate date) {
		InputStream employeeReportStream = getClass().getResourceAsStream("/employeeReport.jrxml");

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void generateReportByStartAndEndDate(LocalDate startDate, LocalDate endDate) {
		

	}
	

}
