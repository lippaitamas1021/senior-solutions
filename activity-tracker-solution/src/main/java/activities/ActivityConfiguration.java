//package activities;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import org.flywaydb.core.Flyway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.sql.DataSource;
//
//@Configuration
//@ComponentScan(basePackageClasses = ActivityConfiguration.class)
//@PropertySource("classpath:/application.properties")
//@EnableTransactionManagement
//@EnableJpaRepositories
//public class ActivityConfiguration {
//
//    private DataSource dataSource;
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public DataSource dataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl(environment.getProperty("jdbc.url"));
//        dataSource.setUser(environment.getProperty("jdbc.username"));
//        dataSource.setPassword(environment.getProperty("jdbc.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public Flyway flyway() {
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.migrate();
//        return flyway;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        return new JpaTransactionManager();
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(true);
//        return hibernateJpaVendorAdapter;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setPackagesToScan();
//        return entityManagerFactoryBean;
//    }
//}
