package org.gennerator.main;

import java.io.IOException;
import java.sql.SQLException;

import org.gennerator.util.GennerateUtil;

public class App {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, IOException {

		GennerateUtil.generate();
	}

}
