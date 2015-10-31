package org.gennerator.test;

import java.io.IOException;
import java.sql.SQLException;

import org.gennerator.util.GennerateUtil;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		GennerateUtil g = new GennerateUtil();
		g.generate();
	}

}
