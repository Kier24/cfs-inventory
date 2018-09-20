package com.cfs.inventory.service;

import java.time.LocalDate;

public interface ReportService {

	void generateReport(LocalDate date);

	void generateReportByStartAndEndDate(LocalDate startDate, LocalDate endDate);

}
