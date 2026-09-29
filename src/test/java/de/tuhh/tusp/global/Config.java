package de.tuhh.tusp.global;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "de.tuhh.tusp.global.dao")
public class Config {}
