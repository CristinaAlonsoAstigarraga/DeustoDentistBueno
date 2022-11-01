package test;



import java.sql.Connection;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BD.BD;

class BDTest {
	Connection con;
	
	@Before
	public void setUp() throws Exception {
		con = BD.initBD("BasesDeDatos.db");
	}

	@After
	public void tearDown() throws Exception {
		con.close();
	}
	@Test
	void test() {
		
	}

}
