package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


// конфигурация java persistence api
@Configuration
@EnableJpaRepositories("app") // ищем в пакете app интерфейсы репозиториев
@EnableTransactionManagement // разрешаем @Transactional
public class JPAConfig {

    @Bean
    public DataSource getSource() { // конфигурация источника из конфига db.cfg и тд
        final Properties info = new Properties();
        try {
            info.load(this.getClass().getResourceAsStream("/db.cfg"));
        } catch (IOException ignored) { } // impossible
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/studs");
        dataSource.setUsername(info.getProperty("user"));
        dataSource.setPassword(info.getProperty("password"));

        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getSource());
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("app");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() { // конфигурация менеджера транзакций
        final var object = entityManagerFactory().getObject();
        assert object != null;
        return new JpaTransactionManager(object);
    }
}
