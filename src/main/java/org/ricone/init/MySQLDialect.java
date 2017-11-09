package org.ricone.init;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDialect extends MySQL5InnoDBDialect {

   public MySQLDialect() {
      super();
      registerFunction("regexp", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 REGEXP ?2"));
   }
}