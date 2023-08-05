package com.turing.springpetclinic.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Milan on 2023/07/15.
 */

public class Helper {

	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}
}
