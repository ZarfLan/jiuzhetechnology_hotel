package com.jiuzhe.app.hotel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.address}")
	private String address;

	@Value("${rabbitmq.username}")
	private String username;

	@Value("${rabbitmq.password}")
	private String password;

	@Value("${rabbitmq.virtualHost}")
	private String virtualHost;


    @Bean
	public ConnectionFactory connectionFactory() {
	    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

	    connectionFactory.setAddresses(address);
	    connectionFactory.setUsername(username);
	    connectionFactory.setPassword(password);
	    connectionFactory.setVirtualHost(virtualHost);
	    connectionFactory.setPublisherConfirms(true);

	    return connectionFactory;
	}

	@Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


}
