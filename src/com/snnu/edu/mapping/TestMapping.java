package com.snnu.edu.mapping;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestMapping {

	public static void main(String[] args) {		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure();
		SchemaExport export = new SchemaExport(configuration);
		export.execute(true, true, false, true);
	}

}
